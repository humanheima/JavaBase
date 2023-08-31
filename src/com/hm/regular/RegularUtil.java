package com.hm.regular;


import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dumingwei on 2017/5/24.
 * 参考链接：https://www.liaoxuefeng.com/wiki/1252599548343744/1255945288020320
 */
public class RegularUtil {

    public static String PC_ACTIVITY_URL = "http://pudong.xun-ao.com/api/pcActivity.php?id=1291&params=1";
    private static String jpg = "http://pudong.xun-ao.com/d/file/daohangguanli/jietou/2016-10-24/6535ef8619484dfb1d2da185bcf58df7.jpg";
    private static String jpeg = "http://pudong.xun-ao.com/d/file/daohangguanli/jietou/2016-10-24/6535ef8619484dfb1d2da185bcf58df7.jpeg";
    private static String png = "http://pudong.xun-ao.com/d/file/daohangguanli/jietou/2016-10-24/6535ef8619484dfb1d2da185bcf58df7.png";
    private static String gif = "http://pudong.xun-ao.com/d/file/daohangguanli/jietou/2016-10-24/6535ef8619484dfb1d2da185bcf58df7.gif";

    static final String PARAM = "[a-zA-Z][a-zA-Z0-9_-]*";
    static final Pattern PARAM_URL_REGEX = Pattern.compile("\\{(" + PARAM + ")\\}");
    private static String REPOSITORY_URL = "https://api.github.com/repos/{owner}/{repo}/";

    private static String colorRegex = "#([0-9a-fA-F]{6}|[0-9a-fA-F]{8})";


    public static void main(String args[]) {
        // System.out.println(getId(PC_ACTIVITY_URL));
        /*System.out.println(isImage(jpg));
        System.out.println(isImage(jpeg));
        System.out.println(isImage(jpg));
        System.out.println(isImage(gif));*/
        //System.out.println(stringFilter("*33333##"));
        //System.out.println(parsePathParameters(REPOSITORY_URL));

        //String colorString1 = "#e83f46#ffe83f46";

        //Pattern pattern = Pattern.compile(colorRegex);

        //Matcher matcher = pattern.matcher(colorString1);
        //while (matcher.find()) {
        //    System.out.println("find" + matcher.group());
        //}


//        System.out.println(isNumber(null));
//        System.out.println(isNumber(""));
//        System.out.println(isNumber("0"));
//        System.out.println(isNumber("10"));
//        System.out.println(isNumber("0.0"));
//        System.out.println(isNumber("0.00"));
//        System.out.println(isNumber("0.01"));
//        System.out.println(isNumber("10.00"));
//        System.out.println(isNumber("100.000"));
//
//        System.out.println(new BigDecimal("1.00").toString());

        //test();
        //testHH();
        //testMatcher();
        //testMatcher1();

        testParentheses();
    }


    private static void testHH() {
        String s = "\uD83D\uDE0A《xxx书名》\uD83D\uDE0A，XXXXX#xx001#";

        String pattern = "\\uD83D\\uDE0A《.*》\\uD83D\\uDE0A";
        Matcher matcher = Pattern.compile(pattern).matcher(s);
        if (matcher.find()) {
            System.out.println(matcher.group());
            //System.out.println(matcher.start());
            //System.out.println(matcher.end());
            System.out.println(s.substring(matcher.start(), matcher.end()));
        }


        //匹配 以#开头，以#结尾，中间可以有任何内容(可以为空)的字符串。
        String pattern2 = "#.*#";
        Matcher matcher2 = Pattern.compile(pattern2).matcher(s);
        //System.out.println(matcher.find());
        if (matcher2.find()) {
            System.out.println(matcher2.group());
            //System.out.println(matcher2.start());
            //System.out.println(matcher2.end());
            System.out.println(s.substring(matcher2.start(), matcher2.end()));
        }
        //return matcher.find();
    }

    private static void testMatcher() {
        String s = "\uD83D\uDE0A《xxx书名》\uD83D\uDE0A，XXXXX##";
        Pattern bookNamePattern = Pattern.compile("\\uD83D\\uDE0A《.*》\\uD83D\\uDE0A");

        Matcher bookNameMatcher = bookNamePattern.matcher(s);
        String bookName = null;
        if (bookNameMatcher.find()) {
            bookName = bookNameMatcher.group(0);
        }
        if (bookName != null) {
            bookName = bookName.replace("\uD83D\uDE0A《", "");
            bookName = bookName.replace("》\uD83D\uDE0A", "");
        }
        if (bookName == null) {
            return;
        }

        System.out.println("bookname = " + bookName);

        String channel = null;
        //匹配 以#开头，以#结尾，中间可以有任何内容(可以为空)的字符串。
        String channelPattern = "#.*#";
        Matcher channelMatcher = Pattern.compile(channelPattern).matcher(s);
        //System.out.println(matcher.find());
        if (channelMatcher.find()) {
            channel = channelMatcher.group(0);
        }
        if (channel != null) {
            channel = channel.replace("#", "");
        }

        if (channel != null && !channel.isEmpty()) {
            System.out.println("channel = " + channel);
        } else {
            System.out.println("channel = null ，或者为空字符串");
        }
    }


    /**
     * 测试分组匹配
     */
    private static void testMatcher1() {
        Pattern p = Pattern.compile("(\\d{3,4})\\-(\\d{7,8})");
        Matcher m = p.matcher("010-12345678");
        if (m.matches()) {
            String g1 = m.group(1);
            String g2 = m.group(2);
            System.out.println(g1);
            System.out.println(g2);
        } else {
            System.out.println("匹配失败!");
        }
    }

    private static Set<String> parsePathParameters(String path) {
        Matcher m = PARAM_URL_REGEX.matcher(path);
        Set<String> patterns = new LinkedHashSet<>();
        while (m.find()) {
            patterns.add(m.group(1));
        }
        return patterns;
    }

    private static boolean isImage(String url) {
        String pattern = "\\.(jpe?g|png|gif)$";
        Matcher matcher = Pattern.compile(pattern).matcher(url);
        return matcher.find();
    }

    /**
     * ? 问号代表前面的字符最多只可以出现一次（0次、或1次）。
     *
     * @param number
     * @return
     */
    private static boolean isNumber(String number) {
        if (number == null) {
            return false;
        }
        String pattern = "^(\\d+)(\\.\\d+)?$";
        Matcher matcher = Pattern.compile(pattern).matcher(number);
        return matcher.find();
    }

    private static String getId(String url) {
        String pattern = "id=[0-9]+";
        Matcher matcher = Pattern.compile(pattern).matcher(url);
        if (matcher.find()) {
            String id = matcher.group();
            return id.replace("id=", "");
        }
        return null;
    }

    private static String stringFilter(String str) {
        // 只允许字母、数字和泰文
        String regEx = "[^a-zA-Z0-9\u0E00-\u0E7F\\s*,：:。.?？@＃％＄《》<>-_...]";//正则表达
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }


    private static void test() {
        //String taskReqString = "[DefaultTaskExecutionRequest{args=[:assembleCommonDebug],projectPath='null'}]";
        String taskReqString = "[DefaultTaskExecutionRequest{args=[:resguardCommonDebug],projectPath='null'}]";

        /**
         *  . 点可以匹配任意一个字符，注意是一个。
         *  * 型号可以匹配任意个字符，包括0个字符。
         *  ? 问号这里表示非贪婪匹配。
         */
        Pattern pattern = Pattern.compile("(assemble|resguard)(.*?Release|.*?Debug)");
        Matcher matcher = pattern.matcher(taskReqString);
        if (matcher.find()) {
            int count = matcher.groupCount();
            for (int i = 0; i <= count; i++) {
                System.out.println(matcher.group(i));
            }
        }

    }

    public static void testParentheses() {
        String input = "This is a string(***hh）hhh";
        String pattern = "（.*）"; // Pattern to match strings with parentheses

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);

        if (matcher.find()) {
            System.out.println("String contains the pattern");
        } else {
            System.out.println("String does not contain the pattern");
        }
    }

}
