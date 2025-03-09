package com.hm.base.interview.android;

/**
 * 汉明距离
 */
public class HammingDistance {

    // 主函数测试
    public static void main(String[] args) {
        String str1 = "10110";
        String str2 = "10011";

        try {
            int result = hammingDistance(str1, str2);
            System.out.println("字符串 '" + str1 + "' 和 '" + str2 + "' 的汉明距离是: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("错误: " + e.getMessage());
        }

        System.out.println("======================");
        main2(args);
    }


    // 计算两个字符串的汉明距离
    public static int hammingDistance(String str1, String str2) {
        // 检查输入是否有效
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException("输入字符串不能为 null");
        }
        if (str1.length() != str2.length()) {
            throw new IllegalArgumentException("两个字符串长度必须相等");
        }

        // 计算汉明距离
        int distance = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }


    public static void main2(String[] args) {
        int x = 1;  // 二进制: 001
        int y = 4;  // 二进制: 100
        int result = hammingDistance(x, y);
        System.out.println("数字 " + x + " 和 " + y + " 的汉明距离是: " + result);
    }

    /**
     * 5. 扩展：二进制数的汉明距离
     * 如果输入是两个整数，可以先将它们转为二进制字符串，再计算汉明距离。以下是扩展实现：
     * @param x
     * @param y
     * @return
     */
    // 计算两个整数的汉明距离
    public static int hammingDistance(int x, int y) {
        // 使用异或操作找出不同的位。当且仅当两个输入值不同时，输出为真（1）；输入值相同时，输出为假（0）。
        int xor = x ^ y;
        int distance = 0;

        // 异或的计算
        // 统计二进制中 1 的个数
        while (xor != 0) {
            distance += xor & 1; // 检查最低位是否为 1
            xor >>= 1;           // 右移一位
        }
        return distance;
    }


}