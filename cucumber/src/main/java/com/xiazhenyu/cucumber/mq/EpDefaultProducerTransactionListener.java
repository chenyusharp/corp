package com.xiazhenyu.cucumber.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * Date: 2022/10/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Slf4j
public class EpDefaultProducerTransactionListener implements TransactionListener {


    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        if (!(arg instanceof EpProducerTransMsgArg)) {
            log.error("args need to be instanceof EpRocketMqProducerTransactionAction");
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        EpProducerTransMsgArg transMsgArg = (EpProducerTransMsgArg) arg;
        transMsgArg.setActionResult(transMsgArg.getAction().executeLocalTransaction(transMsgArg.getTransStatus()));
        return transMsgArg.getTransStatus().getMqTransactionState();
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        return   LocalTransactionState.UNKNOW;
    }
}