package com.xiazhenyu.cucumber.mq.producer;

import com.xiazhenyu.cucumber.mq.EpDefaultProducerTransactionListener;
import com.xiazhenyu.cucumber.mq.EpMessageHolder;
import com.xiazhenyu.cucumber.mq.EpProducerTransMsgArg;
import com.xiazhenyu.cucumber.mq.EpRocketMqProducerTransStatus;
import com.xiazhenyu.cucumber.mq.EpRocketMqProducerTransactionAction;
import com.xiazhenyu.cucumber.mq.RocketMqProperties;
import com.xiazhenyu.cucumber.mq.SysMqConsumeRecordService;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Date: 2022/10/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Slf4j
public class MqTemplate implements InitializingBean, DisposableBean {


    private DefaultMQProducer producer;


    private RocketMqProperties properties;

    private Environment environment;

    private SysMqConsumeRecordService mqConsumeRecordService;

    private static Map<String, TransactionMQProducer> cacheProducerTransactionListenerMap = new ConcurrentHashMap<>();

    private Long mqRetryTimeGap;

    private Integer mqRetryTimesWhenSendFailed;

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    public MqTemplate(DefaultMQProducer producer) {
        this.producer = producer;
    }


    public MqTemplate(DefaultMQProducer producer, RocketMqProperties properties) {
        this.producer = producer;
        this.properties = properties;
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }


    public <T> T sendInTransaction(String topic, EpMessageHolder messageHolder, EpRocketMqProducerTransactionAction<T> action) {
        return sendInTransaction(topic, null, messageHolder, action, 1);
    }


    public <T> T sendInTransaction(String topic, String tag, EpMessageHolder messageHolder,
            EpRocketMqProducerTransactionAction<T> action) {
        return sendInTransaction(topic, tag, messageHolder, action, 1);
    }


    public SendResult send(String topic, String tag, EpMessageHolder messageHolder) {
        Message message = messageHolder.buildRocketMqMessage();
        message.setTopic(topic);
        message.setTags(tag);
        Throwable throwable = null;
        for (int i = 0; i < this.mqRetryTimesWhenSendFailed; i++) {
            try {
                long now = System.currentTimeMillis();
                SendResult sendResult = this.producer.send(message);
                long costTime = System.currentTimeMillis() - now;
                return sendResult;
            } catch (Exception e) {
                log.error("send failed,message:" + message, e);
                throwable = e;
            }
            this.blockWhenSendFailed(mqRetryTimeGap, i);
        }
        this.sendDingdingMsgWhenConsumerError(topic);
        throw new RuntimeException(throwable);
    }


    public <T> T sendInTransaction(String topic, String tag, EpMessageHolder messageHolder,
            EpRocketMqProducerTransactionAction<T> action, Integer queueNums) {
        Message message = messageHolder.buildRocketMqMessage();
        message.setTopic(topic);
        message.setTags(tag);
        Throwable throwable = null;
        for (int i = 0; i < this.mqRetryTimesWhenSendFailed; i++) {
            try {
                long now = System.currentTimeMillis();
                TransactionMQProducer producer = getCacheTransProducer(topic, queueNums);
                EpProducerTransMsgArg<T> producerTransMsgArg = new EpProducerTransMsgArg<>(action, null,
                        new EpRocketMqProducerTransStatus());
                long costTime = System.currentTimeMillis() - now;
                return producerTransMsgArg.getActionResult();
            } catch (Exception e) {
                log.error("sendTransaction message failed.");
                throwable = e;
            }
            this.blockWhenSendFailed(mqRetryTimeGap, i);
        }
        this.sendDingdingMsgWhenConsumerError(topic);
        throw new RuntimeException(throwable);
    }


    private void blockWhenSendFailed(Long mqRetryTimeGap, int retryTimes) {
        try {
            Thread.sleep(retryTimes == 0 ? mqRetryTimeGap : (long) (mqRetryTimeGap * retryTimes * .15));
        } catch (Exception e) {
        }
    }


    private void sendDingdingMsgWhenConsumerError(String topic) {
        StringBuilder msgBuilder = new StringBuilder("消息发送失败，请及时查询日志");
        msgBuilder.append("topic: ").append(topic);
        //发送钉钉消息
    }


    private TransactionMQProducer getCacheTransProducer(String topicName, Integer queueNums) {
        if (cacheProducerTransactionListenerMap.get(topicName) == null) {
            synchronized (this) {
                if (cacheProducerTransactionListenerMap.get(topicName) == null) {
                    cacheProducerTransactionListenerMap.put(topicName, createNewTransProducer(topicName, queueNums));
                }
            }
        }
        return cacheProducerTransactionListenerMap.get(topicName);

    }


    private TransactionMQProducer createNewTransProducer(String topicName, Integer queueNums) {
        StringBuilder groupName = new StringBuilder();
        groupName.append("-" + environment.getProperty("spring.profile.active"));
        TransactionMQProducer transProducer = new TransactionMQProducer(groupName.toString());
        transProducer.setNamesrvAddr(properties.getNameServe());
        transProducer.setSendMsgTimeout(properties.getSendMessageTimeout());
        transProducer.setProducerGroup(groupName.toString());
        transProducer.setCompressMsgBodyOverHowmuch(properties.getCompressMessageBodyThreshold());
        transProducer.setRetryTimesWhenSendFailed(5);
        transProducer.setTransactionListener(new EpDefaultProducerTransactionListener());
        transProducer.setDefaultTopicQueueNums(queueNums);
        try {
            transProducer.start();
        } catch (MQClientException e) {
            log.error("start new transProducer error", e);
        }
        return transProducer;
    }


}