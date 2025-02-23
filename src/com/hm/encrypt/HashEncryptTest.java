package com.hm.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Set;

/**
 * 计算hash值，消息摘要
 */
public class HashEncryptTest {

    public static void main(String[] args) {
        printSupportedAlgorithms();
        String input = "Hello Android";
        StringBuilder result = new StringBuilder();

        // 测试不同的算法
        result.append("Input: ").append(input).append("\n");
        String md5 = getHash(input, "MD5");
        result.append("MD5: ").append(md5).append("\n");

        String sha1 = getHash(input, "SHA-1");
        result.append("SHA-1: ").append(sha1).append("\n");
        String sha256 = getHash(input, "SHA-256");
        result.append("SHA-256: ").append(sha256).append("\n");

        System.out.println(result);
    }

    /**
     * 查看支持哪些摘要算法
     */
    public static void printSupportedAlgorithms() {
        Set<String> algorithms = Security.getAlgorithms("MessageDigest");
        for (String algo : algorithms) {
            System.out.println(algo);
        }
    }

    private static String getHash(String input, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] array = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : array) {
                //digest.digest()返回的是字节数组（byte[]），为了便于阅读，通常转换为十六进制字符串
                // 每个字节，最终占用两个字符的长度
                String hex = Integer.toHexString(b & 0xFF);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }

            String result = sb.toString();
            System.out.println("algorithm: " + algorithm + "，输出密文字节数组长度，length = " + array.length + "，bit 位数 " + array.length * 8 + " ，返回的字符串长度 = " + result.length());

            return result;


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "Error: " + algorithm + " not supported";
        }
    }
}
