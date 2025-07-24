package com.hm.write;

public class RetainKElements {

    public static void main(String[] args) {

        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k = 2;
        RetainKElements retainKElements = new RetainKElements();
        System.out.println(retainKElements.test(nums1, k));

    }


    /**
     * 第一个元素总是保留的
     *
     * @param nums
     * @param k
     * @return
     */
    public int test(int[] nums, int k) {
        if (nums == null || nums.length < k || k <= 0) {
            return 0;
        }

        int pre = nums[0];//前一个元素
        int count = 1;//元素写入的次数

        int writeIndex = 1;
        for (int i = 1; i < nums.length; i++) {
            int value = nums[i];
            if (value == pre) {
                count++;
                if (count <= k) {
                    nums[writeIndex] = value;
                    writeIndex++;
                }

            } else {
                count = 1;
                //不等于的情况
                nums[writeIndex] = value;
                writeIndex++;
                pre = value;
            }
        }

        return writeIndex;

    }


}
