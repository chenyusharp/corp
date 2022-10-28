package com.xiazhenyu.cucumber.mq;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Date: 2022/10/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Data
@AllArgsConstructor
public class EpProducerTransMsgArg<T> {

    private EpRocketMqProducerTransactionAction<T> action;

    private T actionResult;

    private EpRocketMqProducerTransStatus transStatus;


}