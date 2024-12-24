package com.eptison.bojun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eptison.bojun.BojunAPIProcessOrderTransaction.BojunAPICommandDetailObjParam;
import com.eptison.bojun.BojunAPIProcessOrderTransaction.BojunAPICommandRefObjParam;
import com.eptison.bojun.BojunAPIProcessOrderTransaction.BojunAPIProcessOrderParam;
import com.eptison.qimen.Md5Util;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

/**
 * Date: 2024/10/25
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Slf4j
public class TestPostFO {


    public static void main(String[] args) throws IllegalAccessException, IOException {

//        create();

//        modify();

//        delete();

//        processOrder();

//        rRequest();


    }


    public static void rRequest() {
        MOtherInout mOtherInout = new MOtherInout();
        mOtherInout.setId(-1);
        mOtherInout.setBillDate(20241114);
        mOtherInout.setDescription("创建物理调整单");

        MOtherInoutItem mOtherInoutItem = new MOtherInoutItem();
        mOtherInoutItem.setCOtherInoutTypeName("补货");
        mOtherInoutItem.setDescription("新建补货明细");
        mOtherInoutItem.setCount(20);
        mOtherInoutItem.setCStoreName("测试店铺");
        mOtherInoutItem.setMerchantCode("DQXZB炫彩黑165");

        mOtherInout.setMOtherInoutItemList(Lists.newArrayList(mOtherInoutItem));

        SerializeConfig config = new SerializeConfig();
        config.put(MOtherInout.class, new CustomSerializer());
//        System.out.printf(JSON.toJSONString(mOtherInout));
        SerializeFilter[] SerializeFilter=new SerializeFilter[]{new BojunNameFilter(),new BojunPropertyFilter()};
        String jsonString = JSON.toJSONString(mOtherInout, SerializeFilter);
        System.out.println(jsonString);
    }

    public static class BojunNameFilter implements NameFilter {

        public BojunNameFilter() {
        }

        @Override
        public String process(Object object, String name, Object value) {
            Class<?> voClass = object.getClass();
            try {
                Field field = voClass.getDeclaredField((String) name);
                if (field.isAnnotationPresent(BojunAPIRequest.class)) {
                    final String column = field.getAnnotation(BojunAPIRequest.class).column();
                    return column;
                } else {
                    return name;
                }
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static class BojunPropertyFilter implements PropertyFilter {

        public BojunPropertyFilter() {
        }

        @Override
        public boolean apply(Object object, String name, Object value) {
            Class<?> voClass = object.getClass();
            try {
                Field field = voClass.getDeclaredField((String) name);
                if (field.isAnnotationPresent(BojunAPIRequest.class)) {
                    return true;
                } else {
                    return false;
                }
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class CustomSerializer implements ObjectSerializer {

        @Override
        public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
            SerializeWriter out = serializer.out;
            try {
                Class<?> voClass = serializer.getContext().getObject().getClass();
                Field field = voClass.getDeclaredField((String) fieldName);
                if (field.isAnnotationPresent(BojunAPIRequest.class)) {
                    field.setAccessible(true);
                    final BojunAPIRequest bojunAPIRequest = field.getAnnotation(BojunAPIRequest.class);
                    final String column = bojunAPIRequest.column();
                    out.writeFieldName(column);
                }
            } catch (NoSuchFieldException e) {
                // ignore exception
                out.writeNull(SerializerFeature.WriteNullStringAsEmpty);
            }
        }
    }

    public static List<BojunAPIResponseCommonDTO> doPost(String url, Map<String, Object> params) throws IOException {
        // 创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
//        StringBuilder builder = new StringBuilder();
//        params.forEach((name, value) -> {
//            if (builder.length() != 0) {
//                builder.append('&');
//            }
//            builder.append(name);
//            if (value != null) {
//                builder.append('=');
//                builder.append(value);
//            }
//        });
        String postBody = Joiner.on("&").withKeyValueSeparator("=").join(params);
        RequestBody requestBody = RequestBody.create(MediaType.get("application/x-www-form-urlencoded"), postBody);
        // 创建一个请求对象
        Request request = new Request.Builder().url(url).post(requestBody).build();
        // 发送请求获取响应
        try {
            Response response = okHttpClient.newCall(request).execute();
            // 判断请求是否成功
            if (response.isSuccessful()) {
                String body = response.body().string();
                log.info("body:{}", JSON.toJSONString(body));
                // 打印服务端返回结果
                return JSON.parseArray(body, BojunAPIResponseCommonDTO.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void create() throws IOException, IllegalAccessException {
        String DATE_MICO_SECOND_FORMATTER = "yyyy-MM-dd HH:mm:ss.SSS";
        DateTimeFormatter dateTimeMicroSecondFormatter = DateTimeFormatter.ofPattern(DATE_MICO_SECOND_FORMATTER);

        SupplierType supplierType = new SupplierType();
        supplierType.setSupplierTypeCode("代销2");
        supplierType.setSupplierTypeName("代销2");

        List<Integer> result = new ArrayList<>();

        List<BojunAPICreateRequestQO> transactions = new ArrayList<>();

        Map<String, Object> createContent = new HashMap<>();

        final Field[] declaredFields = supplierType.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(BojunAPIRequest.class)) {
                BojunAPIRequest bojunAPIRequest = field.getAnnotation(BojunAPIRequest.class);
                createContent.put(bojunAPIRequest.column(), field.get(supplierType));
            }
        }
        //获得保存的表名
        String table = supplierType.getClass().getAnnotation(BojunAPITable.class).table();
        createContent.put("table", table);
        BojunAPICreateRequestQO commandCreateParam = BojunAPICreateRequestQO.builder()
                .command("ObjectCreate")
                .params(createContent)
                .build();

        transactions.add(commandCreateParam);

        String static_query_url = "http://bjpostest.yptcgroup.com:7001/servlets/binserv/Rest";
        String sip_appkey = "17367076217";
        String sip_timestamp = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).format(dateTimeMicroSecondFormatter);
        String sip_password = "abcd2010300489";
        //先根据md5算法计算出对应的sip_secrect的md5值
        String sip_secrect = Md5Util.getMd5(sip_password);
        //sip_appkey+sip_timestamp+appSecret进行MD5哈希运算，结果为32位长字符串，全部小写
        String sip_sign = Md5Util.getMd5(sip_appkey + sip_timestamp + sip_secrect);

        JSONArray array = JSONArray.parseArray(JSON.toJSONString(transactions));
        //通过okhttp发送请求
        Map<String, Object> params = new HashMap<>();
        params.put("sip_appkey", sip_appkey);
        params.put("sip_timestamp", sip_timestamp);
        params.put("sip_sign", sip_sign);
        params.put("transactions", array);
        log.info("request param:{}", JSON.toJSONString(params));

        List<BojunAPIResponseCommonDTO> apiResponseCommonDTOList = doPost(static_query_url, params);

        log.info("apiResponseCommonDTOList:{}", JSON.toJSONString(apiResponseCommonDTOList));
    }


    public static void modify() throws IOException, IllegalAccessException {

        String DATE_MICO_SECOND_FORMATTER = "yyyy-MM-dd HH:mm:ss.SSS";
        DateTimeFormatter dateTimeMicroSecondFormatter = DateTimeFormatter.ofPattern(DATE_MICO_SECOND_FORMATTER);

        SupplierType supplierType = new SupplierType();
        supplierType.setId(35);
        supplierType.setSupplierTypeCode("代销2");
        supplierType.setSupplierTypeName("代销222");

        List<Integer> result = new ArrayList<>();

        List<BojunAPICreateRequestQO> transactions = new ArrayList<>();

        Map<String, Object> createContent = new HashMap<>();

        final Field[] declaredFields = supplierType.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(BojunAPIRequest.class)) {
                BojunAPIRequest bojunAPIRequest = field.getAnnotation(BojunAPIRequest.class);
                createContent.put(bojunAPIRequest.column(), field.get(supplierType));
            }
        }
        //获得保存的表名
        String table = supplierType.getClass().getAnnotation(BojunAPITable.class).table();
        createContent.put("table", table);
        BojunAPICreateRequestQO commandCreateParam = BojunAPICreateRequestQO.builder()
                .command("ObjectModify")
                .params(createContent)
                .build();

        transactions.add(commandCreateParam);

        String static_query_url = "http://bjpostest.yptcgroup.com:7001/servlets/binserv/Rest";
        String sip_appkey = "17367076217";
        String sip_timestamp = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).format(dateTimeMicroSecondFormatter);
        String sip_password = "abcd2010300489";
        //先根据md5算法计算出对应的sip_secrect的md5值
        String sip_secrect = Md5Util.getMd5(sip_password);
        //sip_appkey+sip_timestamp+appSecret进行MD5哈希运算，结果为32位长字符串，全部小写
        String sip_sign = Md5Util.getMd5(sip_appkey + sip_timestamp + sip_secrect);

        JSONArray array = JSONArray.parseArray(JSON.toJSONString(transactions));
        //通过okhttp发送请求
        Map<String, Object> params = new HashMap<>();
        params.put("sip_appkey", sip_appkey);
        params.put("sip_timestamp", sip_timestamp);
        params.put("sip_sign", sip_sign);
        params.put("transactions", array);
        log.info("request param:{}", JSON.toJSONString(params));

        List<BojunAPIResponseCommonDTO> apiResponseCommonDTOList = doPost(static_query_url, params);

        log.info("apiResponseCommonDTOList:{}", JSON.toJSONString(apiResponseCommonDTOList));
    }

    public static void delete() throws IOException, IllegalAccessException {
        String DATE_MICO_SECOND_FORMATTER = "yyyy-MM-dd HH:mm:ss.SSS";
        DateTimeFormatter dateTimeMicroSecondFormatter = DateTimeFormatter.ofPattern(DATE_MICO_SECOND_FORMATTER);

        SupplierType supplierType = new SupplierType();
        supplierType.setId(35);
        supplierType.setSupplierTypeCode("代销2");
        supplierType.setSupplierTypeName("代销222");

        List<Integer> result = new ArrayList<>();

        List<BojunAPICreateRequestQO> transactions = new ArrayList<>();

        Map<String, Object> createContent = new HashMap<>();

        final Field[] declaredFields = supplierType.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(BojunAPIRequest.class)) {
                BojunAPIRequest bojunAPIRequest = field.getAnnotation(BojunAPIRequest.class);
                createContent.put(bojunAPIRequest.column(), field.get(supplierType));
            }
        }
        //获得保存的表名
        String table = supplierType.getClass().getAnnotation(BojunAPITable.class).table();
        createContent.put("table", table);
        BojunAPICreateRequestQO commandCreateParam = BojunAPICreateRequestQO.builder()
                .command("ObjectDelete")
                .params(createContent)
                .build();

        transactions.add(commandCreateParam);

        String static_query_url = "http://bjpostest.yptcgroup.com:7001/servlets/binserv/Rest";
        String sip_appkey = "17367076217";
        String sip_timestamp = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).format(dateTimeMicroSecondFormatter);
        String sip_password = "abcd2010300489";
        //先根据md5算法计算出对应的sip_secrect的md5值
        String sip_secrect = Md5Util.getMd5(sip_password);
        //sip_appkey+sip_timestamp+appSecret进行MD5哈希运算，结果为32位长字符串，全部小写
        String sip_sign = Md5Util.getMd5(sip_appkey + sip_timestamp + sip_secrect);

        JSONArray array = JSONArray.parseArray(JSON.toJSONString(transactions));
        //通过okhttp发送请求
        Map<String, Object> params = new HashMap<>();
        params.put("sip_appkey", sip_appkey);
        params.put("sip_timestamp", sip_timestamp);
        params.put("sip_sign", sip_sign);
        params.put("transactions", array);
        log.info("request param:{}", JSON.toJSONString(params));

        List<BojunAPIResponseCommonDTO> apiResponseCommonDTOList = doPost(static_query_url, params);

        log.info("apiResponseCommonDTOList:{}", JSON.toJSONString(apiResponseCommonDTOList));

    }

    public static void processOrder() {

        String DATE_MICO_SECOND_FORMATTER = "yyyy-MM-dd HH:mm:ss.SSS";
        DateTimeFormatter dateTimeMicroSecondFormatter = DateTimeFormatter.ofPattern(DATE_MICO_SECOND_FORMATTER);

        MOtherInout mOtherInout = new MOtherInout();
        mOtherInout.setId(-1);
        mOtherInout.setBillDate(20241114);
        mOtherInout.setDescription("创建物理调整单&");

        MOtherInoutItem mOtherInoutItem = new MOtherInoutItem();
        mOtherInoutItem.setCOtherInoutTypeName("补货");
        mOtherInoutItem.setDescription("新建补货明细");
        mOtherInoutItem.setCount(20);
        mOtherInoutItem.setCStoreName("测试店铺");
        mOtherInoutItem.setMerchantCode("DQXZB炫彩黑165");

        mOtherInout.setMOtherInoutItemList(Lists.newArrayList(mOtherInoutItem));

        List<MOtherInout> motherInoutList = Collections.singletonList(mOtherInout);

        List<BojunAPIProcessOrderTransaction> transactions = new ArrayList<>();
        for (MOtherInout epBaseDTO : motherInoutList) {
            BojunAPIProcessOrderTransaction transaction = new BojunAPIProcessOrderTransaction();
            Map<String, Object> masterobj = new HashMap<>();
            BojunAPICommandDetailObjParam detailobjs = new BojunAPICommandDetailObjParam();
            final Field[] declaredFields = epBaseDTO.getClass().getDeclaredFields();
            //获取masterobj table
            String masterobjTable = epBaseDTO.getClass().getAnnotation(BojunAPITable.class).table();
            masterobj.put("table", masterobjTable);

            Map<String, Object> masterContent = new HashMap<>();
            List<Integer> reftables = new ArrayList<>();
            List<BojunAPICommandRefObjParam> refobjs = new ArrayList<>();
            for (Field objMasterField : declaredFields) {
                objMasterField.setAccessible(true);
                if (objMasterField.isAnnotationPresent(BojunAPIRequest.class)) {
                    BojunAPIRequest bojunAPIRequest = objMasterField.getAnnotation(BojunAPIRequest.class);
                    try {
                        Object value = objMasterField.get(epBaseDTO);
                        if (Objects.isNull(value)) {
                            continue;
                        }
                        //不是明细字段
                        if (StringUtils.isNotBlank(bojunAPIRequest.column())) {
                            masterContent.put(bojunAPIRequest.column(), value);
                        }
                        //处理对应的关联表信息
                        if (bojunAPIRequest.reftableId() != -1) {
                            reftables.add(bojunAPIRequest.reftableId());
                            Object refObject = value;
                            final String reftableType = bojunAPIRequest.reftableType();
                            BojunAPICommandRefObjParam detailObjParam = new BojunAPICommandRefObjParam();
                            List<Map<String, Object>> refObjectsList = new ArrayList<>();
                            if (refObject instanceof Collection) {
                                Collection valCol = (Collection) refObject;
                                Iterator valIterator = valCol.iterator();
                                while (valIterator.hasNext()) {
                                    Object val = valIterator.next();
                                    if (val.getClass().isAnnotationPresent(BojunAPITable.class)) {
                                        BojunAPITable bojunAPITable = val.getClass().getAnnotation(BojunAPITable.class);
                                        detailObjParam.setTable(bojunAPITable.table());
                                        //进一步处理关联表的字段信息
                                        final Field[] detailObjDeclaredFields = val.getClass().getDeclaredFields();
                                        Map<String, Object> detailContent = new HashMap<>();
                                        for (Field objDetailField : detailObjDeclaredFields) {
                                            objDetailField.setAccessible(true);
                                            BojunAPIRequest detailObjRequest = objDetailField.getAnnotation(BojunAPIRequest.class);
                                            detailContent.put(detailObjRequest.column(), objDetailField.get(val));
                                        }
                                        refObjectsList.add(detailContent);
                                    }
                                }
                            } else {
                                BojunAPITable bojunAPITable = refObject.getClass().getAnnotation(BojunAPITable.class);
                                detailObjParam.setTable(bojunAPITable.table());
                                final Field[] detailObjDeclaredFields = refObject.getClass().getDeclaredFields();
                                Map<String, Object> detailContent = new HashMap<>();
                                for (Field objDetailField : detailObjDeclaredFields) {
                                    objDetailField.setAccessible(true);
                                    BojunAPIRequest detailObjRequest = objDetailField.getAnnotation(BojunAPIRequest.class);
                                    detailContent.put(detailObjRequest.column(), objDetailField.get(refObject));
                                }
                                refObjectsList.add(detailContent);
                            }
                            if (reftableType.equals("add")) {
                                detailObjParam.setAddList(refObjectsList);
                            }
                            if (reftableType.equals("delete")) {
                                detailObjParam.setDeleteList(refObjectsList);
                            }
                            if (reftableType.equals("modify")) {
                                detailObjParam.setModifyList(refObjectsList);
                            }
                            refobjs.add(detailObjParam);
                        }

                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                detailobjs.setReftables(reftables);
                detailobjs.setRefobjs(refobjs);
            }
            masterobj.putAll(masterContent);
            transaction.setCommand("ProcessOrder");
            BojunAPIProcessOrderParam paramsObj = new BojunAPIProcessOrderParam();
            paramsObj.setMasterobj(masterobj);
            paramsObj.setDetailobjs(detailobjs);
            transaction.setParams(paramsObj);
            transactions.add(transaction);
        }

        String static_query_url = "http://bjpostest.yptcgroup.com:7001/servlets/binserv/Rest";
        String sip_appkey = "17367076217";
        String sip_timestamp = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).format(dateTimeMicroSecondFormatter);
        String sip_password = "abcd2010300489";
        //先根据md5算法计算出对应的sip_secrect的md5值
        String sip_secrect = Md5Util.getMd5(sip_password);
        //sip_appkey+sip_timestamp+appSecret进行MD5哈希运算，结果为32位长字符串，全部小写
        String sip_sign = Md5Util.getMd5(sip_appkey + sip_timestamp + sip_secrect);

        JSONArray array = JSONArray.parseArray(JSON.toJSONString(transactions));
        //通过okhttp发送请求
        Map<String, Object> params = new HashMap<>();
        params.put("sip_appkey", sip_appkey);
        params.put("sip_timestamp", sip_timestamp);
        params.put("sip_sign", sip_sign);
        params.put("transactions", array);
        log.info("request param:{}", JSON.toJSONString(params));

        List<BojunAPIResponseCommonDTO> apiResponseCommonDTOList = null;
        try {
            apiResponseCommonDTOList = doPost(static_query_url, params);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("apiResponseCommonDTOList:{}", JSON.toJSONString(apiResponseCommonDTOList));


    }


    /**
     * 利用对象流实现对象深拷贝
     */
    public static <T> T copyDeep(T source) {
        if (source == null) {
            return null;
        }
        try {
            //序列化
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(source);

            //反序列化
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (T) ois.readObject();
        } catch (Exception e) {
            log.warn("copyDeep failed", e);
        }
        return null;
    }
}