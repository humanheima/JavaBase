package com.hm.network;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by dumingwei on 2017/6/11.
 */
public class URLDecoderTest {

    public static void main(String[] args) {
        try {
            String keyworkd= URLDecoder.decode("%E7%96%AF%E7%8B%82","UTF-8");
            System.out.println(keyworkd);
            String urlStr= URLEncoder.encode("疯狂Android讲义","UTF-8");
            System.out.println(urlStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
