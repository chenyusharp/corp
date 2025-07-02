package com.eptison;

import com.alibaba.fastjson.JSONObject;
import com.eptison.qimen.EpQimenOmsBaseQO;
import com.eptison.qimen.EpQimenOmsBaseQO.GoodsInfo;
import com.eptison.qimen.EpQimenOmsBaseQO.GoodsSpec;
import com.eptison.qimen.EpQimenOmsBaseQO.StockSyncAck;
import com.eptison.qimen.QimenApiTools;
import com.eptison.qimen.QimenOmsApiTradeQO;
import com.eptison.qimen.QimenOmsStockoutOrderQO;
import com.eptison.qimen.WdtClient;
import com.google.common.collect.Lists;
import com.taobao.api.ApiException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.sf.cglib.beans.BeanMap;

/**
 * Date: 2024/2/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class WdtQimenAPITradeQueryTest {


    public static void main(String[] args) throws IOException, ApiException {

        testForTradeQuery();
//
//        shopStockRuleQuery();
//
//        shopStockQuery();

//        stockQuery();

//        refundQuery();

        //查询货品档案
//        goodsQuery();

        //创建平台货品
//        createApiGoodsSpec();

        //查询平台货品
//        apiGoodsChangeQuery();

        //同步平台货品回写
//        apiStockChangeAck();

        //查询原始退款单
//        vipApiRefundQuery();

        //原始订单
//        testForCrmApiTradeQuery();

        //查询oms非销售出库单
//        queryUnSalesStockoutOrder();

        //查询销售出库单
//        querySalesStockoutOrder();

        //库存详情查询接口
//        stockDetailQuery();
    }


    public static void testForTradeQuery() throws IOException, ApiException {
        String apiMethodName = "wdt.vip.api.trade.query";
        Map<String, Object> wdtMap = new HashMap<>();
        wdtMap.put("tid", "2517177937205381352");
        wdtMap.put("page_no", 0);
        wdtMap.put("page_size", 100);
        System.out.println(QimenApiTools.excuteNonCrmApiGetResponse(apiMethodName, wdtMap, false));
    }



    public static void testForCrmApiTradeQuery() throws IOException, ApiException {

        QimenOmsApiTradeQO queryQO=new QimenOmsApiTradeQO();
        queryQO.setPageNo(5);
        queryQO.setPageSize(100);
        queryQO.setWdtInterface("trade_order");
//        queryQO.setTid("250118-153815641651903");
        queryQO.setStartTime("2025-01-18 21:40:00");
        queryQO.setEndTime("2025-01-18 21:50:44");
        QimenApiTools.excuteCrmApiWithAutoRetry(queryQO);
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
//        baseQO.setPageSize(5);
        baseQO.setStartTime("2024-05-15 00:00:00");
        baseQO.setEndTime("2024-05-15 13:00:00");
//        baseQO.setShopName("CHiC PARK天猫旗舰店（奇刻）-CHiC PARK");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("lock_rule_no", "555");
        paramMap.put("lock_rule_name", "555");
//        paramMap.put("shop_no", "eptison2-test");
        paramMap.put("release_reserved_stock", "3");

        List<StockLockDetail> details = new ArrayList<>();
//        details.add(new StockLockDetail("EOP005", "DDH004B160", new BigDecimal(3), 0));
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
                wdtMap.put("tid", "2370917390775197377");
                try {
                    System.out.println("第" + finalI + "个线程执行结果：" + QimenApiTools.excuteNonCrmApiGetResponse(apiMethodName, wdtMap, false));
                } catch (ApiException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        threadPool.shutdown();
    }


    public static void vipApiRefundQuery() throws ApiException {
        String apiMethodName = "wdt.vip.api.refund.query";
        Map<String, Object> wdtMap = new HashMap<>();
//        wdtMap.put("start_time", "2024-12-01 17:01:03");
//        wdtMap.put("end_time", "2024-12-06 17:01:03");
//        wdtMap.put("tid", "XS20210528013.B");
//        wdtMap.put("refund_no", "XS20210528005.B");
        wdtMap.put("refund_no","B2BTH2021012800041");
        String qimenResponse = QimenApiTools.excuteNonCrmApiGetResponse(apiMethodName, wdtMap, true);
        System.out.println(qimenResponse);
    }


    public static void createApiGoodsSpec() throws IOException, ApiException {
        String apiMethodName = "api_goodsspec_push.php";
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setPlatformId(127);
        goodsInfo.setShopNo("eptison2-test");

        GoodsSpec goodsSpec = new GoodsSpec();
        goodsSpec.setGoodsId("1144095");
        goodsSpec.setSpecId("3327448");
        goodsSpec.setGoodsNo("EDE011");
        goodsSpec.setSpecNo("EDE011B175");
        goodsSpec.setStatus(1);
        goodsInfo.setGoodsSpecList(Lists.newArrayList(goodsSpec));
        EpQimenOmsBaseQO omsBaseQO = new EpQimenOmsBaseQO();
        omsBaseQO.setApiGoodsInfo(goodsInfo);
        Map<String, Object> objectMap = BeanMap.create(omsBaseQO);
        String response = QimenApiTools.excuteNonCrmApiGetResponse(apiMethodName, objectMap, true);
        System.out.println(response);
    }


    public static void apiGoodsChangeQuery() throws IOException {
        String apiMethodName = "api_goods_stock_change_query.php";
        EpQimenOmsBaseQO omsBaseQO = new EpQimenOmsBaseQO();
        omsBaseQO.setShopNo("20241113001");
        omsBaseQO.setLimit(100);
        JSONObject jsonObject = QimenApiTools.excuteNonQimenApiGetReponseNode(apiMethodName, omsBaseQO, false);
        System.out.println(jsonObject);
    }


    public static void apiStockChangeAck() throws IOException {
        String apiMethodName = "api_goods_stock_change_ack.php";
        EpQimenOmsBaseQO omsBaseQO = new EpQimenOmsBaseQO();
        List<StockSyncAck> stockSyncAckList = new ArrayList<>();
        StockSyncAck stockSyncAck = new StockSyncAck();
        stockSyncAck.setRecId(44079596L);
        stockSyncAck.setSyncStock(14);
        stockSyncAck.setStockChangeCount(14);
        stockSyncAckList.add(stockSyncAck);
        omsBaseQO.setStockSyncList(stockSyncAckList);
        JSONObject jsonObject = QimenApiTools.excuteNonQimenApiGetReponseNode(apiMethodName, omsBaseQO, true);
        System.out.println(jsonObject);
    }

    public static void stockOutQuery() throws IOException {

        EpQimenOmsBaseQO omsBaseQO = new EpQimenOmsBaseQO();
        omsBaseQO.setStartTime("2024-07-09 08:50:05");
        omsBaseQO.setEndTime("2024-07-09 08:50:02");
        omsBaseQO.setStockoutNo("CK240816000015");
        omsBaseQO.setPageSize(100);
        QimenApiTools.excuteNonQimenApiWithAutoRetry("stock_query.php", omsBaseQO, "stocks", true);

    }



    public static void queryUnSalesStockoutOrder() throws ApiException {
        String apiMethodName = "wdt.stockout.order.query";
        Map<String, Object> wdtMap = new HashMap<>();
        wdtMap.put("start_time", "2025-02-04 20:20:37");
        wdtMap.put("end_time", "2025-02-04 21:40:37");
//        wdtMap.put("stockout_no", "CK202502047152");
        wdtMap.put("src_order_no", "JY2025020410840");
        wdtMap.put("page_no", 0);
        wdtMap.put("page_size", 100);
        String qimenResponse = QimenApiTools.excuteNonCrmApiGetResponse(apiMethodName, wdtMap, false);
        System.out.println(qimenResponse);
    }


    public static void querySalesStockoutOrder() throws ApiException {
        QimenOmsStockoutOrderQO qo = new QimenOmsStockoutOrderQO();
        qo.setWdtInterface("stockout");
        qo.setPageNo(1);
        qo.setPageSize(100);
        qo.setStartTime("2025-02-05 13:17:37");
        qo.setEndTime("2025-02-05 13:23:38");
        System.out.println(QimenApiTools.excuteCrmApiWithAutoRetry(qo));
    }




    public  static  void stockDetailQuery() throws IOException {
        String apiMethodName = "stock_query_detail.php";
        EpQimenOmsBaseQO omsBaseQO = new EpQimenOmsBaseQO();
        omsBaseQO.setSpecNo("FDC063Y165");
        omsBaseQO.setPageNo(0);
        omsBaseQO.setPageSize(100);
        omsBaseQO.setWarehouseNo("EPHZC");
        JSONObject jsonObject = QimenApiTools.excuteNonQimenApiGetReponseNode(apiMethodName, omsBaseQO, false);
        System.out.println(jsonObject);
    }


    /**
     * 查询oms货品档案信息
     * @throws IOException
     */
    public static void goodsQuery() throws IOException {
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