package com.hm.base.interview;

import java.io.UnsupportedEncodingException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * Created by dumingwei on 2017/9/30.
 */
public class InterviewTest {

    public static void main(String[] args) {

        //test3();
        //test5();
        //System.out.println(reverseString("abc"));
        //stringChangeCoding();
        //testCalendar();
        //testDateFormat();
        try {
            testTry();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private static void testTry() throws NullPointerException,CloneNotSupportedException {

        try {
            System.out.println("start try");
            return;
        } finally {
            System.out.println("try finally");
        }

    }

    /**
     * 4、float f=3.4;是否正确？
     * 答:不正确。3.4是双精度数，将双精度型（double）赋值给浮点型（float）属于下转型（down-casting，也称为窄化）
     * 会造成精度损失，因此需要强制类型转换float f =(float)3.4; 或者写成float f =3.4F;。
     */
    private void test1() {
        //float f=3.4;
        float f = 3.4F;
    }

    /**
     * 5、short s1 = 1; s1 = s1 + 1;有错吗?short s1 = 1; s1 += 1;有错吗？
     * 答：对于short s1 = 1; s1 = s1 + 1;由于1是int类型，因此s1+1运算结果也是int 型，需要强制转换类型才能赋值给short型。
     * 而short s1 = 1; s1 += 1;可以正确编译，因为s1+= 1;相当于s1 = (short)(s1 + 1);其中有隐含的强制类型转换。
     */
    private void test2() {
        short s1 = 1;
        //s1 = s1 + 1;

        short s2 = 1;
        s2 += 1;
    }

    private static void test3() {
        String s2 = new StringBuilder("aa").append("va").toString();
        System.out.println(s2.intern() == s2);
        String s1 = new StringBuilder("go").append("od").toString();
        System.out.println(s1.intern() == s1);

    }

    private static void test4() {
        System.out.println(Math.round(11.5));
        System.out.println(Math.round(-11.5));
    }

    /**
     * switch 可以使用 char byte shoat int enum String
     * Character Byte Short Integer
     */
    private static void switchTest() {
        String s = "1";
        switch (s) {
            case "1":
                break;
        }
        DAY day = DAY.FRIDAY;
        switch (day) {
            case FRIDAY:
                break;
            case MONDAY:
                break;
            case SUNDAY:
                break;

        }

        Character a = new Character('c');
        switch (a) {

        }
    }

    enum DAY {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    private static void test5() {
        System.out.println(2 << 3);
        System.out.println(2 >> 3);
        System.out.println(2 >>> 3);
    }

    private static String reverseString(String original) {
        System.out.println(1 >> 2);
        return new StringBuilder(original).reverse().toString();
       /* if (original == null || original.length() <= 1)
            return original;
        return reverseString(original.substring(1)) + original.charAt(0);*/
    }

    private static void stringChangeCoding() {
        String s = "你好";
        try {
            String s1 = new String(s.getBytes("GB2312"), "ISO-8859-1");
            System.out.println(s1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private static void testCalendar() {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.MONTH));    // 0 - 11
        System.out.println(cal.get(Calendar.DATE));
        System.out.println(cal.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal.get(Calendar.MINUTE));
        System.out.println(cal.get(Calendar.SECOND));

        // Java 8
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt.getYear());
        System.out.println(dt.getMonthValue());     // 1 - 12
        System.out.println(dt.getDayOfMonth());
        System.out.println(dt.getHour());
        System.out.println(dt.getMinute());
        System.out.println(dt.getSecond());

        Calendar.getInstance().getTimeInMillis();
        System.currentTimeMillis();
        // Java 8
        Clock.systemDefaultZone().millis();

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.getActualMaximum(Calendar.MONTH));
        System.out.println(calendar.getActualMaximum(Calendar.WEEK_OF_MONTH));
    }

    private static void testDateFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.now();
        System.out.println(date.format(formatter));
    }

    //打印昨天的当前时刻
    private void printYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        System.out.println(calendar.getTime());

        //Java8实现
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);
        System.out.println(yesterday);
    }


}
