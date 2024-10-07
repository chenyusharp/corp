package com.eptison.tk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eptison.qimen.WebUtils;
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
        String app_key = "";
        String app_secret = "";
        //需要验证获取
        String auth_code = "";
        //
        String grant_type = "authorized_code";

        String getAccessTokenUrl = "auth.tiktok-shops.com/api/v2/token/get";

        int connectTimeout = 30000;//毫秒
        int readTimeout = 120000;//毫秒

        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("grant_type", grant_type);
        requestParams.put("app_key", app_key);
        requestParams.put("app_secret", app_secret);
        requestParams.put("auth_code", auth_code);

        //获取accessToken
        String responseString = WebUtils.doPost(getAccessTokenUrl, requestParams, "UTF-8", connectTimeout, readTimeout, null);
        JSONObject reponseNode = JSON.parseObject(responseString);
        log.info("reponseNode:【{}】", reponseNode.toString());

        return null;

    }


    public String refreshAccessToken() throws IOException {
        String app_key = "";
        String app_secret = "";
        String grant_type = "refresh_token";
        //上一步获取accessToken的接口会返回这个值
        String refresh_token = "";

        String refreshAccessTokenUrl = "auth.tiktok-shops.com/api/v2/token/refresh";

        int connectTimeout = 30000;//毫秒
        int readTimeout = 120000;//毫秒

        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("grant_type", grant_type);
        requestParams.put("app_key", app_key);
        requestParams.put("app_secret", app_secret);
        requestParams.put("refresh_token", refresh_token);

        //获取refreshToken
        String responseString = WebUtils.doPost(refreshAccessTokenUrl, requestParams, "UTF-8", connectTimeout, readTimeout, null);
        JSONObject reponseNode = JSON.parseObject(responseString);
        log.info("reponseNode:【{}】", reponseNode.toString());

        return null;
    }










}