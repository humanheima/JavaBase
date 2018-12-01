package com.hm.base.interview.sword_to_offer;

/**
 * 把字符串转化成整数
 * 参考博客地址：https://blog.csdn.net/derrantcm/article/details/46811799
 * <p>
 * Int值的范围-2^31(-2147483648) 到 2^31-1(+2147483647)
 * <p>
 * Integer类中整数最小值和最大值的定义
 * public static final int   MIN_VALUE = 0x80000000;
 * public static final int   MAX_VALUE = 0x7fffffff;
 * <p>
 * 加号'+' ASCII值是43，减号'-' ASCII值是45
 * <p>
 * 数字 0-9的ASCII取值范围是48-57
 * <p>
 * ASCII对照表 http://tool.oschina.net/commons?type=4
 */
public class StringToInt {

    public static void main(String[] args) {
        /*System.out.println(stringToInt("123"));
        System.out.println(stringToInt("+123"));
        System.out.println(stringToInt("-123"));
        //System.out.println(stringToInt("1a123"));
        System.out.println(stringToInt("+2147483647"));
        System.out.println(stringToInt("-2147483647"));
        System.out.println(stringToInt("-2147483648"));
        //System.out.println(stringToInt("+2147483648"));//超过最大的整数*/

        /**
         * 使用第二种方法
         */
        System.out.println(parseInt("-0"));
        System.out.println(parseInt("+0"));
        System.out.println(parseInt("0"));
        System.out.println(parseInt("123"));
        System.out.println(parseInt("+123"));
        System.out.println(parseInt("-123"));
        //System.out.println(stringToInt("1a123"));
        System.out.println(parseInt("+2147483647"));
        System.out.println(parseInt("-2147483647"));
        System.out.println(parseInt("-2147483648"));
        //System.out.println(parseInt("+2147483648"));//超过最大的整数
    }


    public static int stringToInt(String numberString) {
        if (numberString == null || numberString.isEmpty()) {
            throw new NumberFormatException(numberString);
        }

        char first = numberString.charAt(0);
        if (first == '-') {
            return parseString(numberString, 1, false);
        } else if (first == '+') {
            return parseString(numberString, 1, true);

        } else if (first >= '0' && first <= '9') {
            return parseString(numberString, 0, true);

        } else {
            throw new NumberFormatException(numberString);
        }
    }

    private static int parseString(String numberString, int index, boolean positive) {
        if (index >= numberString.length()) {
            throw new NumberFormatException(numberString);
        }
        int result;
        long temp = 0;
        while (index < numberString.length()) {
            if (isDigit(numberString.charAt(index))) {
                temp = temp * 10 + numberString.charAt(index) - '0';
                if (temp > 0x8000_0000L) {
                    throw new NumberFormatException(numberString);
                }
                index++;
            } else {
                throw new NumberFormatException(numberString);
            }
        }
        if (positive) {
            if (temp == 0x8000_0000L) {
                throw new NumberFormatException(numberString);
            } else {
                result = (int) temp;
            }
        } else {
            result = (int) -temp;
        }

        return result;
    }

    /**
     * 判断是否是数字
     *
     * @param c 字符
     * @return true 是数字，false 不是数字
     */
    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }


    /**
     * Integer类中parseInt方法的简化版
     * <p>
     * {@link Integer#parseInt(String)}
     *
     * @param s 整数字符串
     * @return
     */
    public static int parseInt(String s) throws NumberFormatException {

        if (s == null) {
            throw new NumberFormatException("s == null");
        }
        /**
         * 注意下面的计算中，result的值都是小于等于0的。
         */
        int result = 0;
        boolean negative = false;//标志是否是负数
        int i = 0;
        int len = s.length();
        int limit = -Integer.MAX_VALUE;
        /**
         * multmin的作用是用来防止越界
         *
         * multmin = -214748364
         *
         * multmin的绝对值乘以10都会超过整数的取值范围。
         */
        int multmin;
        int digit;
        if (len > 0) {
            char firstChar = s.charAt(0);
            if (firstChar < '0') {//可能是'+'，或者'-'
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;//整数的最小取值范围(-2147483648)
                } else if (firstChar != '+') {
                    throw new NumberFormatException("For input string: \"" + s + "\"");
                }
                if (len == 1) {//就一个正负号
                    throw new NumberFormatException("For input string: \"" + s + "\"");
                }
                i++;
            }
            multmin = limit / 10;//multmin = -214748364
            while (i < len) {
                //向负数方向累加，避免在Integer.MAX_VALUE附近的惊喜。
                digit = charToDigit(s.charAt(i++));
                if (digit < 0) {
                    throw new NumberFormatException("For input string: \"" + s + "\"");
                }
                if (result < multmin) {//如果result小于multmin的话，result再乘以10就越界了。
                    throw new NumberFormatException("For input string: \"" + s + "\"");
                }
                result *= 10;
                if (result < limit + digit) {//如果result小于limit + digit的话，那么result再减去digit就越界了
                    throw new NumberFormatException("For input string: \"" + s + "\"");
                }
                result -= digit;
            }
        } else {
            throw new NumberFormatException("For input string: \"" + s + "\"");
        }


        return negative ? result : -result;
    }

    /**
     * 如果字符范围是'0'-'9'返回 c-'9'的结果。否则返回-1
     *
     * @param c 字符
     * @return 如果字符范围是'0'-'9'返回 c-'9'的结果。否则返回-1
     */
    private static int charToDigit(char c) {
        int result = -1;
        if ('0' <= c && c <= '9') {
            result = c - '0';
        }
        return result;
    }


}
