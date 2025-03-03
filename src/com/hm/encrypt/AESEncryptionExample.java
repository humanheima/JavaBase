package com.hm.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Provider;
import java.security.Security;
import java.util.Base64;
import java.util.Set;

/**
 * 对称加密
 */
public class AESEncryptionExample {

    public static void main(String[] args) throws Exception {

        printSupportedAlgorithms();

        System.out.println();

        String plainText = "Hello, Symmetric Encryption!";

        // 生成AES密钥（128位）
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 可选128、192、256位
        SecretKey secretKey = keyGen.generateKey();

        // 加密
        String encryptedText = encrypt(plainText, secretKey);
        System.out.println("加密后: " + encryptedText);

        // 解密
        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("解密后: " + decryptedText);
    }

    public static void printSupportedAlgorithms() {
        for (Provider provider : Security.getProviders()) {
            System.out.println("Provider: " + provider.getName());
            for (Provider.Service service : provider.getServices()) {
                if (service.getType().equals("KeyGenerator")) {
                    System.out.println("  Algorithm: " + service.getAlgorithm());
                }
            }
        }
    }

    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}