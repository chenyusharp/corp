package com.eptison.qimen;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eptison.tools.EpPairV2;
import com.google.common.base.CaseFormat;
import com.qimencloud.api.DefaultQimenCloudClient;
import com.qimencloud.api.QimenCloudRequest;
import com.qimencloud.api.QimenCloudResponse;
import com.taobao.api.ApiException;
import com.taobao.api.Constants;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.cglib.beans.BeanMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 2024/2/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class QimenApiTools {


    private static String qimenNonCrmRouterServerUrl = "https://hu3cgwt0tc.api.taobao.com/router/qm";

    private static String apiKey = "28792411";

    private static String apiSerect = "3adb40e15f1d77d4524ea085d6413c2f";

    private static String wdtTargetAppKey = "21363512";

    private static String wdtSid = "eptison2";


    private static final String FLAG = "failure";

    private static String nonQimenApiKey = "eptison2-ot";


    public static String nonQimenApiSerect = "f7d79847b6200327aa3fc5551dc9bde9";


    private static String nonQimenBaseServerUrl = "https://api.wangdian.cn/openapi2/";


    private static int outLimitWaitTimeGap = 31000;

    private static int outLimitRetryMax = 3;

    // 奇门错误码 https://open.taobao.com/doc.htm?spm=a219a.7386797.0.0.7c7e669aDpKVnE&source=search&docId=101645&docType=1
    /**
     * 奇门接口调用成功
     */
    private static final String QIMEN_ERROR_CODE_OK = "0";

    //调用查询预约入库单接口使用 采购单号 或者 外部采购单号 直接查询
    //如果OMS查询不到则会 返回code  7200 和7201
    private static final String OMS_ERROR_CODE_7200 = "7200";
    private static final String OMS_ERROR_CODE_7201 = "7201";


    /**
     * 例如:2018-12-28 10:00:00
     */
    public static final String DATE_TIME_SECOUND = "yyyy-MM-dd HH:mm:ss";


    private static final String WDT_API_ERROR_CODE_BUSY = "1012";

    private static final String NODE_RESP_NAME = "response";

    private static final String MESSAGE = "message";


    private static Logger log = LoggerFactory.getLogger(QimenApiTools.class.getSimpleName());

    private static EpPairV2<DefaultQimenCloudClient, QimenCloudRequest> getNonCrmApiRequest(String apiMethod) {
        DefaultQimenCloudClient client = new DefaultQimenCloudClient(qimenNonCrmRouterServerUrl, apiKey, apiSerect, Constants.FORMAT_JSON);
        QimenCloudRequest request = new QimenCloudRequest();
        request.setApiMethodName(apiMethod);
        request.setTargetAppKey(wdtTargetAppKey);//注意！ 千万不能少了这
        return new EpPairV2<>(client, request);
    }


    public static QimenCloudResponse getQimenCloudResponse(String apiMethodName, EpPairV2<DefaultQimenCloudClient, QimenCloudRequest> reqPair) throws ApiException {
        reqPair.getRight().addQueryParam("sid", wdtSid);//注意！ 千万不能少了这
        QimenCloudResponse response;
        long performanceStart = System.currentTimeMillis();
        response = reqPair.getLeft().execute(reqPair.getRight());
        log.warn("奇门非CRM接口{} 调用耗时 {}ms", apiMethodName, System.currentTimeMillis() - performanceStart);
        if (FLAG.equals(response.getFlag())) {
            throw new RuntimeException(String.format("请求OMS奇门接口失败,失败原因【%s】", response.getSubMsg()));
        }
        return response;
    }

    public static String excuteNonCrmApiGetResponse(String apiMethodName, Map<String, Object> paramMap) throws ApiException {
        EpPairV2<DefaultQimenCloudClient, QimenCloudRequest> reqPair = getNonCrmApiRequest(apiMethodName);
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            if (null != entry.getValue()) {
                Object value = entry.getValue();
                if (value instanceof Date) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_SECOUND);
                    value = simpleDateFormat.format(value);
                }
                reqPair.getRight().addQueryParam(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entry.getKey()), String.valueOf(value));
            }
        }
        QimenCloudResponse response = getQimenCloudResponse(apiMethodName, reqPair);
        JSONObject reponseNode = JSON.parseObject(response.getBody());
        return reponseNode.getString(NODE_RESP_NAME);
    }


    public static <T> T excuteNonCrmApiGetResponse(String apiMethodName, Object object, Class<T> dtoClass) throws ApiException {
        EpPairV2<DefaultQimenCloudClient, QimenCloudRequest> reqPair = getNonCrmApiRequest(apiMethodName);
        Map<String, Object> objectMap = BeanMap.create(object);
        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            if (null != entry.getValue()) {
                Object value = entry.getValue();
                if (value instanceof Date) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_SECOUND);
                    value = simpleDateFormat.format(value);
                }
                reqPair.getRight().addQueryParam(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entry.getKey()), String.valueOf(value));
            }
        }
        QimenCloudResponse response = getQimenCloudResponse(apiMethodName, reqPair);
        JSONObject reponseNode = JSON.parseObject(response.getBody());
        String string = reponseNode.getString(NODE_RESP_NAME);
        return JSON.parseObject(string, dtoClass);
    }


    public <T> List<T> excuteNonQimenApiWithAutoRetry(String apiMethodName, EpQimenOmsBaseQO wdtQO, String dataListNodeName, Class<T> dtoClass) throws IOException {
        JSONObject reponseNode = this.excuteNonQimenApiGetReponseNode(apiMethodName, wdtQO);
        return JSON.parseArray(reponseNode.getString(dataListNodeName), dtoClass);
    }


    public static JSONObject excuteNonQimenApiGetReponseNode(String apiMethodName, EpQimenOmsBaseQO wdtQO) throws IOException {
        /**
         *  WdtClient client = new WdtClient("eptison2", "eptison2-ot", "f7d79847b6200327aa3fc5551dc9bde9", "https://api.wangdian.cn/openapi2");
         *   ----对应正式环境
         */
        wdtSid = "apidevnew2";
        nonQimenApiKey = "eptison2-test";
        nonQimenApiSerect = "123456789";
        nonQimenBaseServerUrl = "http://sandbox.wangdian.cn/openapi2/";
        WdtClient client = new WdtClient(wdtSid, nonQimenApiKey, nonQimenApiSerect, nonQimenBaseServerUrl);
        Map<String, Object> objectMap = BeanMap.create(wdtQO);
        Map<String, String> paramMap = new HashMap<>(objectMap.size());
        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            if (null != entry.getValue()) {
                paramMap.put(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entry.getKey()), String.valueOf(entry.getValue()));
            }
        }
        String responseTxt;

        JSONObject reponseNode = new JSONObject();
        Integer outLimitRetryCounter = 0;
        for (; outLimitRetryCounter < outLimitRetryMax; outLimitRetryCounter++) {
            long performanceStart = System.currentTimeMillis();
            responseTxt = client.execute(apiMethodName, paramMap);
            log.warn("旺店通标准接口{} 调用耗时 {}ms", apiMethodName, System.currentTimeMillis() - performanceStart);
            reponseNode = JSON.parseObject(responseTxt);
            log.info("错误信息:{}",reponseNode.toJSONString());
            //系统调用频繁，旺店通1分钟最多60次
            if (WDT_API_ERROR_CODE_BUSY.equals(reponseNode.getString("code"))) {
                try {
                    Thread.sleep(outLimitWaitTimeGap);
                } catch (Exception e) {
                    //do nothing
                }
                //调用查询预约入库单接口使用 采购单号 或者 外部采购单号 直接查询
                //如果OMS查询不到则会 返回code  7200 和7201
                //这里不希望抛出异常
            } else if (!QIMEN_ERROR_CODE_OK.equals(reponseNode.getString("code")) && !OMS_ERROR_CODE_7200.equals(reponseNode.getString("code")) && !OMS_ERROR_CODE_7201.equals(reponseNode.getString("code"))) {
                throw new RuntimeException();
            } else {
                break;
            }
        }
        if (outLimitRetryCounter == outLimitRetryMax) {
            throw new RuntimeException();
        }
        return reponseNode;
    }

}