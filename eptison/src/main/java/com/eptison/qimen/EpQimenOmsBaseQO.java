package com.eptison.qimen;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;
import lombok.Data;

/**
 * 奇门旺店通OMS请求参数QO基类，暂时存放分页相关数据
 *
 * @author 朱智贤
 * @since 2019-08-13
 */
@Data
public class EpQimenOmsBaseQO {

    /** 购买ERP时由旺店通分配给ERP购买方，请从ERP购买方获取 */
    private String sid;

    /** 旺店通给的appKey, 暂时同奇门appkey */
    private String appkey;

    @JSONField(name = "page_no")
    private int pageNo;

    @JSONField(name = "page_size")
    private int pageSize = 10;


    @JSONField(name = "end_time")
    private String endTime;

    @JSONField(name = "start_time")
    private String startTime;


    @JSONField(name = "shop_name")
    private String shopName;


    @JSONField(name = "shop_no")
    private String shopNo;


    @JSONField(name = "limit")
    private Integer limit;


    /**
     * 出库单号
     */
    @JSONField(name = "stockout_no")
    private String stockoutNo;


    /**
     * 商家编码
     */
    @JSONField(name = "spec_no")
    private String specNo;


    @JSONField(name = "api_goods_info")
    private GoodsInfo apiGoodsInfo;


    @JSONField(name = "stock_sync_list")
    private List<StockSyncAck> stockSyncList;

    @Data
    public static class GoodsInfo {

        @JSONField(name = "platform_id")
        private Integer platformId;

        @JSONField(name = "shop_no")
        private String shopNo;

        @JSONField(name = "goods_list")
        private List<GoodsSpec> goodsSpecList;

    }

    @Data
    public static class GoodsSpec {


        @JSONField(format = "goods_id")
        private String goodsId;

        @JSONField(format = "spec_id")
        private String specId;

        @JSONField(format = "goods_no")
        private String goodsNo;

        @JSONField(format = "spec_no")
        private String specNo;

        @JSONField(format = "status")
        private Integer status = 1;
    }


    @Data
    public static class StockSyncAck {

        @JSONField(name = "rec_id")
        private Long recId;

        @JSONField(name = "sync_stock")
        private Integer syncStock;

        @JSONField(name = "stock_change_count")
        private Integer stockChangeCount;

    }

}
