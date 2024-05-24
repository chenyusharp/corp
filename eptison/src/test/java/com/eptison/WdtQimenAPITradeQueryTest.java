package com.eptison;

import com.alibaba.fastjson.JSON;
import com.eptison.qimen.EpQimenOmsBaseQO;
import com.eptison.qimen.QimenApiTools;
import com.eptison.qimen.WdtClient;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Date: 2024/2/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class WdtQimenAPITradeQueryTest {


    public static void main(String[] args) throws IOException {

//        String apiMethodName = "wdt.vip.api.trade.query";
//        Map<String, Object> wdtMap = new HashMap<>();
//        wdtMap.put("tid", "AD202402120030100040086509");
//        wdtMap.put("page_no", 0);
//        wdtMap.put("page_size", 100);
//        System.out.println(QimenApiTools.excuteNonCrmApiGetResponse(apiMethodName, wdtMap));

        String apiMethodName = "shop_stock_rule_query.php";
//        String apiMethodName = "wdt.goods.brand.query";
//        Map<String, Object> wdtMap = new HashMap<>();
//        wdtMap.put("start_time", "AD202402120030100040086509");
//        wdtMap.put("end_time","2024-05-10 13:00:00");
//        wdtMap.put("page_no", 0);
//        wdtMap.put("page_size", 100);
//        wdtMap.put("status",1);
        EpQimenOmsBaseQO baseQO = new EpQimenOmsBaseQO();
        baseQO.setPageSize(5);
        baseQO.setStartTime("2024-05-15 00:00:00");
        baseQO.setEndTime("2024-05-15 13:00:00");
//        baseQO.setShopName("CHiC PARK天猫旗舰店（奇刻）-CHiC PARK");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("lock_rule_no", "555");
        paramMap.put("lock_rule_name", "555");
//        paramMap.put("shop_no", "eptison2-test");
        paramMap.put("release_reserved_stock", "3");

        List<StockLockDetail> details = new ArrayList<>();
        details.add(new StockLockDetail("EOP005", "DDH004B160", new BigDecimal(3), 0));

        QimenApiTools.excuteNonQimenApiGetReponseNode(apiMethodName,);



        // 测试环境调用
//        paramMap.put("details", details);
//
//        String wdtSid = "apidevnew2";
//        String nonQimenApiKey = "eptison2-test";
//        String nonQimenApiSerect = "123456789";
//        String nonQimenBaseServerUrl = "http://sandbox.wangdian.cn/openapi2/";
//        WdtClient client = new WdtClient(wdtSid, nonQimenApiKey, nonQimenApiSerect, nonQimenBaseServerUrl);
//        HashMap<String, String> objectMap = new HashMap<>();
//        objectMap.put("params", JSON.toJSONString(paramMap));
//        String responseTxt = client.execute(apiMethodName, objectMap);
//        System.out.printf(JSON.parseObject(responseTxt).toJSONString());
//        System.out.println(QimenApiTools.excuteNonQimenApiGetReponseNode(apiMethodName, baseQO));
    }


    @Data
    @AllArgsConstructor
    public static class StockLockDetail {

        /**
         * 仓库编号
         */
        private String warehouse_no;

        /**
         * 商家编码
         */
        private String spec_no;

        /**
         * 锁定数量
         */
        private BigDecimal actual_lock_num;

        /**
         * 删除
         */
        private Integer deleted;


    }


}