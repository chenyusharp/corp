package com.eptison.tk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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


    public static Map<String, String> testAuthPairMap = new HashMap<>();
    public static final String APP_KEY = "app_key";
    public static final String APP_SECRET = "app_secret";
    public static final String ACCESS_TOKEN = "access_token";

    static {
        testAuthPairMap.put(APP_KEY, "6cnctergfpv4c");
        testAuthPairMap.put(APP_SECRET, "8957fd508cd86883b48e92ab7412ef147e743369");
        testAuthPairMap.put(ACCESS_TOKEN, "ROW_8J_22wAAAAD8YDnyiVK7IfEIkutWul5cjip7td6EP1SJQAgrTHg6gHHP-ItwZTbl2hKV2IBPnfZewrw0FfjKU9QutWk23Nlyvs7qrAYnARCw6YkcyaZFkg");
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getAccessToken());
    }

    public static String getAccessToken() throws IOException {
        String app_key = "6dt816a6ht3te";
        String app_secret = "a27308a84d7c3b1f9edcca43bde8a54e4b7b3106";
        //需要验证获取
        String auth_code = "ROW_mPZJYQAAAAAaFCcv4Y4SR_5UIaPo8_H88milKFEFs-N4tublxfF4cEIElqjHyq-jTgdEGi6QQdcIoKo8QLv1877WFnSMDfHq";
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
        String grant_type = "refresh_token";
        //上一步获取accessToken的接口会返回这个值
        String refresh_token = "ROW_DTIIkwAAAABulNVucoe3BblXMR_CQ9MvhTx4HNUoJ9EMa-TVIuhf4OSELNyK5Fq8F5AMWkuCFvc";

        String refreshAccessTokenUrl = "https://auth.tiktok-shops.com/api/v2/token/refresh";

        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("app_key", testAuthPairMap.get(APP_KEY));
        requestParams.put("app_secret", testAuthPairMap.get(APP_SECRET));
        requestParams.put("refresh_token", refresh_token);
        requestParams.put("grant_type", grant_type);

        //获取refreshToken
        String responseString = HttpUtil.doGetAccessToken(refreshAccessTokenUrl, requestParams);
//        String responseString = WebUtils.doGet(refreshAccessTokenUrl, requestParams);
        log.info("reponseNode:【{}】", responseString);

        return null;
    }


}