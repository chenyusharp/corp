package com.xiazhenyu.cucumber.mq.consumer;

import com.xiazhenyu.cucumber.mq.EpConsumeMode;
import com.xiazhenyu.cucumber.mq.EpMqConsumerGroupName;
import com.xiazhenyu.cucumber.mq.EpMqTopic;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * Date: 2022/10/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EpRocketMqConsumerMethod {


    /**
     * 消息主题
     */
    EpMqTopic topic();


    /**
     * 消费者组别名称
     */
    EpMqConsumerGroupName groupName();


    /**
     * 标签表达式
     */
    String tag() default "*";


    /**
     * 消息消费模式，默认为并行消费
     */
    EpConsumeMode consumerMode() default EpConsumeMode.CONCURRENTLY;


    MessageModel mmessageModel() default MessageModel.CLUSTERING;

    int consumeThreadMax() default 64;


    int consumeThreadMin() default 10;

    long consumeTimeout() default 60 * 60 * 1000;

    int queueNum() default 1;


}