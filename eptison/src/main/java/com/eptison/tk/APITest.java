package com.eptison.tk;

import com.alibaba.fastjson.JSONObject;
import com.eptison.qimen.WebUtils;
import java.util.Map;

/**
 * Date: 2024/10/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class APITest {


    //通用请求域名地址
    private static String COMMON_PATH_PREFIX = "https://open-api.tiktokglobalshop.com/";
    //商品类目相关
    private static String CATEGORIES_SEARCH_URL = "/gs_full_service_commodity/202405/beta/categories/search";
    private static int connectTimeout = 30000;//毫秒
    private static int readTimeout = 120000;//毫秒


    public static void main(String[] args) {
        TkCommonQO tkCommonQO = new TkCommonQO();
        tkCommonQO.setPageSize(100);
        tkCommonQO.setPageOffset(1);
        final Map map = TkCommonQO.convert2LowerUnderScore(tkCommonQO);
        System.out.println(map);

    }


    public static String CategoriesSearch() {
        String request_url = COMMON_PATH_PREFIX + CATEGORIES_SEARCH_URL;
        //首先获取access_token
        TkCommonQO tkCommonQO = new TkCommonQO();
        tkCommonQO.setPageSize(100);
        tkCommonQO.setPageOffset(1);
        //还需要设置appKey、sign（通过Singature接口获取）、timestamp
        Map<String, String> requestParam = TkCommonQO.convert2LowerUnderScore(tkCommonQO);
        //转成json字符串
        String jsonRequestParam = JSONObject.toJSONString(requestParam);
        HttpUtil.doPost(request_url,jsonRequestParam);

        return null;
    }

}