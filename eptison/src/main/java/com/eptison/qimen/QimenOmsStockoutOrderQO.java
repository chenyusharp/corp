package com.eptison.qimen;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 奇门-OMS 出库单 QO
 *
 * @author LMyang
 * @date 2020/4/1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QimenOmsStockoutOrderQO extends EpQimenOmsBaseQO {

    /**
     * 状态
     */
    @JSONField(name = "status")
    private Integer status;

    /**
     * 系统订单编号
     */
    @JSONField(name = "src_order_no")
    private String srcOrderNo;

    /**
     * 原始单号
     */
    @JSONField(name = "src_id")
    private Integer srcId;

    /**
     * 出库单号
     */
    @JSONField(name = "stockout_no")
    private String stockoutNo;

    /**
     * 店铺编号
     */
    @JSONField(name = "shop_no")
    private String shopNo;

    /**
     * 仓库编号
     */
    @JSONField(name = "warehouse_no")
    private String warehouseNo;

    @JSONField(name = "wdt_interface")
    private String wdtInterface;
}
