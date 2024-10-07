package com.eptison.tk;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.experimental.UtilityClass;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Date: 2024/10/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@UtilityClass
public class HttpUtil {

    private static OkHttpClient client;

    private static int connectTimeout = 30000;//毫秒
    private static int readTimeout = 120000;//毫秒

    private final Object LOCK = new Object();

    private volatile boolean SIGNAL = false;


    private static final String DEFAULT_MEDIA_TYPE = "application/json; charset=utf-8";


    private OkHttpClient getInstance() {
        if (!SIGNAL) {
            synchronized (LOCK) {
                if (!SIGNAL) {
                    client = new OkHttpClient.Builder()
                            .connectTimeout(connectTimeout, TimeUnit.MICROSECONDS)
                            .writeTimeout(readTimeout, TimeUnit.MICROSECONDS)
                            .readTimeout(readTimeout, TimeUnit.MICROSECONDS)
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

    public String doPost(String url, String requestBody) {
        return doPost(url, requestBody, DEFAULT_MEDIA_TYPE);
    }


    public String doPost(String url, String postBody, String mediaType) {
        //需要先获取accessToken
        String accessToken = null;
        try {
            accessToken = AccessToken.getAccessToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MediaType requestMediaType = MediaType.parse(Objects.isNull(mediaType) ? DEFAULT_MEDIA_TYPE : mediaType);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("content-type", requestMediaType.toString())
                .addHeader("x-tts-access-token", accessToken)
                .post(RequestBody.create(requestMediaType, postBody))
                .build();
        try (Response response = getInstance().newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}