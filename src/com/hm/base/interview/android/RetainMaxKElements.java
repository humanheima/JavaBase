package com.hm.base.interview.android;

import com.hm.write.RetainKElements;

/**
 * 有序数组，最多保留K个重复元素.md
 */
public class RetainMaxKElements {

    // 测试代码
    public static void main(String[] args) {
        RetainMaxKElements solution = new RetainMaxKElements();

        test1(solution);

        //test2(solution);

        //test3(solution);
    }

    private static void test1(RetainMaxKElements solution) {
        // 测试用例1
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int len1 = solution.removeDuplicates(nums1, k1);
        System.out.println("Length: " + len1);  // 输出 5

        int len2 = new RetainKElements().test(nums1,k1);

        System.out.println("Length: " + len2);


        for (int i = 0; i < len1; i++) {
            System.out.print(nums1[i] + " ");  // 输出 1 1 2 2 3
        }
        System.out.println();
    }

    private static void test3(RetainMaxKElements solution) {
        // 测试用例3
        int[] nums3 = {1, 2, 3, 4};
        int k3 = 2;
        int len3 = solution.removeDuplicates(nums3, k3);
        System.out.println("Length: " + len3);  // 输出 4
        for (int i = 0; i < len3; i++) {
            System.out.print(nums3[i] + " ");  // 输出 1 2 3 4
        }
    }

    private static void test2(RetainMaxKElements solution) {
        // 测试用例2
        int[] nums2 = {1, 1, 1, 1};
        int k2 = 1;
        int len2 = solution.removeDuplicates(nums2, k2);
        System.out.println("Length: " + len2);  // 输出 1
        for (int i = 0; i < len2; i++) {
            System.out.print(nums2[i] + " ");  // 输出 1
        }
        System.out.println();
    }


    public int removeDuplicates(int[] nums, int k) {
        // 处理特殊情况
        if (nums == null || nums.length == 0 || k <= 0) {
            return 0;
        }

        // 如果k大于等于数组长度，直接返回原长度
        if (k >= nums.length) {
            return nums.length;
        }

        // writePos表示结果数组的写入位置
        int writePos = 1;
        // count记录当前元素出现的次数
        int count = 1;

        // 从第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                // 如果当前元素与前一个相同
                count++;
                // 如果计数不超过k，可以继续写入
                if (count <= k) {
                    nums[writePos] = nums[i];
                    writePos++;
                }
            } else {
                // 遇到新元素，重置计数
                count = 1;
                //覆盖多余的元素。比如 {1, 1, 1, 3, 3} , k = 2;必须用 3 覆盖 index = 2 位置上的 1
                nums[writePos] = nums[i];
                writePos++;
            }
        }

        return writePos;
    }

}