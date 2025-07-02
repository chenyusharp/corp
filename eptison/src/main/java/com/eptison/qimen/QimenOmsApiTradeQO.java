package com.eptison.qimen;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 石明科
 * @since 2020-04-04 10:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QimenOmsApiTradeQO extends EpQimenOmsBaseQO {

    @JSONField(name = "start_modified")
    private String startTime;

    @JSONField(name = "end_modified")
    private String endTime;

    @JSONField(name = "wdt_interface")
    private String wdtInterface;

    @JSONField(name = "tid")
    private String tid;
}
