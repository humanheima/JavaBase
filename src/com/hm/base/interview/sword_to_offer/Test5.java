package com.hm.base.interview.sword_to_offer;

/**
 * Created by dmw on 2018/11/17.
 * Desc: 把字符串中的空格替换为 %20
 */
public class Test5 {

    public static void main(String[] args) {

        char[] chars = new char[50];
        String happyString = "We are happy.";

        char[] happyStringCharArray = happyString.toCharArray();
//        for (int i = 0; i < happyStringCharArray.length; i++) {
//            chars[i] = happyStringCharArray[i];
//        }

        System.arraycopy(happyStringCharArray, 0, chars, 0, happyStringCharArray.length);

        int resultLength = replaceBlank(chars, happyString.length());

        for (int i = 0; i < resultLength; i++) {
            System.out.print(chars[i]);
        }
        System.out.println();

        System.out.println("--------------");
        Test5 test5 = new Test5();
        System.out.println(test5.replaceSpace(happyString));
    }


    public String replaceSpace(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();

        int lastIndex = s.length() - 1;
        for (int i = 0; i <= lastIndex; i++) {
            char charAt = s.charAt(i);
            if (charAt == ' ') {
                stringBuilder.append('%');
                stringBuilder.append('2');
                stringBuilder.append('0');
            } else {
                stringBuilder.append(charAt);
            }
        }
        return stringBuilder.toString();
    }


    /**
     * @param chars      字符数组
     * @param usedLength 字符数组中字符占据的长度
     * @return
     */
    public static int replaceBlank(char[] chars, int usedLength) {
        // 判断输入是否合法
        if (chars == null || chars.length < usedLength) {
            return -1;
        }

        // 统计字符数组中的空白字符数
        int whiteCount = 0;
        for (int i = 0; i < usedLength; i++) {
            if (chars[i] == ' ') {
                whiteCount++;
            }
        }

        // 如果没有空白字符就不用处理
        if (whiteCount == 0) {
            return usedLength;
        }

        // 计算转换后的字符长度是多少
        int targetLength = whiteCount * 2 + usedLength;
        if (targetLength > chars.length) { // 如果转换后的长度大于数组的最大长度，直接返回失败
            return -1;
        }

        int resultLength = targetLength; // 保存长度结果用于返回

        usedLength--; // 从后向前，第一个开始处理的字符
        targetLength--; // 处理后的字符放置的位置

        // 字符中有空白字符，一直处理到所有的空白字符处理完
        while (usedLength >= 0 && usedLength < targetLength) {
            // 如是当前字符是空白字符，进行"%20"替换
            if (chars[usedLength] == ' ') {
                chars[targetLength--] = '0';
                chars[targetLength--] = '2';
                chars[targetLength--] = '%';
            } else { // 否则移动字符
                chars[targetLength--] = chars[usedLength];
            }
            usedLength--;
        }

        return resultLength;
    }
}
