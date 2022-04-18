package com.hm.base.interview.sword_to_offer;

import java.util.Arrays;

/**
 * Created by dumingwei on 2022/4/18.
 * <p>
 * Desc:剑指 Offer 39. 数组中出现次数超过一半的数字
 * <p>
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 把这个数字放到它所在的位置上，如果位置上有了，就加1
 */
public class Test39 {

    /**
     * 使用hash的方式来解决吧
     *
     * @param nums
     * @return
     */
    public int majorityElementHash(int[] nums) {
        // TODO: 2022/4/18  
        return 1;
    }

    /**
     * 或者直接排序
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }


}
