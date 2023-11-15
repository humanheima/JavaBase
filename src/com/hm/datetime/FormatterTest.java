package com.hm.datetime;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by dumingwei on 2023/11/15
 * 测试Formatter类格式化时间
 */
public class FormatterTest {

    StringBuilder builder = new StringBuilder();
    //builder 用来存储格式化后的字符串
    Formatter formatter = new Formatter(builder, Locale.US);

    public static void main(String[] args) {
        FormatterTest test = new FormatterTest();
        //test.test1();
        //test.test2();
        //test.test3();
        //test.test4();
        //test.test5();
        //test.testFormatGenerate();
        //test.testFormatDateTime();
        test.testPercent();
        test.testLineSeparator();
    }

    public void test1() {
        Calendar calendar = Calendar.getInstance();

        String s = String.format("Duke's Birthday: %1$tm %1$te,%1$tY", calendar);
        String s1 = String.format("Duke's Birthday: %1$tm %1$te,%1$tY", calendar.getTimeInMillis());
        //效果一样
        System.out.println(s);
        System.out.println(s1);

    }

    public void test2() {
        Calendar calendar = Calendar.getInstance();
        //下面两行代码表示都使用第一个参数：calendar
        String s1 = String.format("Duke's Birthday: %1$tm %1$te,%1$tY", calendar);
        String s2 = String.format("Duke's Birthday: %1$tm %<te,%<tY", calendar);
        //效果一样
        System.out.println(s1);
        System.out.println(s2);
    }

    public void test3() {
        //没有参数对应格式[argument_index$]，默认每一个百分号后面的格式化字符串对应一个参数。
        //%tm 格式化 calendar1，%te 格式化 calendar2，%tR 格式化 calendar3
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar3 = Calendar.getInstance();
        Calendar calendar4 = Calendar.getInstance();

        String out = String.format("Duke's Birthday: %tm,%te,%tR,%tD", calendar1, calendar2, calendar3, calendar4);
        System.out.println(out);
    }

    public void test4() {
        Calendar calendar1 = Calendar.getInstance();
        String out = String.format("Duke's Birthday:%tD", calendar1);
        String out1 = String.format("Duke's Birthday:%tm/%td/%ty", calendar1, calendar1, calendar1);
        System.out.println(out);
        System.out.println(out1);
    }

    /**
     * %tA 星期几的全称，%ta 星期几的简称，对中文没有影响
     */
    public void test5() {
        Calendar calendar1 = Calendar.getInstance();
        String out = String.format("Duke's Birthday:%tA", calendar1);
        String out1 = String.format("Duke's Birthday:%ta", calendar1);
        System.out.println(out);
        System.out.println(out1);

        //String.format("%1$tA %1$ta", calendar1);
        //System.out.println(builder.toString());

    }

    public void testFormatGenerate() {
        /**
         * 如果要被格式化的参数是null,输出结果是false,
         * 如果参数是一个boolean或者Boolean类型的数据，输出true或者false，其他情况返回true
         */

        String output1 = String.format("%1$b %2$b %3$b", null, true, 124);
        //输出大写的true和false
        String output2 = String.format("%1$B %2$B %3$B", null, true, 124);
        System.out.println(output1);
        System.out.println(output2);

        /**
         * 如果要被格式化的参数是null,输出结果是null,其他情况输出Integer.toHexString(arg.hashCode())
         */
        builder.delete(0, builder.length());
        String output3 = String.format("%1h %2h %3h", null, 1024, 2048);
        //输出大写的NULL
        String output4 = String.format("%1H %2H %3H", null, 1024, 2048);
        System.out.println(output3);
        System.out.println(output4);

        /**
         * 如果要被格式化的参数是null,输出null,如果参数实现了 Formattable 接口（如果要自定义Formatter的‘s’转换说明符的功能，需要实现Formattable接口），
         * 那么会调用arg.formatTo方法，除此之外，返回结果是arg.toString()
         */

        String output5 = String.format("%1$s , %2$s", null, 23);
        System.out.println(output5);
        //输出大写的NULL
        String output6 = String.format("%1$S , %2$S", null, 23);
        System.out.println(output6);

        String output7 = String.format("%1$c %2$c %3$c", 'c', 65, 97);
        System.out.println(output7);

        //输出都是大写
        String output8 = String.format("%1$C %2$C %3$C", 'c', 65, 97);
        System.out.println(output8);

        /**
         * 如果使用‘#’这个flag,八进制的输出结果前面会有一个0，十六进制输出结果前面有一个0x
         * 对于八进制和十六进制，如果 要被各式化的参数是一个负数，那么结果会加上一个2 的n次方的值,
         * 从而成为一个无符号数， n就是对应数据类型的位数，下面的例子中127和-127都是32位的。
         */
        String output9 = String.format("十进制：%1$d 八进制：%1$#o 十六进制小写：%1$#x 十六进制大写：%1$#X", 127);
        System.out.println(output9);

        String output10 = String.format("十进制：%1$d 八进制：%1$#o 十六进制小写：%1$#x 十六进制大写：%1$#X", -127);
        System.out.println(output10);

        String output11 = String.format("%1$d %2$d %1$+d %2$+d", 12700, -12700);
        System.out.println(output11);

        //
        /**
         * %1$ d,%2$ d,$后面跟空格的话，正数前面加空格，负数前面不会加空格
         * %1$08d,%2$08d,输出结果是8位，不够的话前面补0
         */
        String output12 = String.format("%1$ d,%2$ d,%1$08d,%2$08d %1$,8d %2$,8d %1$(8d %2$(8d", 12700, -12700);
        System.out.println(output12);

        /**
         * %1$,8d %2$,8d:输出结果是8位，不够的话前面补空格，","逗号控制输出结果包括特定语言环境下的组分隔符，
         * 12,700  -12,700
         */
        String output13 = String.format("%1$,8d %2$,8d", 12700, -12700);
        System.out.println(output13);

        /**
         * 输出结果会把负值用括号包起来，类似这样，格式化-12,700，输出(12700)
         */
        String output14 = String.format("%1$(d %2$(d", 12700, -12700);
        System.out.println(output14);


        /**-------------BigInteger 转化成八进制或者十六进制-----------------------*/
        BigInteger bigInteger1 = new BigInteger("64", 10);
        BigInteger bigInteger2 = new BigInteger("-64", 10);
        //输出结果：100 -100,+0x40 -0x40,0000000040 -000000040
        String output15 = String.format("%1$o %2$o,%1$+#x %2$+#x,%1$010x %2$010x", bigInteger1, bigInteger2);
        System.out.println(output15);
        /**---------------------------------------------------------*/

        /**--------------float, Float, double and Double,BigDecimal----------------------*/

        //关于flag 'g',默认精度是6.要被各式化的数字的绝对值 m;
        //如果0.0001<=m<=10的precision次方，就把结果格式化成十进制格式
        //如果m<0.0001 or m>10的precision次方，就把结果用科学计数法表示。

        float a = 1220000;
        float b = 100;
        /**
         * ‘e’,’E’：结果是一个用科学计数法表示的十进制数字
         * ‘g’,’G’ ：结果是一个用十进制表示的浮点数或者科学计数法表示的浮点数，看哪个表示的更短
         *
         * ‘f’：结果是一个用十进制表示的浮点数
         */
        String output16 = String.format("%1$e %1$.4E,%1$g %1$.4g,%1$.3f", a);
        System.out.println("output16 =" + output16);

        String output17 = String.format("%1$e %1$.4e,%1$g %1$.4g,%1$.3f", b);
        System.out.println("output17 =" + output17);

        String output18 = String.format("%1$e %1$.4e,%1$g %1$.4g,%1$.3f", -1220000f);

        System.out.println("output18=" + output18);

        String output19 = String.format("%1$e %1$.4e,%1$g %1$.4g,%1$.3f", -100f);
        System.out.println("output19=" + output19);

        //关于BigDecimal
        BigDecimal bigDecimal1 = new BigDecimal("1220000");
        BigDecimal bigDecimal2 = new BigDecimal("-1220000");
        BigDecimal bigDecimal3 = new BigDecimal("0");
        String output20 = String.format("%1$e %1$.4e,%1$g %1$.4g,%1$.3f", bigDecimal1);
        System.out.println("output20=" + output20);

        String output21 = String.format("%1$e %1$.4e,%1$g %1$.4g,%1$.3f", bigDecimal2);
        System.out.println("output21=" + output21);

        String output22 = String.format("%1$e %1$.4e,%1$g %1$.4g,%1$.3f", bigDecimal3);
        System.out.println("output22=" + output22);
    }

    /**
     * 测试日期格式化
     */
    public void testFormatDateTime() {
        Date date = new Date();
        long time = date.getTime();

        /**
         * output=19:05:30 下午
         */
        String output = String.format("%1$tH:%1$tM:%1$tS %1$tp", time);
        System.out.println("output=" + output);

        /**
         * output1=19:05
         */
        String output1 = String.format("%1$tR", time);
        System.out.println("output1=" + output1);

        /**
         * output2=19:05:30
         */
        String output2 = String.format("%1$tT", time);
        System.out.println("output2=" + output2);

        /**
         * output3=07:05:30 下午
         */
        String output3 = String.format("%1$tr", time);
        System.out.println("output3=" + output3);

        /**
         * output4=2023-11-15 十一月 星期三
         */
        String output4 = String.format("%1$tY-%1$tm-%1$td %1$tB %1$ta", time);
        System.out.println("output4=" + output4);


        /**
         * output5=11/15/23
         */
        String output5 = String.format("%1$tD", time);
        System.out.println("output5=" + output5);

        /**
         * output6=2023-11-15
         */
        String output6 = String.format("%1$tF", time);
        System.out.println("output6=" + output6);

        /**
         * output7=星期三 十一月 15 19:08:52 CST 2023
         */
        String output7 = String.format("%1$tc", time);
        System.out.println("output7=" + output7);
    }

    /**
     * 测试百分号
     */
    public void testPercent() {
        String output = String.format("%1$d%%100", 12);
        System.out.println(output);
    }

    /**
     * 测试换行
     */
    public void testLineSeparator() {
        String output = String.format("%1$s%ngood afternoon", "Hello world");
        System.out.println(output);
    }

}
