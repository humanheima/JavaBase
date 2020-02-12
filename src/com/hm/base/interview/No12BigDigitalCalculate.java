package com.hm.base.interview;

/**
 * 剑指offer面试题12相关题目：大位数加减乘法的实现、
 * 解题思路：使用字符串表示数字，转换成数组进行计算。按位相加，然后处理进位
 * 把字符串翻转过来模拟从低位到高位的相加
 *
 * @author GL
 */
public class No12BigDigitalCalculate {

    public static void main(String[] args) {
        String a = "88900988";
        String b = "7878778888";
        System.out.println(bigDigitalSum(a, b));
        System.out.println(bigDigitalSumAnother(a, b));

    }

    /**
     * 两个大数相加，默认两个大数位不带符号位的正数
     *
     * @param a
     * @param b
     * @return
     */
    public static String bigDigitalSum(String a, String b) {

        //翻转两个字符串并转换成数组
        char[] aArray = new StringBuffer(a).reverse().toString().toCharArray();
        char[] bArray = new StringBuffer(b).reverse().toString().toCharArray();
        int aLength = aArray.length;
        int bLength = bArray.length;

        //两个整数相加的最大位数为两个整数中最大位数+1
        int maxLength = aLength > bLength ? aLength : bLength;
        int[] result = new int[maxLength + 1];

        //按照数位对应相加
        for (int i = 0; i < maxLength + 1; i++) {
            //判断当前位是否超过了当前数值的最大位数，如果是用0代替进行运算
            int aInt = i < aLength ? (aArray[i] - '0') : 0;
            int bInt = i < bLength ? (bArray[i] - '0') : 0;
            result[i] = aInt + bInt;
        }

        //处理进位，进位加到相邻的高位上
        for (int i = 0; i < result.length; i++) {
            if (result[i] > 10) {
                result[i + 1] = result[i + 1] + result[i] / 10;
                result[i] = result[i] % 10;
            }
        }

        StringBuffer realResult = new StringBuffer();
        //判断是否有前置0，如果有不会打印出来
        boolean isBeginning = true;
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i] == 0 && isBeginning)
                continue;
            else
                isBeginning = false;
            //从后向前将结果逆转
            realResult.append(result[i]);
        }
        return realResult.toString();
    }

    /**
     * 两个大数相加，默认两个大数位不带符号位的正数
     *
     * @param a
     * @param b
     * @return
     */
    public static String bigDigitalSumAnother(String a, String b) {

        //翻转两个字符串并转换成数组

        int aLength = a.length();
        int bLength = b.length();

        int maxLength = aLength > bLength ? aLength : bLength;

        StringBuilder builderA = new StringBuilder();
        for (int i = 0; i < maxLength - aLength; i++) {
            builderA.append("0");
        }

        a = builderA.append(a).toString();

        StringBuilder builderB = new StringBuilder();
        for (int i = 0; i < maxLength - bLength; i++) {
            builderB.append("0");
        }
        b = builderB.append(b).toString();

        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();

        aLength = aArray.length;
        bLength = bArray.length;


        //两个整数相加的最大位数为两个整数中最大位数+1
        char[] result = new char[maxLength + 1];

        //temp用来表示是否有进位
        int temp = 0;
        //按照数位对应相加
        //todo 这样会把小的一个数变大比如 123 +3456 ，会变成 1230+3456，会导致结果变大
        for (int i = maxLength - 1; i >= 0; i--) {
            //判断当前位是否超过了当前数值的最大位数，如果是用0代替进行运算
            //48 是 0对应的ASCII码
            int aInt = i < aLength ? (aArray[i] - 48) : 0;
            int bInt = i < bLength ? (bArray[i] - 48) : 0;
            int all = aInt + bInt;
            result[i + 1] = ((char) ((all % 10 + temp) % 10 + 48));
            temp = (all + temp) / 10;
        }
        if (temp != 0) {
            result[0] = (char) (temp + 48);
        }

        //去掉空字符
        return String.valueOf(result).replaceAll("\u0000", "");
    }


}

