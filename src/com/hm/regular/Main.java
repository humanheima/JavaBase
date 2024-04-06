package com.hm.regular;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        String url = "https://www.qidian.com"; // 你的 URL
        //String url = "https://appapi.www.hongxiu.com"; // 你的 URL
        //String url = "https://www.hongxiu.com"; // 你的 URL
        //String url = "https://appapi.hongxiu.com/api/v3/book/info?bookId=17353456504825904&screen=1"; // 你的 URL

        URL parsed = new URL(url);
        /**
         * 匹配规则
         * 1 .hongxiu.com
         * 2 .qidian.com
         * 3 .yuewen.com
         * 4 .qq.com
         */
        String pattern = ".*\\.(hongxiu\\.com|qidian\\.com|yuewen\\.com|qq\\.com)";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(parsed.getHost());
        if (matcher.matches()) {
            System.out.println("URL matches pattern: " + pattern);
        } else {
            System.out.println("Error URL does not match pattern: " + pattern);
        }
    }
}