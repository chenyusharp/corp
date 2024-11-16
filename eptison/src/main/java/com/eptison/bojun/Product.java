package com.eptison.bojun;

import com.alibaba.fastjson.JSON;
import com.eptison.qimen.Md5Util;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * Date: 2024/10/24
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Data
public class Product {


    private Long id;
    //    AD_CLIENT_ID;DOMAIN,

    @BojunAPIResponse(alias = "NAME")
    private String goodsNo;
    //    VIRTUALNAME,
    @BojunAPIResponse(alias = "VALUE")
    private String goodsName;

    private List<Mdim> mdims;



    public static void main(String[] args) {
        // 创建一个条件集合
//        List<Condition> conditions = new ArrayList<>();
//        conditions.add(new Condition("TXDY", "C_SUPPLIER_ID;CODE"));
//        conditions.add(new Condition("DQXZ", "NAME"));
//        conditions.add(new Condition("夏", "M_DIM2_ID;ATTRIBCODE"));
//        conditions.add(new Condition("DUI BAI", "M_DIM1_ID;ATTRIBCODE"));
//
//        Expression expression = new Expression();
//        expression.setCombine("and");
//        createExpression(expression, conditions);
//
//        String jsonResult = JSON.toJSONString(expression);
//        System.out.println(jsonResult);

        String sip_secrect="abcd2010300489";
        //先根据md5算法计算出对应的sip_secrect的md5值
        System.out.println(Md5Util.getMd5(sip_secrect));


        String DATE_MICO_SECOND_FORMATTER = "yyyy-MM-dd HH:mm:ss.SSS";
        DateTimeFormatter dateTimeMicroSecondFormatter = DateTimeFormatter.ofPattern(DATE_MICO_SECOND_FORMATTER);
        String sip_timestamp = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).format(dateTimeMicroSecondFormatter);
        System.out.println(sip_timestamp);

    }
}