package com.hm.algorithm;

/**
 * Created by rjdu on 2016/11/21.
 * 计算 m 的n次幂
 */
public class HotelExcel {

    public static void main(String args[]) {
        String result = expString(2, 3);
        System.out.println(result);
    }

    /**
     * @param bottom 底数
     * @param var    指数
     * @return
     */
    private static String expString(Integer bottom, Integer var) {
        //2的0次幂是1
        String result = "1";
        if (var == 0) {
            return result;
        }
        //循环，每次将result乘以2并保存在result中，最终result就是我们要计算的结果
        int i = 1;
        while (i <= var) {
            //累加计算结果
            result = multiString(bottom, result);
            i++;
        }
        return result;
    }

    /**
     * @param bottom 底数
     * @param str    指数
     * @return
     */
    private static String multiString(Integer bottom, String str) {
        //将字符串转化成字符数组
        char[] c = str.toCharArray();
        //结果可能和原数组一样长或者比原数组长度长1， 2的3次幂是8,2的4次幂就是16了
        char[] result = new char[c.length + 1];
        //temp用来表示是否有进位
        int temp = 0;
        for (int i = c.length - 1; i >= 0; i--) {//从后向前遍历
            //48 是 0对应的ASCII码
            int j = (int) c[i] - 48;
            int all = j * bottom;
            //为什么是i+1呢？，因为最后可能会有进位
            result[i + 1] = ((char) ((all % 10 + temp) % 10 + 48));
            temp = (all + temp) / 10;
        }
        if (temp != 0) {
            //最后如果有进位
            result[0] = (char) (temp + 48);
        }
        //去掉不需要的0
        return String.valueOf(result).replaceAll("\u0000", "");
    }

}
