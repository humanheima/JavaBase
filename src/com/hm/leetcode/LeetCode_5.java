package com.hm.leetcode;

/**
 * Crete by dumingwei on 2020-03-04
 * Desc:
 * <p>
 * 问题描述
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 解题思路：
 * <p>
 * 定一个maxLength 记录最长的回文子串
 * <p>
 * 很直观的一个想法就是
 * 1. 我先从第一个字符开始，判断[0,1],[0,2],[0,3]...[0,n-1],找出这中间所有字符串中最长的回文字符串。
 * 2. 然后从第2个字符开始，判断[1,2],[1,3],[1,4]...[1,n-1],找出这中间所有字符串中最长的回文字符串。
 * 2. 然后从第3个字符开始，判断[2,3],[2,4],[2,5]...[2,n-1],找出这中间所有字符串中最长的回文字符串。
 * ...
 * 3. 重复上面的步骤，直到倒数第2个字符。[n-2,n-1]。
 */
public class LeetCode_5 {

    public static void main(String[] args) {

        //test1();
        //test2();
        //test3();
        test4();
        test5();

    }

    public static void test1() {
        String s = "babad";
        System.out.println(longestPalindrome(s));
    }

    public static void test2() {
        String s = "cbbd";
        System.out.println(longestPalindrome(s));
    }

    public static void test3() {
        String s = "bb";
        System.out.println(longestPalindrome(s));
    }

    public static void test4() {
        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);
        String s = "iopsajhffgvrnyitusobwcxgwlwniqchfnssqttdrnqqcsrigjsxkzcmuoiyxzerakhmexuyeuhjfobrmkoqdljrlojjjysfdslyvckxhuleagmxnzvikfitmkfhevfesnwltekstsueefbrddxrmxokpaxsenwlgytdaexgfwtneurhxvjvpsliepgvspdchmhggybwupiqaqlhjjrildjuewkdxbcpsbjtsevkppvgilrlspejqvzpfeorjmrbdppovvpzxcytscycgwsbnmspihzldjdgilnrlmhaswqaqbecmaocesnpqaotamwofyyfsbmxidowusogmylhlhxftnrmhtnnljjhhcfvywsqimqxqobfsageysonuoagmmviozeouutsiecitrmkypwknorjjiaasxfhsftypspwhvqovmwkjuehujofiabznpipidhfxpoustquzyfurkcgmioxacleqdxgrxbldcuxzgbcazgfismcgmgtjuwchymkzoiqhzaqrtiykdkydgvuaqkllbsactntexcybbjaxlfhyvbxieelstduqzfkoceqzgncvexklahxjnvtyqcjtbfanzgpdmucjlqpiolklmjxnscjcyiybdkgitxnuvtmoypcdldrvalxcxalpwumfx";
        System.out.println(longestPalindrome(s));
        System.out.println("消耗时间：" + (System.currentTimeMillis() - startTime));
    }

    public static void test5() {
        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        System.out.println(longestPalindrome(s));
        System.out.println("消耗时间：" + (System.currentTimeMillis() - startTime));
    }


    /**
     * 暴力求解法
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length(); j >= i + 1; j--) {
                String tmp = s.substring(i, j);
                if (result.length() > tmp.length()) {
                    break;
                }
                if (isPalindrome1(tmp) && tmp.length() > result.length()) {
                    result = tmp;
                }
            }
        }
        return result;
    }

    /**
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }
        String reverse = new StringBuilder(s).reverse().toString();
        return s.equals(reverse);
    }

    public static boolean isPalindrome1(String numberStr) {
        int length = numberStr.length();
        if (length == 1) {
            return true;
        }
        //获取中间位置
        int middle = length / 2;
        for (int i = 0; i < middle; i++) {
            char left = numberStr.charAt(i);
            char right = numberStr.charAt(length - 1 - i);
            //如果有一个字符不相等，返回false。
            if (left != right) {
                return false;
            }
        }
        return true;
    }


    /**
     * 中心扩展法，还没看懂
     *
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

}
