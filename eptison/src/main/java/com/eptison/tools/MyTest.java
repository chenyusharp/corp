package com.eptison.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Date: 2024/2/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyTest {


    public static void main(String[] args) {

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
        List<String> classList=new ArrayList<>();
        AtomicInteger a= new AtomicInteger(10);
//        classList.add("11");
        AtomicInteger result=EpOptionalUtils.isTrue(CollectionUtils.isEmpty(classList)).map(x->{
            a.set(1);
            System.out.println("11");
            return a;
        }).orElseGet(()->{
            a.set(2);
            System.out.println("22");
            return a;
        });
        System.out.println(result.get());
        System.out.println("111222");
    }

}