package com.hm.base.interview;

/**
 * Crete by dumingwei on 2020-02-12
 * Desc:
 * <p>
 * 参考链接：
 * https://blog.csdn.net/Koala_Tree/article/details/78287547
 * https://blog.csdn.net/weixin_39916039/article/details/81215798
 */
public class FindMissingNumber {

    public static void main(String[] args) {
        int[] a = {1, 2, 0, 3, 4, 6};
        System.out.println(missingNumber(a));
    }


    public static int missingNumber(int[] nums) {
        int a = 0;
        int i = 0;
        for (i = 0; i < nums.length; i++) {
            a = a ^ i ^ nums[i];
        }
        System.out.println(i);
        return a ^ i;
    }

}
