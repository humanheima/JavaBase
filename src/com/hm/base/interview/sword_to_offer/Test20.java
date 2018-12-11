package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2018/12/4
 * <p>
 * Desc:表示数值的字符串。请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * <p>
 * 解题思路：在数值之前可能有一个字符表示正负的’-‘或者’+’。接下来是若干个0到9的数位表示数值的整数部分（在某些小数里可能没有数值的整数部分）。
 * 如果数值是一个小数，那么在小数点后面可能会有若干个0到9的数位表示数值的小数部分。
 * 如果数值用科学计数法表示，接下来是一个’e’或者‘E’，以及紧跟着的一个整数（可以有正负号）表示指数。
 * 判断一个字符串是否符合上述模式时，首先看第一个字符是不是正负号。如果是，在字符串上移动一个字符，继续扫描剩余的字符串中0到9的数位。
 * 如果是一个小数，则将遇到小数点。另外，如果是用科学计数法表示的数值，在整数或者小数的后面还有可能遇到’e’或者’E’。
 * <p>
 * 参考链接：https://blog.csdn.net/derrantcm/article/details/46825671
 * <p>
 * 测试用例 1.
 * 测试用例 1..
 * 测试用例 .1
 * 测试用例 + 或者 -
 * 测试用例 1.1e
 * 测试用例 1.1e
 */
public class Test20 {

    public static void main(String[] args) {

        //System.out.println(100.);
        //System.out.println(.1);
        //System.out.println(+1.e3);
        //System.out.println(2E-3);
        System.out.println(isNumeric("+"));
        System.out.println(isNumeric("100"));
        System.out.println(isNumeric("+100"));
        System.out.println(isNumeric("-100"));
        System.out.println(isNumeric("-100."));
        System.out.println(isNumeric("-100.1e2"));
        System.out.println(isNumeric("-.1e2"));
        System.out.println(isNumeric("-.1e"));
        System.out.println(isNumeric("-.e"));
        System.out.println(isNumeric("1.234E"));

    }

    public static boolean isNumeric(String string) {
        if (string == null || string.length() < 1) {
            return false;
        }
        int index = 0;
        if (string.charAt(index) == '+' || string.charAt(index) == '-') {
            index++;
        }
        if (index >= string.length()) {
            return false;
        }
        boolean numeric = true;
        //扫描整数部分
        index = scanDigits(string, index);
        //还没有到达字符串末尾
        if (index < string.length()) {
            if (string.charAt(index) == '.') {
                //如果是小数点，移动到下一个位置
                index++;
                //扫描小数部分
                index = scanDigits(string, index);
                if (index >= string.length()) {
                    numeric = true;
                } else if (string.charAt(index) == 'e' || string.charAt(index) == 'E') {
                    //扫描指数部分
                    numeric = isExponential(string, index);
                } else {
                    numeric = false;
                }
            } else if (string.charAt(index) == 'e' || string.charAt(index) == 'E') {
                numeric = isExponential(string, index);
            } else {
                numeric = false;
            }
            return numeric;
        } else {
            return true;
        }
    }

    /**
     * 判断是否是科学计数法的结尾部分 如E5，e5，E+5，e-5，e(E)后面接整数
     *
     * @param string
     * @param index
     * @return
     */
    private static boolean isExponential(String string, int index) {
        if (index >= string.length()) {
            return false;
        }
        index++;
        if (index >= string.length()) {
            return false;
        }
        if (string.charAt(index) == '+' || string.charAt(index) == '-') {
            index++;
        }
        if (index >= string.length()) {
            return false;
        }
        index = scanDigits(string, index);

        return index >= string.length();
    }

    /**
     * 扫描字符串部分的数字部分
     *
     * @param string
     * @param index
     * @return
     */
    private static int scanDigits(String string, int index) {
        while (index < string.length() && string.charAt(index) >= '0' && string.charAt(index) <= '9') {
            index++;
        }
        return index;
    }


}
