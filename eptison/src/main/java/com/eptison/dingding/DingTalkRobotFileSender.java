package com.eptison.dingding;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.file.IOUtils;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiChatCreateRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiV2UserGetbymobileRequest;
import com.dingtalk.api.response.OapiChatCreateResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiV2UserGetbymobileResponse;
import com.taobao.api.ApiException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DingTalkRobotFileSender {
    //https://oapi.dingtalk.com/robot/send?access_token=5567e0bc1f79de630abb14fce307823e29483f5b7b7caa3b0ee4fe2669cda015

    // 钉钉机器人 Webhook 地址
    private static final String WEBHOOK_URL = "https://oapi.dingtalk.com/robot/send?access_token=584d57716efab93f011fbd60e310a3741c50f9788fc6c980af278aa8b8504ef6";
    private static final Logger log = LoggerFactory.getLogger(DingTalkRobotFileSender.class);
    // 钉钉文件上传接口地址
    private static String UPLOAD_URL = "https://oapi.dingtalk.com/media/upload?access_token={}&type=file";

    public static void main(String[] args) {
        // 要发送的 Excel 文件路径
        String filePath = "/Users/xiazhenyu/Desktop/建议移库.xlsx";
        try {
            // 上传文件并获取 media_id
            String mediaId = uploadFile(filePath);
            log.info("mediaId:{}", mediaId);
            if (mediaId != null) {
                // 发送文件消息
                sendFileMessage(mediaId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        final String appAccessToken = "ec5a6c1629f434d4ad9944a4c12f8bfd";
//        String userId = getUserDingDingIdByMobile("18239576394", appAccessToken);
//        log.info("userId:{}", userId);
//        createOpenConversationId(appAccessToken);
    }

    public static void createOpenConversationId(String accessToken) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/chat/create");
        OapiChatCreateRequest req = new OapiChatCreateRequest();
        req.setName("企业内部群测试");
        req.setOwner("16562928831228833");
        req.setUseridlist(Arrays.asList("16562928831228833", "17403602730392271"));
        req.setShowHistoryType(1L);
        req.setSearchable(0L);
        req.setValidationType(0L);
        req.setMentionAllAuthority(0L);
        req.setManagementType(0L);
        req.setChatBannedType(0L);
        OapiChatCreateResponse rsp = null;
        try {
            rsp = client.execute(req, accessToken);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        System.out.println(rsp.getBody());
    }

    /**
     * 上传文件到钉盘
     * @param filePath 文件路径
     * @return media_id
     * @throws IOException 异常
     */
    private static String uploadFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件不存在: " + filePath);
            return null;
        }
        final String appAccessToken = getAppAccessToken("dingti7cqstkr84s3jue", "Yuvbbm5egDsnXFvgN0M-oj50eNHod2tujInZc3plWc6hSCO6YLP2nGw3-vyxYS50");
        log.info("appAccessToken:{}", appAccessToken);
        UPLOAD_URL = UPLOAD_URL.replace("{}", appAccessToken);
        URL url = new URL(UPLOAD_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);

        String boundary = UUID.randomUUID().toString();
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        try (InputStream inputStream = new FileInputStream(file)) {
            byte[] fileBytes = IOUtils.toByteArray(inputStream);
            StringBuilder postData = new StringBuilder();
            postData.append("--").append(boundary).append("\r\n");
            postData.append("Content-Disposition: form-data; name=\"media\"; filename=\"").append(file.getName()).append("\"\r\n");
            postData.append("Content-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet\r\n");
            postData.append("\r\n");

            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
            byte[] endData = ("\r\n--" + boundary + "--\r\n").getBytes("UTF-8");

            connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length + fileBytes.length + endData.length));
            connection.getOutputStream().write(postDataBytes);
            connection.getOutputStream().write(fileBytes);
            connection.getOutputStream().write(endData);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String response = IOUtils.toString(connection.getInputStream(), "UTF-8");
                JSONObject json = JSONObject.parseObject(response);
                if (json.getIntValue("errcode") == 0) {
                    return json.getString("media_id");
                } else {
                    System.out.println("文件上传失败: " + json.toString());
                }
            } else {
                System.out.println("文件上传请求失败，响应码: " + responseCode);
            }
        }
        return null;
    }

    /**
     * 发送文件消息
     * @param mediaId 文件的 media_id
     * @throws IOException 异常
     */
    private static void sendFileMessage(String mediaId) throws IOException {
        URL url = new URL(WEBHOOK_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        Map<String, Object> fileMessage = new HashMap<>();
        fileMessage.put("msgtype", "file");
        Map<String, String> file = new HashMap<>();
        file.put("media_id", mediaId);
        fileMessage.put("file", file);

        String jsonMessage = new JSONObject(fileMessage).toString();
        connection.getOutputStream().write(jsonMessage.getBytes("UTF-8"));

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String response = IOUtils.toString(connection.getInputStream(), "UTF-8");
            JSONObject json = JSONObject.parseObject(response);
            if (json.getIntValue("errcode") == 0) {
                System.out.println("文件消息发送成功");
            } else {
                System.out.println("文件消息发送失败: " + json.toString());
            }
        } else {
            System.out.println("文件消息发送请求失败，响应码: " + responseCode);
        }
    }

    private static String getAppAccessToken(String appKey, String appSecret) {
        // 先从redis中获取
        //获取企业accessToken
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest tokenReq = new OapiGettokenRequest();
        tokenReq.setAppkey(appKey);
        tokenReq.setAppsecret(appSecret);
        tokenReq.setHttpMethod("GET");
        OapiGettokenResponse response = null;
        try {
            response = client.execute(tokenReq);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        if (!response.isSuccess()) {
            return null;
        }
        return response.getAccessToken();
    }

    private static String getUserDingDingIdByMobile(String mobile, String accessToken) {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/getbymobile");
            OapiV2UserGetbymobileRequest req = new OapiV2UserGetbymobileRequest();
            req.setMobile(mobile);
            OapiV2UserGetbymobileResponse response = client.execute(req, accessToken);
            if (!response.isSuccess()) {
                log.error("EpDingdingUtil getUserIdByMobile：mobile：{} response：{}", mobile, response);
                return null;
            }
            return response.getResult().getUserid();
        } catch (ApiException e) {
            log.error("EpDingdingUtil getUserIdByMobile：mobile：{}  error", mobile, e);
        }
        return null;
    }
}