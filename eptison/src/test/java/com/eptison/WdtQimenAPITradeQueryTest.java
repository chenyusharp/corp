package com.eptison;

import com.alibaba.fastjson.JSON;
import com.eptison.qimen.EpQimenOmsBaseQO;
import com.eptison.qimen.QimenApiTools;
import com.eptison.qimen.WdtClient;
import com.google.common.collect.Lists;
import com.taobao.api.ApiException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
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


    public static void main(String[] args) throws IOException, ApiException {

//        testForTradeQuery();
//
//        shopStockRuleQuery();
//
//        shopStockQuery();

//        stockQuery();

        refundQuery();

        //查询货品档案
//        goodsQuery();
    }


    public static void testForTradeQuery() throws IOException, ApiException {
        String apiMethodName = "wdt.vip.api.trade.query";
        Map<String, Object> wdtMap = new HashMap<>();
        wdtMap.put("tid", "2293905147874279592");
        wdtMap.put("page_no", 0);
        wdtMap.put("page_size", 100);
        System.out.println(QimenApiTools.excuteNonCrmApiGetResponse(apiMethodName, wdtMap, false));
    }

    public static void shopStockRuleQuery() throws IOException, ApiException {
        //        String apiMethodName = "shop_stock_rule_query.php";
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
    }


    public static void shopStockQuery() throws IOException {
        // 测试环境调用
        String apiMethodName = "shop_stock_query.php";
        String wdtSid = "apidevnew2";
        String nonQimenApiKey = "eptison2-test";
        String nonQimenApiSerect = "123456789";
        String nonQimenBaseServerUrl = "http://sandbox.wangdian.cn/openapi2/";
        WdtClient client = new WdtClient(wdtSid, nonQimenApiKey, nonQimenApiSerect, nonQimenBaseServerUrl);
        HashMap<String, String> objectMap = new HashMap<>();
//        objectMap.put("params", JSON.toJSONString(paramMapFotTest));
//        String responseTxt = client.execute(apiMethodName, objectMap);
        EpQimenOmsBaseQO omsBaseQO = new EpQimenOmsBaseQO();
        omsBaseQO.setStartTime("2024-08-15 04:00:00");
        omsBaseQO.setEndTime("2024-08-22 04:00:00");
        omsBaseQO.setPageNo(0);
        omsBaseQO.setPageSize(30);
//        System.out.printf(JSON.parseObject(responseTxt).toJSONString());
        System.out.println(QimenApiTools.excuteNonQimenApiGetReponseNode(apiMethodName, omsBaseQO, false));
    }


    public static void stockQuery() throws IOException {
        EpQimenOmsBaseQO omsBaseQO = new EpQimenOmsBaseQO();
        omsBaseQO.setStartTime("2024-07-09 08:50:05");
        omsBaseQO.setEndTime("2024-07-09 08:50:02");
        omsBaseQO.setPageSize(100);
        QimenApiTools.excuteNonQimenApiWithAutoRetry("stock_query.php", omsBaseQO, "stocks", false);
    }


    public static void refundQuery() throws IOException, ApiException {
        ExecutorService threadPool = Executors.newFixedThreadPool(30);
        for (int i = 0; i < 1; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                String apiMethodName = "wdt.refund.query";
                Map<String, Object> wdtMap = new HashMap<>();
//                wdtMap.put("start_time", "2024-09-18 08:56:08");
//                wdtMap.put("end_time", "2024-09-20 10:56:08");
                wdtMap.put("tid","AT202411080003");
                try {
                    System.out.println("第"+ finalI
                            +"个线程执行结果："+QimenApiTools.excuteNonCrmApiGetResponse(apiMethodName, wdtMap, true));
                } catch (ApiException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        threadPool.shutdown();
    }



    public  static  void stockOutQuery() throws IOException {

        EpQimenOmsBaseQO omsBaseQO = new EpQimenOmsBaseQO();
        omsBaseQO.setStartTime("2024-07-09 08:50:05");
        omsBaseQO.setEndTime("2024-07-09 08:50:02");
        omsBaseQO.setStockoutNo("CK240816000015");
        omsBaseQO.setPageSize(100);
        QimenApiTools.excuteNonQimenApiWithAutoRetry("stock_query.php", omsBaseQO, "stocks", true);

    }


    /**
     * 查询oms货品档案信息
     * @throws IOException
     */
    public static  void goodsQuery() throws IOException {
        EpQimenOmsBaseQO omsBaseQO = new EpQimenOmsBaseQO();
        omsBaseQO.setStartTime("2024-06-04 08:50:05");
        omsBaseQO.setEndTime("2024-06-05 18:50:02");
        omsBaseQO.setSpecNo("EDW033Z160");
        omsBaseQO.setPageSize(100);
        System.out.println(QimenApiTools.excuteNonQimenApiWithAutoRetry("goods_query.php", omsBaseQO, "goods_list", true));
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