package com.hm.regular;


import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dumingwei on 2017/5/24.
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

        String colorString1 = "#e83f46#ffe83f46";

        Pattern pattern =Pattern.compile(colorRegex);

        Matcher matcher=  pattern.matcher(colorString1);
        while (matcher.find()){
            System.out.println("find"+matcher.group());
        }



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


}
