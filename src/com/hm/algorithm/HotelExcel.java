package com.hm.algorithm;

/**
 * Created by rjdu on 2016/11/21.
 * 计算 m 的n次幂
 */
public class HotelExcel {

    public static void main(String args[]) throws Exception {
        String result = expString(2, 6);
        System.out.println(result);
    }

    private static String expString(Integer bottom, Integer var) {
        int i = 0;
        String str = "1";
        while (i < var) {
            str = multiString(str, bottom);
            i++;
        }
        return str;
    }

    private static String multiString(String str, Integer var) {
        char[] c = str.toCharArray();
        //结果可能和原数组一样长或者比原数组长度长1， 2的3次幂是8,2的4次幂就是16了
        char[] result = new char[c.length + 1];
        //temp用来表示是否有进位
        int temp = 0;
        for (int i = c.length - 1; i >= 0; i--) {
            //48 是 0对应的ASCII码
            int j = (int) c[i] - 48;
            int all = j * var;
            result[i + 1] = ((char) ((all % 10 + temp) % 10 + 48));
            temp = (all + temp) / 10;
        }
        if (temp != 0) {
            //如果有进位
            result[0] = (char) (temp + 48);
        }
        System.out.println(String.valueOf(result));
        //去掉不需要的0
        return String.valueOf(result).replaceAll("\u0000", "");
    }

}
