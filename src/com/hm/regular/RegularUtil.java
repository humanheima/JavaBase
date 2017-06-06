package com.hm.regular;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.HashMap;
import java.util.Map;
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
        System.out.println(isImage(jpg));
        System.out.println(isImage(jpeg));
        System.out.println(isImage(jpg));
        System.out.println(isImage(gif));
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


}
