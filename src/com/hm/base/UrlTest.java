package com.hm.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumingwei on 2018/5/25 0025.
 */
public class UrlTest {

    public static void main(String[] args) {
        /*String url = "http://192.168.0.19:8888/cas/login";
        String encodeUrl = null;
        try {
            encodeUrl = URLEncoder.encode(url, Charset.forName("UTF-8").toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(encodeUrl);*/

        List<String> list = new ArrayList<>(1000);

        list.subList(500, list.size());


    }
}
