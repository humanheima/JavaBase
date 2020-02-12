package com.hm.base.interview.sword_to_offer;

/**
 * Crete by dumingwei on 2020-02-09
 * <p>
 * Desc:从数组汇总找出一个只出现一次的数，
 * 从数组中找出只出现一次的两个数，数组中其他数都出现两次
 */
public class FindTwoNumberOnce {

    public static void main(String[] args) {

        int arr[] = {2, 3, 4, 9, 4, 3, 9, 2, 8};

        findOnlyNum(arr, arr.length);

        int arr2[] = {2, 3, 4, 9, 6, 4, 3, 9, 2, 8};
        findTwo(arr2, arr2.length);
    }

    public static void findOnlyNum(int arr[], int len) {
        int result = 0;
        for (int i = 0; i < len; i++) {
            result = result ^ arr[i];
        }

        System.out.println(result);
    }

    public static void findTwo(int data[], int length) {

        if (length < 2)
            return;

        // get num1 ^ num2
        int resultExclusiveOR = 0;
        for (int i = 0; i < length; ++i)
            resultExclusiveOR ^= data[i];

        int indexOf1 = findFirstBitIs1(resultExclusiveOR);

        int num1 = 0;
        int num2 = 0;

        for (int j = 0; j < length; ++j) {
            // divide the numbers in data into two groups,
            // the indexOf1 bit of numbers in the first group is 1,
            // while in the second group is 0
            if (isBit1(data[j], indexOf1))
                num1 ^= data[j];
            else
                num2 ^= data[j];
        }
        System.out.println(num1);
        System.out.println(num2);

    }

    /**
     * 查找数字二进制表示中，第一个不为0的下标
     *
     * @param num 7 1001
     *            6 0110
     *            5 0101
     * @return
     */
    public static int findFirstBitIs1(int num) {
        int indexBit = 0;
        while (((num & 1) == 0) && (indexBit < 32)) {
            num = num >> 1;
            ++indexBit;
        }

        return indexBit;
    }


    /**
     * 判断数字num的二进制表示在下标为indexBit上是否为1
     *
     * @param num      比如7 1001
     * @param indexBit
     * @return
     */
    public static boolean isBit1(int num, int indexBit) {
        num = num >> indexBit;
        return (num & 1) == 1;
    }
}
