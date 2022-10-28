package com.xiazhenyu.cucumber.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageConst;

/**
 * 发送的消息体
 *
 * @author 朱智贤
 * @since 2019-10-23
 */
@Slf4j
public class EpMessageHolder {

    private EpMessageHolder() {
    }

    /**
     * 具体消息实体，有可能是集合
     */
    @Getter
    private Object payload;

    /**
     * 辅助字段, 比如单据ID，用于console快速查找消息
     */
    @Getter
    private String key;

    /**
     * 延时消息
     */
    @Getter
    private EpMessageDelayLevel delay;

    /**
     * 消息的其他属性，比如: rabbitmq里的header, rocketmq里的消息其他属性
     */
    private Map<String, String> properties;

    /**
     * 用于判断消息体是否是集合类
     */
    public static final String KEY_IS_COLLECTION = "epIsPayloadInstanceOfCollection";

    /**
     * 全路径类名，用于反向序列化
     */
    public static final String KEY_ENTITY_CLASS = "epPayloadFullQulifiedClass";

    /**
     * 开发环境，用于解决本地消息被其他开发者消费掉的问题，环境隔离
     */
    public static final String KEY_HOST_NAME = "epMachineHostName";

    public EpMessageHolder setKey(String key) {
        this.key = key;
        return this;
    }

    public <T extends EpMessageBaseDTO> EpMessageHolder setPayload(T payload) {
        this.payload = payload;
        return this;
    }

    public <T extends EpMessageBaseDTO> EpMessageHolder setPayload(Collection<T> payload) {
        //???如何处理令牌上下文
        this.payload = payload;
        return this;
    }

    /**
     * 应急发送消息用到，其他情况不建议使用
     */
    public EpMessageHolder setUnSafePayload(Object payload) {
        this.payload = payload;
        return this;
    }

    public EpMessageHolder setDelay(EpMessageDelayLevel delay) {
        this.delay = delay;
        return this;
    }

    public Map<String, String> getAllExtProperties() {
        return properties;
    }

    private EpMessageHolder setExtProperty(String key, String value) {
        if (this.properties == null) {
            this.properties = new HashMap<>(16);
        }
        this.properties.put(key, value);
        return this;
    }

    public static EpMessageHolder newInst() {
        return new EpMessageHolder();
    }

    public Message buildRocketMqMessage() {
        if (this.payload == null) {
            throw new RuntimeException();
        }
        if (this.payload instanceof Collection) {
            setExtProperty(KEY_IS_COLLECTION, "true");
        } else {
            setExtProperty(KEY_IS_COLLECTION, "false");
        }
        if (this.payload != null) {
            if (this.payload instanceof Collection) {
                setExtProperty(KEY_ENTITY_CLASS, ((Collection) this.payload).iterator().next().getClass().getName());
            } else {
                setExtProperty(KEY_ENTITY_CLASS, this.payload.getClass().getName());
            }
        }
//        if(EpSysEnv.DEV.equals(EpSysEnv.getCurrentSysEnv())
//            ||EpSysEnv.TEST.equals(EpSysEnv.getCurrentSysEnv())){
//            //开发环境要解决消息被别人消费掉的问题, 测试环境因和开发共用，所以存在会消费掉开发环境的消息
//            try {
//                setExtProperty(KEY_HOST_NAME, InetAddress.getLocalHost().getHostName()
//                        .replaceAll("\\.","-"));
//            }catch (UnknownHostException e){
//                log.warn("获取本地服务器机器名失败", e);
//            }
//        }
        Message rocketmqMessge = new Message();
        rocketmqMessge.setBody(JSON.toJSONBytes(this.getPayload(), SerializerFeature.SkipTransientField));
        for (Map.Entry<String, String> entry : this.properties.entrySet()) {
            rocketmqMessge.putUserProperty(entry.getKey(), entry.getValue());
        }
        if (StringUtils.isNotBlank(key)) {
            rocketmqMessge.getProperties().put(MessageConst.PROPERTY_KEYS, key);
        }
        if (null != delay) {
            rocketmqMessge.getProperties().put(MessageConst.PROPERTY_DELAY_TIME_LEVEL,
                    String.valueOf(delay.getLevel()));
        }
        return rocketmqMessge;
    }
}
