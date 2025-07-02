package com.eptison.chicpark;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Date: 2025/2/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ChunTaiHaiDing {


    private static final String request_url = "http://saas.dc.hdkj123.com:8000/pod-open/s/v1/trade/savenew";

    private static final String bloc = "0000";

    private static final String accessKey = "085f3295-83c0-4cc4-8b6b-2998b610";

    public static final String accessSecret = "cec00a3b-7770-41ef-8ef1-e1ae5271";

    public static final String path = "/pod-open/s/v1/trade/savenew";

    public static void main(String[] args) {

        OkHttpClient client = new Builder()
                .connectTimeout(30000, TimeUnit.SECONDS)
                .writeTimeout(120000, TimeUnit.SECONDS)
                .readTimeout(120000, TimeUnit.SECONDS)
                .build();
        long timestamp = System.currentTimeMillis();
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("bloc", "0000");
        requestParam.put("accessKey", accessKey);
//        requestParam.put("accessSecret", accessSecret);
        requestParam.put("timestamp", timestamp);
        requestParam.put("sign",toHexString(md5(path + bloc + accessKey + timestamp + accessSecret)));

        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setCollectionTerminalNo("chicpark");
        tradeInfo.setTicketNumber("CXLOF2025021802");
        tradeInfo.setTradeTime("2025-02-18 00:00:00");
        tradeInfo.setTradeType("RTN");
        tradeInfo.setTradeAmount(-100);
        tradeInfo.setQty(-1);
        ArticleItem ai = new ArticleItem();
        ai.setAmount(-100);
        ai.setQty(-1);
        ai.setName("服装");
        ai.setPrice(100);
        tradeInfo.setArticleItems(Lists.newArrayList(ai));
        // 业务对象
        String bizNessJsonStr = JSON.toJSONString(tradeInfo);

        // 设置请求的媒体类型为 JSON
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        // 创建 RequestBody 并设置 JSON 数据
        RequestBody requestBody = RequestBody.create(mediaType, bizNessJsonStr);

        String postPrefixUrl = Joiner.on("&").withKeyValueSeparator("=").join(requestParam);
        System.out.println(request_url + "?" + postPrefixUrl);
        Request.Builder requestBuilder = new Request.Builder()
                .url(request_url + "?" + postPrefixUrl)
                .addHeader("content-type", "application/json; charset=utf-8");
        requestBuilder.post(requestBody);
        try (Response response = client.newCall(requestBuilder.build()).execute()) {
            String result = response.body().string();
            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * md5
     */
    public static String md5(String string) {
        if (string == null) {
            return null;
        }
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        byte[] btInput = string.getBytes();
        try {
            /** 获得MD5摘要算法的 MessageDigest 对象 */
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            /** 使用指定的字节更新摘要 */
            mdInst.update(btInput);
            /** 获得密文 */
            byte[] md = mdInst.digest();
            /** 把密文转换成十六进制的字符串形式 */
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * 字符串转16进制
     */
    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }


}