package com.xiazhenyu.cucumber.mq;

import org.apache.rocketmq.client.producer.LocalTransactionState;

/**
 * Date: 2022/10/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@FunctionalInterface
public interface EpRocketMqProducerTransactionAction<T> {

    T executeLocalTransaction(final EpRocketMqProducerTransStatus state);

}
