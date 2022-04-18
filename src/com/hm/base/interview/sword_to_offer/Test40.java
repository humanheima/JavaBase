package com.hm.base.interview.sword_to_offer;

import java.util.Arrays;

/**
 * Created by dumingwei on 2022/4/18.
 * <p>
 * Desc:剑指 Offer 40.最小的k个数
 */
public class Test40 {

    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || k <= 0) {
            int[] result = {};
            return result;
        }
        int length = arr.length;
        if (k >= length) {
            return arr;
        }
        Arrays.sort(arr);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;
    }

}
