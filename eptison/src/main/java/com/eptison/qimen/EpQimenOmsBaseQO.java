package com.eptison.qimen;

import com.alibaba.fastjson.annotation.JSONField;
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



}
