package com.eptison.tools;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Date: 2024/2/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyTest {


    public static void main(String[] args) throws ParseException {

//        String startTime = "2024-01-01";
//        String endTime = "2024-02-29";
//        //计算需要的总的天数
//        //创建一个formatter
//        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        //字符串转换为localDate
//        final LocalDate startLocalDate = LocalDate.parse(startTime, dateTimeFormatter);
//        final LocalDate endLocalDate = LocalDate.parse(endTime, dateTimeFormatter);
//        //求两个时间的差的天数(针对日期)
//        final long betweenDays = startLocalDate.until(endLocalDate, ChronoUnit.DAYS);
//        //这个是针对的时间
////        final long betweenDays = Duration.between(startLocalDate, endLocalDate).toDays();
//        //轮训处理
//        for (long stepSize = 0; stepSize <= betweenDays; stepSize++) {
//            //从起始位置增加stepSize
//            final LocalDate localDateTime = startLocalDate.plus(stepSize, ChronoUnit.DAYS);
//            //localDate转换为字符串
//            System.out.println(localDateTime.format(dateTimeFormatter));
//        }
//        List<String> classList=new ArrayList<>();
//        AtomicInteger a= new AtomicInteger(10);
////        classList.add("11");
//        AtomicInteger result=EpOptionalUtils.isTrue(CollectionUtils.isEmpty(classList)).map(x->{
//            a.set(1);
//            System.out.println("11");
//            return a;
//        }).orElseGet(()->{
//            a.set(2);
//            System.out.println("22");
//            return a;
//        });
//        System.out.println(result.get());
//        System.out.println("111222");
//        String date1Str="2024-03-13";
//        String date2Str="2024-03-20";
//        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
//        Date date1=dateFormat.parse(date1Str);
//        Date date2=dateFormat.parse(date2Str);
//
//        int compareResult=date1.compareTo(date2);
//        System.out.println(compareResult);


//        String deliveryNo="CK20240526010-1";
//        String regExp="^CK[0-9]+-[1-9][0-9]*$";
//        System.out.print(Pattern.matches(regExp,null));

        BigDecimal a=new BigDecimal("47.63");
        BigDecimal a1=a.divide(new BigDecimal(3),6,BigDecimal.ROUND_HALF_UP);
        BigDecimal b=new BigDecimal("31.75");
        BigDecimal b1=b.divide(new BigDecimal(2),6,BigDecimal.ROUND_HALF_UP);
        BigDecimal c=new BigDecimal("63.50");
        BigDecimal c1=c.divide(new BigDecimal(4),6,BigDecimal.ROUND_HALF_UP);
        BigDecimal d=new BigDecimal("47.62");
        BigDecimal d1=d.divide(new BigDecimal(3),6,BigDecimal.ROUND_HALF_UP);
        System.out.printf(a1.multiply(new BigDecimal(3)).add(
                b1.multiply(new BigDecimal(6))).add(
                        c1.multiply(new BigDecimal(4))).add(d1.multiply(new BigDecimal(3))).toPlainString());

    }

}