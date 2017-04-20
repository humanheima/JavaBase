package com.hm.datetime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * Created by dumingwei on 2017/3/13.
 */
public class DateTimeTest {

    public static void main(String args[]) {

       /* Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);*/
        LocalDateTime from = LocalDateTime.of(2016, Month.DECEMBER, 12, 0, 0);
        LocalDateTime to = LocalDateTime.of(2017, Month.JANUARY, 12, 0, 0);
        Duration duration = Duration.between(from, to);
        System.out.println(duration.toDays());
        System.out.println(duration.toHours());

    }
}