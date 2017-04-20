package com.hm.base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by dumingwei on 2017/3/13.
 */
public class Base64Test {

    public static void main(String args[]) {

        String text = "Base64 finally in Java 8!";
        String encoded = Base64
                .getEncoder()
                .encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println(encoded);

        String decode=new String(Base64.getDecoder().decode(encoded),StandardCharsets.UTF_8);
        System.out.println(decode);

    }
}