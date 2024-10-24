package com.eptison.tk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;

/**
 * Date: 2024/10/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Slf4j
public class AccessToken {


    public static String getAccessToken() throws IOException {
        String app_key = "6dt816a6ht3te";
        String app_secret = "a27308a84d7c3b1f9edcca43bde8a54e4b7b3106";
        //需要验证获取
        String auth_code = "ROW_61Z8kgAAAAAaFCcv4Y4SR_5UIaPo8_H8YuTUc8hsMGwG9qCWyM-12ybZgUjdm2Q0uigGkqPjGHh1hikonTSPwx01tzKJg3cK";
        //
        String grant_type = "authorized_code";

        String getAccessTokenUrl = "https://auth.tiktok-shops.com/api/v2/token/get";

        int connectTimeout = 30000;//毫秒
        int readTimeout = 120000;//毫秒

        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("grant_type", grant_type);
        requestParams.put("app_key", app_key);
        requestParams.put("app_secret", app_secret);
        requestParams.put("auth_code", auth_code);

        //获取accessToken
//        String responseString = WebUtils.doGet(getAccessTokenUrl, requestParams, "UTF-8", connectTimeout, readTimeout, null);
        String responseString = HttpUtil.doGetAccessToken(getAccessTokenUrl, requestParams);
        JSONObject reponseNode = JSON.parseObject(responseString);
        log.info("reponseNode:【{}】", reponseNode.toString());

        return null;

    }


    public static String refreshAccessToken() throws IOException {
        String app_key = "6dt816a6ht3te";
        String app_secret = "a27308a84d7c3b1f9edcca43bde8a54e4b7b3106";
        String grant_type = "refresh_token";
        //上一步获取accessToken的接口会返回这个值
        String refresh_token = "ROW_kWo_OwAAAABMBMVU_bn9s6SD6fz96Q17e89z9Y7ROAayQE4ywc7jXTjBK9UwWRs8zIatl06rXy8";

        String refreshAccessTokenUrl = "https://auth.tiktok-shops.com/api/v2/token/refresh";

        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("app_key", app_key);
        requestParams.put("app_secret", app_secret);
        requestParams.put("refresh_token", refresh_token);
        requestParams.put("grant_type", grant_type);

        //获取refreshToken
        String responseString = HttpUtil.doGetAccessToken(refreshAccessTokenUrl, requestParams);
//        String responseString = WebUtils.doGet(refreshAccessTokenUrl, requestParams);
        log.info("reponseNode:【{}】", responseString);

        return null;
    }


}