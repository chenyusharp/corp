package com.xiazhenyu.cucumber.mq;

import lombok.Data;

/**
 * 基于所属人员权限做的扩展类, 后续有需要的可以往这个中间类里面加
 *
 * @author 石明科
 * @since 2020-07-03 16:45
 */
@Data
public class EpBasePayableDTO {

    /**
     * 对账人员id
     */
    private Long payableUserId;
}
