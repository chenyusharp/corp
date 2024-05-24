package com.eptison.qimen;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * Created on 2020-04-02.
 *
 */
public class WdtClient {

    private String appkey;
    private String appsecret;
    private String sid;
    private String baseUrl;

    private int connectTimeout = 30000;//毫秒
    private int readTimeout = 120000;//毫秒

    public WdtClient(String sid, String appkey, String appsecret, String baseUrl)
    {
        this.sid = sid;
        this.appkey = appkey;
        this.appsecret = appsecret;
        this.baseUrl = baseUrl;

        if(!this.baseUrl.endsWith("/"))
            this.baseUrl = this.baseUrl+"/";
    }

    public void setTimeout(int connectTimeout, int readTimeout)
    {
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    /**
     * 给TOP请求签名。
     *
     * @param appsecret 签名密钥
     * @return 签名
     * @throws IOException
     */
    public static String signRequest(Map<String, String> params, String appsecret) {
        // 第一步：检查参数是否已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        for (String key : keys) {
            if("sign".equals(key))
                continue;

            if(query.length() > 0)
                query.append(';');

            int len = key.length();
            query.append(String.format("%02d", len)).append('-').append(key).append(':');

            String value = params.get(key);

            len = value.length();
            query.append(String.format("%04d", len)).append('-').append(value);

        }

        query.append(appsecret);

        // 第三步：使用MD5加密,转化为大写的十六进制
        return Md5Util.getMd5(query.toString());
    }

    public String execute(String relativeUrl, Map<String, String> params) throws IOException {

        params.put("appkey", this.appkey);
        params.put("sid", this.sid);
        params.put("timestamp", Long.toString(System.currentTimeMillis()/1000));
        params.put("sign", signRequest(params, this.appsecret));

        return WebUtils.doPost(this.baseUrl + relativeUrl, params, "UTF-8", connectTimeout, readTimeout, null);

    }
}
