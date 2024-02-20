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
import java.text.SimpleDateFormat;
import java.util.Date;
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


    private static QimenCloudResponse getQimenCloudResponse(String apiMethodName, EpPairV2<DefaultQimenCloudClient, QimenCloudRequest> reqPair) throws ApiException {
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


}