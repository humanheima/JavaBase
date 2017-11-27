package com.hm.base;

/**
 * Created by dumingwei on 2017/10/2.
 * 正数的反码补码都是本身
 * 负数的反码：符号位保持不变，其余各位，按位取反
 * 负数的补码：就是反码+1
 * <p>
 * 正数取非值 以4为例
 * 原码     0000 0100
 * 按位取反 1111 1011
 * 减去1    1111 1010
 * 除符号位按位取反 1000 0101 =-5
 * <p>
 * 负数取非值 以-5为例
 * 原码 1000 0101
 * 取反码，符号位不变 1111 1010
 * 取补码            1111 1011
 * 按位取反          0000 0100 =4
 */
public class ShiftTest {

    public static void main(String[] args) {
        // System.out.println(3 + ((9 - 3) >> 1));
        System.out.println(0);
        System.out.println(~-5);
    }
}
