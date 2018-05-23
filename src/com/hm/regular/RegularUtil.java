package com.hm.regular;


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

    public static void main(String args[]) {
        // System.out.println(getId(PC_ACTIVITY_URL));
        /*System.out.println(isImage(jpg));
        System.out.println(isImage(jpeg));
        System.out.println(isImage(jpg));
        System.out.println(isImage(gif));*/
        System.out.println(stringFilter("*33333##"));
    }

    private static boolean isImage(String url) {
        String pattern = "\\.(jpe?g|png|gif)$";
        Matcher matcher = Pattern.compile(pattern).matcher(url);
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

    public static String stringFilter(String str) {
        // 只允许字母、数字和泰文
        String regEx = "[^a-zA-Z0-9\u0E00-\u0E7F\\s*,：:。.?？@＃％＄《》<>-_...]";//正则表达
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }


}
