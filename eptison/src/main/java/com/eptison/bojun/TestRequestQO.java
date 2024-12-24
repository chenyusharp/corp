package com.eptison.bojun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.eptison.bojun.BojunAPICommonRequestQO.BojunAPICommandQueryParam;
import com.eptison.bojun.BojunAPICommonRequestQO.BojunAPIQueryFilter;
import com.eptison.bojun.ProductQO.BojunAPIQueryOrderBy;
import com.eptison.qimen.Md5Util;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.Data;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 2024/10/24
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Data
public class TestRequestQO {


    private static final Logger log = LoggerFactory.getLogger(TestRequestQO.class);
    private Integer age;

    private String name;

    private Long length;

    private Double weight;

    private Map<String, String> goodsMap;


    private List<String> packageList;


    private Set<Integer> driverSet;

    // sip_appkey=xiazhenyu77@aliyun.com&sip_timestamp=2024-10-24 10:39:49.000&sip_sign=d9f277790644956d330f8aebd3b0adff

    // sip_appkey=xiazhenyu77@aliyun.com&sip_timestamp=2024-10-24 13:36:37.000&sip_sign=77e29215c22612875e9e1da6d5928953
    //密码：abcd2010300489
    public static void main(String[] args) throws IllegalAccessException {
        //利用反射获取QO中的Field集合
//        TestRequestQO testRequestQO = new TestRequestQO();
//        final Field[] declaredFields = testRequestQO.getClass().getDeclaredFields();
//        for (Field field : declaredFields) {
//            System.out.println(field.getName() + ":" + field.getType());
//        }
        List<Expression> expressions = new ArrayList<>();
        expressions.add(new Expression("TXDY", "C_SUPPLIER_ID;CODE"));
        expressions.add(new Expression("DQXZ", "NAME"));
        expressions.add(new Expression("夏", "M_DIM2_ID;ATTRIBCODE"));
        expressions.add(new Expression("DUI BAI", "M_DIM1_ID;ATTRIBCODE"));

//        BojunAPIQueryFilter filter = new BojunAPIQueryFilter();
//        if (expressions.size() == 1) {
//            filter.setColumn(expressions.get(0).getColumn());
//            filter.setCondition(expressions.get(0).getOperator() + expressions.get(0).getCondition());
//        } else {
//            filter.setCombine("and");
//            createExpression(filter, expressions);
//        }
//
//        String jsonResult = JSON.toJSONString(filter);
//        if (StringUtils.isNotBlank(jsonResult)) {
//            System.out.println(jsonResult);
//            return;
//        }

        String DATA_FORMATTER = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATA_FORMATTER);

        String DATE_MICO_SECOND_FORMATTER = "yyyy-MM-dd HH:mm:ss.SSS";
        DateTimeFormatter dateTimeMicroSecondFormatter = DateTimeFormatter.ofPattern(DATE_MICO_SECOND_FORMATTER);

        ProductQO productQO = new ProductQO();
//        productQO.setSupplierCode("TXDY");
        productQO.setGoodsNo("BMQT001");
//        productQO.setColorList(Lists.newArrayList("方形领黑色","蓝黑色","三角领黑色","骷哒古哒黑","钢琴黑","牛仔黑","黑黑黑#","活性黑","黑亮色","波若诺啦黑","克黑","特黑","布啦啦叽黑","花黑色","黑白色","黑暗色","特黑黑黑黑黑黑黑黑黑黑黑黑黑黑黑黑黑黑黑色","酷小黑","精灵黑","圆领特黑","黑底波点","黑兰色","黑红色","黑绿","黑绿色","星空黑","高牢度黑","黑白波点","黑色、肤色","暗夜黑","花卡其1","酷酷的黑","冰曜黑","黑底烫金","中领特黑","黑色","黑底白点","牛仔黑色","黑黑黑","纯调黑白","炭黑色","萌狗黑","彩虹黑","橘猫黑","黑底烫银","巴黎黑","爱心黑","甜筒黑","缤纷绮黑",
//                "银河黑","一字领黑色","灰卡其","蓝灰色","青灰色","米灰色","绿灰色","花灰色","灰深灰吊染","烟灰色","深灰","麻灰色","浅灰","灰咖","灰白双面","炭灰色","浅麻灰","灰白色","高级灰","冰雾灰","猫咪灰","星空灰","烟熏灰","圆领石灰","黑灰色","V领石灰","深灰色","中领石灰","中灰色","灰紫色","灰紫","青绿灰","土灰色","浅灰色","橡皮灰","暗花灰","岩石灰","灰咖色","灰色格","灰色渐变","灰杏色","银灰色","稻灰色","深灰蓝色","浅褐灰","深花灰","蓝灰","一字领高级灰","方形领高级灰","三角领高级灰","冰灰"));
        productQO.setPageQuery(false);
        List<ProductQO.BojunAPIQueryOrderBy> orderBys = new ArrayList<>();
//        orderBys.add(new ProductQO.BojunAPIQueryOrderBy(true,"ID"));
//        orderBys.add(new ProductQO.BojunAPIQueryOrderBy(true,"NAME"));
//        productQO.setOrderByList(orderBys);
        //通过QO上的标签来获取对应的查询columns
        String table = "";
        //映射到fms的DTO对象
        List<Class> apiMappingDTOList=new ArrayList<>();
        //映射DTO属性对象
        List<List<String>> mappingColumnsList=new ArrayList<>();

        if (productQO.getClass().isAnnotationPresent(BojunAPITable.class)) {
            table = productQO.getClass().getAnnotation(BojunAPITable.class).table();
            apiMappingDTOList.add(productQO.getClass().getAnnotation(BojunAPITable.class).resultMapDTO());
        }

        //返回对象
        final Field[] declaredFields = productQO.getClass().getAnnotation(BojunAPITable.class).resultMapDTO().getDeclaredFields();
        List<String> columns = new ArrayList<>();
        List<String> fieldNames = new ArrayList<>();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(BojunAPIResponse.class)) {
                final BojunAPIResponse annotation = field.getAnnotation(BojunAPIResponse.class);
                columns.add(annotation.alias());
                fieldNames.add(field.getName());
            }
        }
        mappingColumnsList.add(fieldNames);
        //请求参数，暂时支持所有的查询都是and的关系
        List<Expression> expressionList = new ArrayList<>();
        final Field[] queryQO = productQO.getClass().getDeclaredFields();
        Integer currentPage = 0;
        Integer pageSize = 30;
        Boolean pageQuery = null;
        List<BojunAPIQueryOrderBy> orderByList=null;
        for (Field field : queryQO) {
            field.setAccessible(true);
            if (!field.isAnnotationPresent(BojunAPIRequest.class)) {
                continue;
            }
            Object value = field.get(productQO);
            if (Objects.isNull(value)) {
                continue;
            }
            String condition = null;
            String operator = "=";
            //这里需要对value做特殊的处理
            if (value instanceof String) {
                condition = value.toString();
            }
            if (value instanceof Date) {
                //伯俊时间也是采用的字符串，并且只支持yyyy-MM-dd HH:mm:ss的格式
                condition = LocalDateTime.ofInstant(((Date) value).toInstant(), ZoneId.systemDefault()).format(dateTimeFormatter);
            }
            if (value instanceof Collection) {
                Collection valCol = (Collection) value;
                StringBuilder stringBuilder = new StringBuilder("(");
                for (Iterator iterator = valCol.iterator(); ; ) {
                    Object val = iterator.next();
                    if (val instanceof String) {
                        stringBuilder.append("'").append(val).append("'");
                    }
                    if (iterator.hasNext()) {
                        stringBuilder.append(",");
                    } else {
                        break;
                    }
                }
                condition = stringBuilder.append(")").toString();
                operator = "in ";
            }
            if (value instanceof BigDecimal) {
                condition = ((BigDecimal) value).toPlainString();
            }
            final BojunAPIRequest annotation = field.getAnnotation(BojunAPIRequest.class);
            final String column = annotation.column();
            if (column.equals("pageQuery")) {
                pageQuery = (Boolean) value;
                continue;
            }
            if (column.equals("currentPage")) {
                currentPage = (Integer) value;
                continue;
            }
            if (column.equals("pageSize")) {
                pageSize = (Integer) value;
                continue;
            }
            if (column.equals("orderByList")){
                orderByList= (List<BojunAPIQueryOrderBy>) value;
                continue;
            }
            expressionList.add(new Expression(column, condition, operator));
        }
        // 转为BojunAPIQueryFilter的结构
        BojunAPIQueryFilter queryFilter = new BojunAPIQueryFilter();
        if (expressionList.size() == 1) {
            queryFilter.setColumn(expressionList.get(0).getColumn());
            queryFilter.setCondition(expressionList.get(0).getOperator() + expressionList.get(0).getCondition());
        } else {
            queryFilter.setCombine("and");
            createExpression(queryFilter, expressionList);
        }
        Integer start=null;
        Integer range=null;
        if (pageQuery.equals(Boolean.TRUE)) {
            start = (currentPage - 1) * pageSize;
            range = pageSize;
        }
        BojunAPICommonRequestQO bojunAPICommonRequestQO = BojunAPICommonRequestQO.builder()
                .command("Query")
                .params(BojunAPICommandQueryParam.builder()
                        .columns(columns)
                        .start(start)
                        .count(true)
                        .range(range).
                        orderby(orderByList)
                        .params(queryFilter)
                        .table(table)
                        .build())
                .build();
        log.info("bojunAPICommonRequestQO:{}", JSON.toJSONString(bojunAPICommonRequestQO));
        //调用接口进行查询

        String static_query_url = "http://pos.yptcgroup.com:7001/servlets/binserv/Rest";
        String sip_appkey = "it@eptison.com";
        String sip_timestamp = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).format(dateTimeMicroSecondFormatter);
        String sip_password = "eptison123";
        //先根据md5算法计算出对应的sip_secrect的md5值
        String sip_secrect = Md5Util.getMd5(sip_password);
        //sip_appkey+sip_timestamp+appSecret进行MD5哈希运算，结果为32位长字符串，全部小写
        String sip_sign = Md5Util.getMd5(sip_appkey + sip_timestamp + sip_secrect);
        //transactions数组，每一个数组代表一个请求处理逻辑
        List<BojunAPICommonRequestQO> transactions = new ArrayList<>();
        transactions.add(bojunAPICommonRequestQO);

        JSONArray array = JSONArray.parseArray(JSON.toJSONString(transactions));

        //通过okhttp发送请求
        Map<String, Object> params = new HashMap<>();
        params.put("sip_appkey", sip_appkey);
        params.put("sip_timestamp", sip_timestamp);
        params.put("sip_sign", sip_sign);
        params.put("transactions", array);
        log.info("request param:{}", JSON.toJSONString(params));
        try {
            List<BojunAPIResponseCommonDTO> apiResponseCommonDTOList = doPost(static_query_url, params);
            log.info("responseBody:{}", JSON.toJSONString(apiResponseCommonDTOList));
            //首先判断apiResponseCommonDTOList的大小和transactions的大小是不是一样
            if (transactions.size()!=apiResponseCommonDTOList.size()){
                //抛出异常
                throw new RuntimeException();
            }
            //这里需要根据transations的数组的顺序来匹配对应的请求对象
            List<List> resultList = new ArrayList<>();
            for (int i = 0; i < transactions.size(); i++) {
                final Class mappingClass = apiMappingDTOList.get(i);
                List responseClassList = new ArrayList<>();
                final BojunAPIResponseCommonDTO bojunAPIResponseCommonDTO = apiResponseCommonDTOList.get(i);
                if (bojunAPIResponseCommonDTO.getCode()==0){
                    final List<List<Object>> dataRowsList = bojunAPIResponseCommonDTO.getData().getRows();
                    final List<String> fieldNameList = mappingColumnsList.get(i);
                    List<Map<String,Object>> resultMapList=new ArrayList<>();


                    for (List<Object> rows : dataRowsList) {
                        Map<String,Object> resultMap=new HashMap<>();
                        for (int j = 0; j < fieldNameList.size(); j++) {
                            resultMap.put(fieldNameList.get(j), rows.get(j));
                        }
                        resultMapList.add(resultMap);
                    }
                    for (Map<String, Object> objectMap : resultMapList) {
                        responseClassList.add(JSON.parseObject(JSON.toJSONString(objectMap),mappingClass));
                    }
                    resultList.add(responseClassList);
                    bojunAPIResponseCommonDTO.setBizDataList(responseClassList);
                }else {
                    //抛出异常？
                    throw  new RuntimeException();
                }

            }
        log.info("apiResponseCommonDTOList:{}", JSON.toJSONString(apiResponseCommonDTOList));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    protected static void createExpression(BojunAPIQueryFilter queryFilter, List<Expression> expressionList) {
        if (CollectionUtils.isEmpty(expressionList)) {
            return;
        }
        if (expressionList.size() == 1) {
            BojunAPIQueryFilter exp = new BojunAPIQueryFilter();
            exp.setCondition(expressionList.get(0).getCondition());
            exp.setColumn(expressionList.get(0).getOperator() + expressionList.get(0).getColumn());
            queryFilter.setExpr1(exp);
        } else if (expressionList.size() == 2) {
            final BojunAPIQueryFilter expr1 = new BojunAPIQueryFilter();
            expr1.setColumn(expressionList.get(0).getColumn());
            expr1.setCondition(expressionList.get(0).getOperator() + expressionList.get(0).getCondition());
            queryFilter.setExpr1(expr1);

            final BojunAPIQueryFilter expr2 = new BojunAPIQueryFilter();
            expr2.setColumn(expressionList.get(1).getColumn());
            expr2.setCondition(expressionList.get(1).getOperator() + expressionList.get(1).getCondition());
            queryFilter.setExpr2(expr2);
            queryFilter.setCombine("and");
        } else {
            //分成两部分
            List<Expression> part1 = expressionList.subList(0, expressionList.size() / 2);
            List<Expression> part2 = expressionList.subList(expressionList.size() / 2, expressionList.size());
            BojunAPIQueryFilter exp1 = new BojunAPIQueryFilter();
            BojunAPIQueryFilter exp2 = new BojunAPIQueryFilter();

            queryFilter.setExpr1(exp1);
            queryFilter.setExpr2(exp2);
            createExpression(exp2, part1);
            createExpression(exp1, part2);
        }
    }

    public static List<BojunAPIResponseCommonDTO> doPost(String url, Map<String, Object> params) throws IOException {
        // 创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        StringBuilder builder = new StringBuilder();
        params.forEach((name, value) -> {
            if (builder.length() != 0) {
                builder.append('&');
            }
            builder.append(name);
            if (value != null) {
                builder.append('=');
                builder.append(value);
            }
        });
        RequestBody requestBody = RequestBody.create(MediaType.get("application/x-www-form-urlencoded"), builder.toString());
        // 创建一个请求对象
        Request request = new Builder().url(url).post(requestBody).build();
        // 发送请求获取响应
        try {
            Response response = okHttpClient.newCall(request).execute();
            // 判断请求是否成功
            if (response.isSuccessful()) {
                String body = response.body().string();
                log.info("body:{}",JSON.toJSONString(body));
                // 打印服务端返回结果
                return JSON.parseArray(body,BojunAPIResponseCommonDTO.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}