package com.xiazhenyu.cucumber.mq;

import org.apache.rocketmq.client.producer.LocalTransactionState;

/**
 * 发送事务型消息，最终事务消息状态结果
 * @author 朱智贤
 * @since 2019-10-24
 */
public class EpRocketMqProducerTransStatus {

    private LocalTransactionState localTransactionState = LocalTransactionState.COMMIT_MESSAGE;

    public EpRocketMqProducerTransStatus commit(){
        this.localTransactionState = LocalTransactionState.COMMIT_MESSAGE;
        return this;
    }

    public EpRocketMqProducerTransStatus rollbak(){
        this.localTransactionState = LocalTransactionState.ROLLBACK_MESSAGE;
        return this;
    }

    LocalTransactionState getMqTransactionState(){
        return localTransactionState;
    }
}
