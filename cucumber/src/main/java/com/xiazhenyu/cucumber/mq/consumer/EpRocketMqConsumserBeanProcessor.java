package com.xiazhenyu.cucumber.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.xiazhenyu.cucumber.mq.EpConsumeMode;
import com.xiazhenyu.cucumber.mq.EpMessageBaseDTO;
import com.xiazhenyu.cucumber.mq.EpMessageHolder;
import com.xiazhenyu.cucumber.mq.SysMqConsumeRecordService;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Date: 2022/10/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Component
@Slf4j
@ConditionalOnBean(name = "eopParentApplication")
public class EpRocketMqConsumserBeanProcessor implements BeanPostProcessor {

    public static final String SPRING_PROFILE_ACTIVE = "spring.profile.active";

    @Resource
    private Environment environment;

    private Map<String, DefaultMQPullConsumer> cacheConsumerMap = new ConcurrentHashMap<>(100);

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    protected boolean filterMqConsumerClass(Class clazz) {
        return clazz != null && !clazz.getName().startsWith("com.eptison.eop.thirdpart");
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(bean);
        if (EpRocketMqConsumer.class.isAssignableFrom(targetClass) && this.filterMqConsumerClass(targetClass)) {
            Map<Method, EpRocketMqConsumerMethod> annotatedMethods = MethodIntrospector.selectMethods(targetClass,
                    (MethodIntrospector.MetadataLookup<EpRocketMqConsumerMethod>) method -> AnnotatedElementUtils.getMergedAnnotation(
                            method, EpRocketMqConsumerMethod.class)
            );
            if (!annotatedMethods.isEmpty()) {
                for (Map.Entry<Method, EpRocketMqConsumerMethod> entry : annotatedMethods.entrySet()) {
                    EpRocketMqConsumerMethod method = entry.getValue();
                    if (method.groupName().getNameHelper().length() >= 100) {
                        throw new RuntimeException();
                    }
                    if (method.topic().getNameHelper().length() >= 100) {
                        throw new RuntimeException();
                    }
                    String groupName = tryWrapSysEnvAndLocalHostName(method.groupName().getNameHelper());
                    if (cacheConsumerMap.keySet().contains(groupName)) {
                        return bean;
                    }
                    cacheConsumerMap.put(groupName, createNewConsumer(groupName, method, bean, entry.getKey()));


                }

            }


        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }


    private String tryWrapSysEnvAndLocalHostName(String groupName) {
        StringBuilder groupNameBuilder = new StringBuilder(groupName);
        groupNameBuilder.append("-" + environment.getProperty(SPRING_PROFILE_ACTIVE));
        return groupNameBuilder.toString();

    }

    @Resource
    private SysMqConsumeRecordService mqConsumeRecordService;


    private DefaultMQPushConsumer createNewConsumer(String groupName, EpRocketMqConsumerMethod anno, Object bean,
            Method method) {
        String nameServer = environment.getProperty("spring.rocket.name-server");

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName, true);
        consumer.setConsumeThreadMin(anno.consumeThreadMin());
        consumer.setConsumeThreadMax(anno.consumeThreadMax());
        consumer.setConsumeTimeout(anno.consumeTimeout());
        consumer.setNamesrvAddr(nameServer);

        try {
            this.consumerReSubscribe(anno, consumer);
            if (EpConsumeMode.ORDERLY.equals(anno.consumerMode())) {
                consumer.setMaxReconsumeTimes(5);
                setOrderLyListener(bean, method, consumer);
            } else {
                consumer.setMaxReconsumeTimes(5);

            }

        } catch (Exception e) {
            throw new RuntimeException();
        }

        return consumer;


    }


    private StringBuilder buildSql92(EpRocketMqConsumerMethod method) {
        try {
            StringBuilder selectSql92 = new StringBuilder(
                    EpMessageHolder.KEY_HOST_NAME + "=" + Inet4Address.getLocalHost().getHostName().replace("\\.", "-"));
            if (StringUtils.isNotBlank(method.tag()) && !"*".equals(method.tag())) {
                selectSql92.append(" and ");
                boolean isFirst = true;
                for (String tag : method.tag().split("\\|\\|")) {
                    if (isFirst) {
                        selectSql92.append(MessageConst.PROPERTY_TAGS + "=" + tag + "' ");
                        isFirst = false;
                    } else {
                        selectSql92.append("or " + MessageConst.PROPERTY_TAGS + "=" + tag + "' ");
                    }
                }
            }
            return selectSql92;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }


    private void consumerReSubscribe(EpRocketMqConsumerMethod method, DefaultMQPushConsumer consumer) throws MQClientException {
        if ("dev".equals(environment.getProperty(SPRING_PROFILE_ACTIVE)) ||
                "test".equals(environment.getProperty(SPRING_PROFILE_ACTIVE))) {
            StringBuilder selectSql92 = buildSql92(method);
            consumer.subscribe(method.topic().getNameHelper(), MessageSelector.bySql(selectSql92.toString()));
        } else {
            consumer.subscribe(method.topic().getNameHelper(), method.tag());
        }
    }


    private void setOrderLyListener(Object bean, Method method, DefaultMQPushConsumer consumer) {
        consumer.registerMessageListener((List<MessageExt> msgs, ConsumeOrderlyContext context) -> {
            MessageExt currentMsg = null;
            try {

                for (MessageExt msgExt : msgs) {
                    currentMsg = msgExt;
                    long now = System.currentTimeMillis();
                    invokeConsumerMessage(bean, method, msgExt);
                    long costTime = System.currentTimeMillis() - now;
                    log.warn("consumer orderly message success");
                }
            } catch (Exception e) {
            }
            return ConsumeOrderlyStatus.SUCCESS;
        });
    }


    private void setConcurrentlyListener(Object bean, Method method, DefaultMQPushConsumer consumer) {
        consumer.registerMessageListener((List<MessageExt> msgs,ConsumeConcurrentlyContext context) -> {
            MessageExt currentMsg = null;
            try {

                for (MessageExt msgExt : msgs) {
                    currentMsg = msgExt;
                    long now = System.currentTimeMillis();
                    invokeConsumerMessage(bean, method, msgExt);
                    long costTime = System.currentTimeMillis() - now;
                    log.warn("consumer orderly message success");
                }
            } catch (Exception e) {
            }
            return ConsumeOrderlyStatus.SUCCESS;
        });
    }













    private void invokeConsumerMessage(Object bean, Method method, MessageExt messageExt)
            throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        boolean isCollection = "true".equalsIgnoreCase(messageExt.getUserProperty(EpMessageHolder.KEY_IS_COLLECTION));
        Class msgEntityClass = Class.forName(messageExt.getUserProperty(EpMessageHolder.KEY_ENTITY_CLASS));
        Object methodArg = isCollection ? JSON.parseArray(new String(messageExt.getBody(), IOUtils.UTF8), msgEntityClass) :
                JSON.parseObject(messageExt.getBody(), msgEntityClass);
        if (EpMessageBaseDTO.class.isAssignableFrom(msgEntityClass) && methodArg != null) {
            Long msgMaxOffset = StringUtils.isBlank(messageExt.getProperty(MessageConst.PROPERTY_MAX_OFFSET)) ? 0L :
                    Long.parseLong(messageExt.getProperty(MessageConst.PROPERTY_MAX_OFFSET));
            if (isCollection) {
                ((Collection) methodArg).forEach(item -> {
                    ((EpMessageBaseDTO) item).setMessageId(messageExt.getMsgId());
                    ((EpMessageBaseDTO) item).setTopic(messageExt.getTopic());
                    ((EpMessageBaseDTO) item).setConsumeOffsetDiff(msgMaxOffset - messageExt.getQueueOffset());
                });
            } else {
                ((EpMessageBaseDTO) methodArg).setMessageId(messageExt.getMsgId());
                ((EpMessageBaseDTO) methodArg).setTopic(messageExt.getTopic());
                ((EpMessageBaseDTO) methodArg).setConsumeOffsetDiff(msgMaxOffset - messageExt.getQueueOffset());
            }
        }
        method.invoke(bean, methodArg);
    }


}