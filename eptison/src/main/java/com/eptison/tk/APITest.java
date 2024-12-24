package com.eptison.tk;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import okhttp3.Headers;

/**
 * Date: 2024/10/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class APITest {


    //通用请求域名地址
    private static String COMMON_PATH_PREFIX = "https://open-api.tiktokglobalshop.com";
    //商品类目相关
    private static String CATEGORIES_SEARCH_URL = "/gs_full_service_commodity/202405/beta/categories/search";
    //商品列表查询接口
    public static String PRODUCTS_SEARCH_URL = "/gs_full_service_commodity/202410/beta/products/search";
    //修改库存的接口
    public static final String INVENTORY_UPDATE = "/gs_full_service_inventory/202405/beta/virtual_inventory/update";
    public static final String INVENTORY_QUERY = "/gs_full_service_inventory/202405/beta/virtual_inventory/query";


    public static final String access_token = AccessToken.testAuthPairMap.get(AccessToken.ACCESS_TOKEN);

    //品牌查询
    public static String BRAND_SEARCH = "/gs_full_service_commodity/202405/beta/brands";
    private static int connectTimeout = 30000;//毫秒
    private static int readTimeout = 120000;//毫秒


    private static final String app_key = "6cnctergfpv4c";
    private static final String app_secret = "8957fd508cd86883b48e92ab7412ef147e743369";
    //上一步获取accessToken的接口会返回这个值
    String refresh_token = "ROW_DTIIkwAAAABulNVucoe3BblXMR_CQ9MvhTx4HNUoJ9EMa-TVIuhf4OSELNyK5Fq8F5AMWkuCFvc";

    public static void main(String[] args) {
//        TkCommonQO tkCommonQO = new TkCommonQO();
//        tkCommonQO.setPageSize(100);
//        tkCommonQO.setPageOffset(1);
//        final Map map = TkCommonQO.convert2LowerUnderScore(tkCommonQO);
//        System.out.println(map);

//        try {
//            AccessToken.getAccessToken();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            AccessToken.refreshAccessToken();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            categoriesSearch();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            productsSearch();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        try {
            updateVirtualStockUpdate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            virtualStockQuery();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void productsSearch() throws IOException {
        String request_url = COMMON_PATH_PREFIX + PRODUCTS_SEARCH_URL;

        //首先获取access_token
        TkCommonQO tkCommonQO = new TkCommonQO();
        tkCommonQO.setPageSize(10);
        tkCommonQO.setPageOffset(1);
        tkCommonQO.setPlatformSpuCodes(Arrays.asList("TESTS240103000008"));
        //获取accessToken
        Headers headers = Headers.of("x-tts-access-token", access_token,
                "UserAgent", "EP");
        HttpUtil.doPost(request_url, tkCommonQO, null, headers);
    }

    public static void categoriesSearch() throws IOException {
        String request_url = COMMON_PATH_PREFIX + CATEGORIES_SEARCH_URL;

        //首先获取access_token
        TkCommonQO tkCommonQO = new TkCommonQO();
        tkCommonQO.setPageSize(10);
        tkCommonQO.setPageOffset(1);
        //获取accessToken
        Headers headers = Headers.of("x-tts-access-token", access_token, "UserAgent", "EP");
        HttpUtil.doPost(request_url, tkCommonQO, null, headers);
    }


    public static void updateVirtualStockUpdate() throws IOException {

        String request_url = COMMON_PATH_PREFIX + INVENTORY_UPDATE;

        //首先获取access_token
        StockUpdateDTO stockUpdateDTO = new StockUpdateDTO();
        List<StockUpdateDTO> stockUpdateDTOList = new ArrayList<>();
        stockUpdateDTOList.add(stockUpdateDTO);
        stockUpdateDTO.setCode("TESTS24080600001400101");
        stockUpdateDTO.setQuantity(10);
        TkCommonQO tkCommonQO = new TkCommonQO();
        tkCommonQO.setSkus(stockUpdateDTOList);
        Headers headers = Headers.of("x-tts-access-token", access_token, "UserAgent", "EP");

        HttpUtil.doPost(request_url, tkCommonQO, null, headers);
//        HttpUtil.doPost(plm_demo_test_url, tkCommonQO, null,headers);
    }


    public static void virtualStockQuery() throws IOException {
        String request_url = COMMON_PATH_PREFIX + INVENTORY_QUERY;

        //首先获取access_token
        TkCommonQO tkCommonQO = new TkCommonQO();
        tkCommonQO.setSkuCode(Lists.newArrayList("TESTS24080600001400101"));
        Headers headers = Headers.of("x-tts-access-token", access_token, "UserAgent", "EP");

        HttpUtil.doPost(request_url, tkCommonQO, null, headers);
    }

}