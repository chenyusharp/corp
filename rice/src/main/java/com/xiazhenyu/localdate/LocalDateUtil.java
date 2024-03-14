package com.xiazhenyu.localdate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import org.apache.tomcat.jni.Local;

/**
 * Date: 2023/1/1
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class LocalDateUtil {


    public static void main(String[] args) {
//        LocalDate localDate = LocalDate.now();
//        LocalTime localTime = LocalTime.now();
//        LocalDateTime localDateTime = LocalDateTime.now();

//        LocalDateTime localDateTime = LocalDateTime.now();
//        final LocalDate localDate = localDateTime.toLocalDate();
//        final LocalTime localTime = localDateTime.toLocalTime();

//        LocalDate localDate = LocalDate.of(2023, 1, 1);
//        final LocalTime localTime = LocalTime.of(22, 13, 1);
//        final LocalDateTime localDateTime = LocalDateTime.of(2023, 1, 1, 22, 13, 1);
//        final LocalDateTime localDateTime1 = LocalDateTime.of(localDate, localTime);

//        final LocalDateTime localDateTime = LocalDateTime.parse("2023-01-01T22:13:01");
//        final LocalDate localDate = LocalDate.parse("2023-01-01");
//        final LocalTime localTime = LocalTime.parse("22:13:01");

        //一个当前的时间
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        dateTimeFormatter.format(LocalDateTime.now());



//
        System.out.println(dateTimeFormatter.format(LocalDateTime.now()));

        final LocalDateTime localDateTime = LocalDateTime.parse("2023-01-01 22:13:01", dateTimeFormatter);

        System.out.println(localDateTime);

//        final LocalDateTime localDateTime = LocalDateTime.of(2023, 01, 01, 22, 23, 01);
//        System.out.println(localDateTime);
//        final LocalDateTime localDateTime1 = localDateTime.plusDays(5).minusHours(3);
//        System.out.println(localDateTime1);
//        final LocalDateTime localDateTime2 = localDateTime1.minusMonths(1);
//        System.out.println(localDateTime2);

//        final LocalDateTime localDateTime = LocalDateTime.of(2023, 01, 01, 22, 25, 01);
//        System.out.println(localDateTime);
//
//        final LocalDateTime localDateTime1 = localDateTime.withDayOfMonth(31);
//        System.out.println(localDateTime1);
//
//        final LocalDateTime localDateTime2 = localDateTime1.withMonth(9);
//        System.out.println(localDateTime2);

//        final LocalDateTime localDateTime = LocalDate.now().withDayOfMonth(1).atStartOfDay();
//        System.out.println(localDateTime);
//        final LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
//        System.out.println(lastDay);
//
//        final LocalDate nextMonthFirstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
//        System.out.println(nextMonthFirstDay);
//
//        final LocalDate firstWeekday = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
//        System.out.println(firstWeekday);

//        final LocalDateTime localDateTime = LocalDateTime.now();
//        final LocalDateTime target = LocalDateTime.of(2019, 11, 23, 8, 15, 23);
//        System.out.println(localDateTime.isBefore(target));
//
//        System.out.println(LocalDate.now().isBefore(LocalDate.of(2019, 11, 23)));
//
//        System.out.println(LocalTime.now().isAfter(LocalTime.parse("08:13:00")));

        final LocalDateTime start = LocalDateTime.of(2023, 1, 1, 23, 16, 1);
        final LocalDateTime end = LocalDateTime.of(2023, 2, 1, 8, 12, 34);
        final Duration duration = Duration.between(start, start);
        System.out.println(duration);

        final Period period = LocalDate.of(2023, 1, 1).until(LocalDate.of(2023, 2, 22));
        System.out.println(period.getDays());

        final LocalTime localTime_start = LocalTime.of(12, 34, 44);
        final LocalTime localTime_end = LocalTime.of(14, 23, 55);
        System.out.println(Duration.between(localTime_start,localTime_end));



    }

}