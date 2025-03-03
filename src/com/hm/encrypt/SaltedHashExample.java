package com.hm.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 散列函数加密，加盐操作。
 */
public class SaltedHashExample {

    // 生成随机盐
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16]; // 16字节的盐
        random.nextBytes(salt);
        // 将字节数组转为 Base64 字符串，便于存储
        return Base64.getEncoder().encodeToString(salt);
    }

    // 计算加盐后的 SHA-256 哈希值
    public static String hashWithSalt(String password, String salt) throws NoSuchAlgorithmException {
        // 将盐和密码拼接
        String saltedInput = salt + password;

        // 使用 SHA-256 算法
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(saltedInput.getBytes());

        // 将字节数组转为十六进制字符串
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // 验证密码
    public static boolean verifyPassword(String inputPassword, String storedSalt, String storedHash) 
            throws NoSuchAlgorithmException {
        // 用输入的密码和存储的盐重新计算哈希值
        String computedHash = hashWithSalt(inputPassword, storedSalt);
        // 比较计算出的哈希值与存储的哈希值
        return computedHash.equals(storedHash);
    }

    public static void main(String[] args) {
        try {
            // 模拟用户注册
            String password = "password123";
            String salt = generateSalt();
            String hashedPassword = hashWithSalt(password, salt);

            System.out.println("原始密码: " + password);
            System.out.println("生成的盐: " + salt);
            System.out.println("加盐后的哈希值: " + hashedPassword);

            // 模拟用户登录验证
            String inputPassword = "password123"; // 用户输入的密码
            boolean isValid = verifyPassword(inputPassword, salt, hashedPassword);
            System.out.println("密码验证结果: " + (isValid ? "通过" : "失败"));

            // 测试错误密码
            String wrongPassword = "password456";
            boolean isValidWrong = verifyPassword(wrongPassword, salt, hashedPassword);
            System.out.println("错误密码验证结果: " + (isValidWrong ? "通过" : "失败"));

        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 算法不可用: " + e.getMessage());
        }
    }
}