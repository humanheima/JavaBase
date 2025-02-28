package com.hm.base.interview.sword_to_offer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dumingwei on 2022/4/16.
 * <p>
 * Desc:剑指offer 48题
 * 最长无重复字符的子串
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * <p>
 * 作者：LeetCode-Solution
 * 链接：<a href="https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/solution/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-l4yo/">...</a>
 * 来源：力扣（LeetCode）
 */
public class Test48 {


    public static void main(String[] args) {

        //method1();

        //method2();

        method3();
    }

    private static void method3() {
        System.out.println("--------------");
        System.out.println(new Test48().lengthOfLongestSubstring4("dvdf"));
        System.out.println(new Test48().lengthOfLongestSubstring4(" "));
        System.out.println(new Test48().lengthOfLongestSubstring4("abcabcbb"));
        System.out.println(new Test48().lengthOfLongestSubstring4("bbbbb"));
        System.out.println(new Test48().lengthOfLongestSubstring4("pwwkew"));
    }

    private static void method2() {
        System.out.println("--------------");
        new Test48().lengthOfLongestSubstring2("dvdf");
        System.out.println(new Test48().lengthOfLongestSubstring2(" "));
        new Test48().lengthOfLongestSubstring2("abcabcbb");
        new Test48().lengthOfLongestSubstring2("bbbbb");
        new Test48().lengthOfLongestSubstring2("pwwkew");
    }

    private static void method1() {
        new Test48().lengthOfLongestSubstring("dvdf");
        new Test48().lengthOfLongestSubstring(" ");
        new Test48().lengthOfLongestSubstring("abcabcbb");
        new Test48().lengthOfLongestSubstring("bbbbb");
        new Test48().lengthOfLongestSubstring("pwwkew");
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return -1;
        }
        if (s.length() == 1) {
            return 1;
        }
        int length = s.length();
        List<List<Character>> stringBuilderList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            List<Character> builder = new ArrayList<>();
            builder.add(s.charAt(i));
            for (int j = i + 1; j < length; j++) {
                char charAt = s.charAt(j);
                if (!builder.contains(charAt)) {
                    builder.add(charAt);
                } else {
                    break;
                }
            }
            stringBuilderList.add(builder);
        }
        int maxLength = 0;
        int index = 0;
        for (int i = stringBuilderList.size() - 1; i >= 0; i--) {
            int length1 = stringBuilderList.get(i).size();
            if (length1 > maxLength) {
                index = i;
                maxLength = length1;
            }
        }

        System.out.println(stringBuilderList.get(index).toString());
        return maxLength;
    }

    /**
     * 这个是比较好的方法，外层循环，每次加step，比+1 效率高。就用这种方式来实现
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int length = s.length();
        List<List<Character>> stringBuilderList = new ArrayList<>();
        int step = 0;
        for (int i = 0; i < length; i += step) {
            step = 1;
            List<Character> builder = new ArrayList<>();
            builder.add(s.charAt(i));
            for (int j = i + 1; j < length; j++) {
                char charAt = s.charAt(j);
                if (!builder.contains(charAt)) {
                    step++;
                    builder.add(charAt);
                } else {
                    break;
                }
            }
            stringBuilderList.add(builder);
        }
        int maxLength = 0;
        int index = 0;
        for (int i = stringBuilderList.size() - 1; i >= 0; i--) {
            int length1 = stringBuilderList.get(i).size();
            if (length1 > maxLength) {
                index = i;
                maxLength = length1;
            }
        }

        System.out.println(stringBuilderList.get(index).toString());
        return maxLength;
    }


    /**
     * 这个是比较好的方法，外层循环，每次加step，比+1 效率高。就用这种方式来实现
     *
     * @param s
     * @return
     */
    public String lengthOfLongestSubstring4(String s) {
        int length = s.length();
        List<Character> result = new ArrayList<>();
        List<List<Character>> stringBuilderList = new ArrayList<>();
        int step = 0;
        for (int i = 0; i < length; i += step) {
            step = 1;
            List<Character> temp = new ArrayList<>();
            temp.add(s.charAt(i));
            for (int j = i + 1; j < length; j++) {
                char charAt = s.charAt(j);
                if (!temp.contains(charAt)) {
                    step++;
                    temp.add(charAt);
                } else {
                    break;
                }
            }
            if (temp.size() > result.size()) {
                result = temp;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Character character : result) {
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    /**
     * 这个暂时不研究，方法挺好。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

}
