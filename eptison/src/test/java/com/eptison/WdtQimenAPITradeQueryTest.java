package com.eptison;

import com.eptison.qimen.QimenApiTools;
import com.taobao.api.ApiException;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2024/2/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class WdtQimenAPITradeQueryTest {


    public static void main(String[] args) throws ApiException {

        String apiMethodName = "wdt.vip.api.trade.query";
        Map<String, Object> wdtMap = new HashMap<>();
        wdtMap.put("tid", "AD202402120030100040086509");
        wdtMap.put("page_no", 0);
        wdtMap.put("page_size", 100);
        System.out.println(QimenApiTools.excuteNonCrmApiGetResponse(apiMethodName, wdtMap));
    }

}