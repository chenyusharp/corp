package com.eptison.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.text.DateFormatter;

/**
 * Date: 2024/2/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyTest {


    public static void main(String[] args) {

        String startTime = "2024-01-01";
        String endTime = "2024-02-29";
        //计算需要的总的天数
        //创建一个formatter
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //字符串转换为localDate
        final LocalDate startLocalDate = LocalDate.parse(startTime, dateTimeFormatter);
        final LocalDate endLocalDate = LocalDate.parse(endTime, dateTimeFormatter);
        //求两个时间的差的天数(针对日期)
        final long betweenDays = startLocalDate.until(endLocalDate, ChronoUnit.DAYS);
        //这个是针对的时间
//        final long betweenDays = Duration.between(startLocalDate, endLocalDate).toDays();
        //轮训处理
        for (long stepSize = 0; stepSize <= betweenDays; stepSize++) {
            //从起始位置增加stepSize
            final LocalDate localDateTime = startLocalDate.plus(stepSize, ChronoUnit.DAYS);
            //localDate转换为字符串
            System.out.println(localDateTime.format(dateTimeFormatter));
        }

    }

}