package com.eptison.tk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.eptison.bojun.BojunAPIResponseCommonDTO;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import lombok.experimental.UtilityClass;
import net.sf.cglib.beans.BeanMap;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 2024/10/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@UtilityClass
public class HttpUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);
    private static OkHttpClient client;

    private static int connectTimeout = 30000;//毫秒
    private static int readTimeout = 120000;//毫秒

    private final Object LOCK = new Object();

    private volatile boolean SIGNAL = false;


    private static final String DEFAULT_MEDIA_TYPE = "application/json";

    private static final String DEFAULT_CHARSET = "utf-8";


    private static final String app_key = AccessToken.testAuthPairMap.get(AccessToken.APP_KEY);
    private static final String app_secret = AccessToken.testAuthPairMap.get(AccessToken.APP_SECRET);
    //上一步获取accessToken的接口会返回这个值
//    String refresh_token = "ROW_DTIIkwAAAABulNVucoe3BblXMR_CQ9MvhTx4HNUoJ9EMa-TVIuhf4OSELNyK5Fq8F5AMWkuCFvc";


    private OkHttpClient getInstance() {
        if (!SIGNAL) {
            synchronized (LOCK) {
                if (!SIGNAL) {
                    client = new Builder()
                            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                            .writeTimeout(readTimeout, TimeUnit.SECONDS)
                            .readTimeout(readTimeout, TimeUnit.SECONDS)
                            .build();
                    SIGNAL = true;
                }
            }
        }
        return client;
    }


    public HttpUrl buildHttpUrl(String url, Map<String, String> queryParams) {
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        queryParams.forEach((key, value) -> builder.addQueryParameter(key, String.valueOf(value)));
        return builder.build();
    }


    public String doGet(String url, Headers headers) {
        Request request = new Request.Builder().url(url).headers(headers).build();
        try (Response response = getInstance().newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * https://auth.tiktok-shops.com/api/v2/token/refresh?
     * refresh_token=ROW_DTIIkwAAAABulNVucoe3BblXMR_CQ9MvhTx4HNUoJ9EMa-TVIuhf4OSELNyK5Fq8F5AMWkuCFvc&
     * app_key=6cnctergfpv4c&
     * grant_type=refresh_token&
     * app_secret=8957fd508cd86883b48e92ab7412ef147e743369
     * @param url
     * @param paramsMap
     * @return
     */
    public String doGetAccessToken(String url, Map<String, String> paramsMap) {
        try {
            String httpUrl = buildGetUrl(url, buildQuery(paramsMap, DEFAULT_CHARSET));
            Request.Builder requestBuilder = new Request.Builder().url(httpUrl);
            try (Response response = getInstance().newCall(requestBuilder.build()).execute()) {
                assert response.body() != null;
                return response.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String doGet(String url, TkCommonQO tkCommonQO,Headers headers) {
        try {
            Map<String, String> commonAPIQueryParam = new HashMap<>();
            commonAPIQueryParam.put("app_key", app_key);
            commonAPIQueryParam.put("timestamp", String.valueOf(System.currentTimeMillis()/1000));
            if (tkCommonQO != null){
                Map<String, String> businessQueryParam = BeanMap.create(tkCommonQO);
                commonAPIQueryParam.putAll(businessQueryParam);
            }

            url = buildGetUrl(url, buildQuery(commonAPIQueryParam, DEFAULT_CHARSET));
            HttpUrl httpUrl = HttpUrl.get(url);
            //获取签名
            String signature = Signature.generateSignature(httpUrl, DEFAULT_MEDIA_TYPE, app_secret, null);
            url = url + "&sign=" + signature;
            log.info("get request url:{}", url);
            Request.Builder requestBuilder = new Request.Builder().url(url)
                    .addHeader("content-type", "application/json");

            if (headers != null) {
                final Map<String, List<String>> headersMultimap = headers.toMultimap();
                headersMultimap.forEach((key, value) -> requestBuilder.addHeader(key, value.get(0)));
            }
            log.info("requestBuilder is :{}", JSONObject.toJSONString(requestBuilder.build()));
            try {
                Response response = getInstance().newCall(requestBuilder.build()).execute();
                try {
                    final ResponseBody responseBody = response.body();
                    return JSON.parseObject(responseBody.string()).getJSONObject("data").toJSONString();
                } finally {
                    response.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String doPost(String url, TkCommonQO tkCommonQO, String mediaType, Headers headers) throws IOException {
        Map<String, String> commonAPIQueryParam = new HashMap<>();
        commonAPIQueryParam.put("app_key", app_key);
        commonAPIQueryParam.put("timestamp", String.valueOf(System.currentTimeMillis()/1000));
        url = buildGetUrl(url, buildQuery(commonAPIQueryParam, DEFAULT_CHARSET));
        MediaType requestMediaType = MediaType.parse(Objects.isNull(mediaType) ? DEFAULT_MEDIA_TYPE : mediaType);
        HttpUrl httpUrl = HttpUrl.get(url);
        String jsonRequestParam = JSONObject.toJSONString(tkCommonQO);
        final RequestBody requestBody = RequestBody.create(requestMediaType, jsonRequestParam);
        log.info("request body:{}",requestBody);
        //获取签名
        String signature = Signature.generateSignature(httpUrl, requestMediaType.toString(), app_secret, requestBody);
        url = url + "&sign=" + signature;
        log.info("post request url:{}", url);
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .addHeader("content-type", requestMediaType.toString());
        requestBuilder.post(requestBody);
        if (headers != null) {
            final Map<String, List<String>> headersMultimap = headers.toMultimap();
            headersMultimap.forEach((key, value) -> requestBuilder.addHeader(key, value.get(0)));
        }
        try (Response response = getInstance().newCall(requestBuilder.build()).execute()) {
            String result = response.body().string();
            log.info("response is:{}", result);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private static String buildGetUrl(String strUrl, String query) throws IOException {
        if (StringUtils.isEmpty(query)) {
            return strUrl;
        }
        if (!strUrl.contains("?")) {
            strUrl = strUrl + "?" + query;
        } else if (strUrl.endsWith("&")) {
            strUrl = strUrl + query;
        } else {
            strUrl = strUrl + "&" + query;
        }
        return strUrl;
    }

    public static String buildQuery(Map<String, String> params, String charset) throws IOException {
        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuilder query = new StringBuilder();
        Set<Entry<String, String>> entries = params.entrySet();
        boolean hasParam = false;

        for (Entry<String, String> entry : entries) {
            String name = entry.getKey();
            String value = entry.getValue();
            // 忽略参数名或参数值为空的参数
            if (areNotEmpty(name, value)) {
                if (hasParam) {
                    query.append("&");
                } else {
                    hasParam = true;
                }

                query.append(name).append("=").append(URLEncoder.encode(value, charset));
            }
        }

        return query.toString();
    }


    /**
     * 检查指定的字符串列表是否不为空。
     */
    private static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= StringUtils.isNotBlank(value);
            }
        }
        return result;
    }

}