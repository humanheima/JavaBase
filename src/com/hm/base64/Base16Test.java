package com.hm.base64;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.math.BigInteger;

/**
 * Created by dumingwei on 2023/09/24
 * 测试生成base16字符串和将base16字符串转换为字符串
 */
public class Base16Test {

    public static void main(String args[]) {

        //String text = "Base64 finally in Java 8!";
        String input = "854046041117";
        // 将字符串转换为字节数组
        byte[] bytes = input.getBytes();

        // 将字节数组转换为BigInteger对象
        BigInteger bigInteger = new BigInteger(1, bytes);

        // 将BigInteger对象转换为base16表示的字符串
        String base16 = bigInteger.toString(16);

        System.out.println(base16);

        base16ToString(base16);

    }

    public static void base16ToString(String base16) {
        // Base16 data as a byte array
        byte[] base16Data = DatatypeConverter.parseHexBinary(base16);

        // Convert base16 data to string
        String result = new String(base16Data);

        System.out.println(result); // Output: Hello World
    }

}