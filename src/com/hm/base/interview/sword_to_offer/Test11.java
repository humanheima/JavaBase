package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2018/11/28
 * <p>
 * Desc:把一个数组最开始的若干个元素搬到数组的末尾， 我们称之数组的旋转。输入一个递增排序的数组的一个旋转， 输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为｛1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * <p>
 * 参考链接：https://blog.csdn.net/DERRANTCM/article/details/45457859
 */
public class Test11 {

    public static void main(String[] args) {

        //method1();

        method2();
    }

    private static void method1() {
        // 旋转0个元素
        int[] array0 = {1, 2, 3, 4, 5};
        System.out.println(minArray2(array0));

        int[] array1 = {3, 4, 5, 1, 2};
        System.out.println(minArray2(array1));

        // 有重复数字，并且重复的数字刚好的最小的数字
        int[] array2 = {3, 4, 5, 1, 1, 2};
        System.out.println(minArray2(array2));

        // 有重复数字，但重复的数字不是第一个数字和最后一个数字
        int[] array3 = {3, 4, 5, 1, 2, 2};
        System.out.println(minArray2(array3));

        // 有重复的数字，并且重复的数字刚好是第一个数字和最后一个数字
        int[] array4 = {1, 0, 1, 1, 1};
        System.out.println(minArray2(array4));

        // 数组中只有一个数字
        int[] array6 = {2};
        System.out.println(minArray2(array6));

        // 数组中数字都相同
        int[] array7 = {1, 1, 1, 1, 1, 1, 1};
        System.out.println(minArray2(array7));

        int[] array8 = {3, 3, 1, 3};
        System.out.println(minArray2(array8));

        // 输入NULL
        System.out.println(minArray2(null));
    }

    private static void method2() {
        // 旋转0个元素
        int[] array0 = {1, 2, 3, 4, 5};
        System.out.println(findMinBinary(array0));

        int[] array1 = {3, 4, 5, 1, 2};
        System.out.println(findMinBinary(array1));

        // 有重复数字，并且重复的数字刚好的最小的数字
        int[] array2 = {3, 4, 5, 1, 1, 2};
        System.out.println(findMinBinary(array2));

        // 有重复数字，但重复的数字不是第一个数字和最后一个数字
        int[] array3 = {3, 4, 5, 1, 2, 2};
        System.out.println(findMinBinary(array3));

        // 有重复的数字，并且重复的数字刚好是第一个数字和最后一个数字
        int[] array4 = {1, 0, 1, 1, 1};
        System.out.println(findMinBinary(array4));

        // 数组中只有一个数字
        int[] array6 = {2};
        System.out.println(findMinBinary(array6));

        // 数组中数字都相同
        int[] array7 = {1, 1, 1, 1, 1, 1, 1};
        System.out.println(findMinBinary(array7));

        int[] array8 = {3, 3, 1, 1, 3, 3};
        System.out.println(findMinBinary(array8));
    }


    /**
     * 这种算法也挺好
     *
     * @param numbers
     * @return
     */
    public static int minArray2(int[] numbers) {
        if (numbers == null) {
            return -5001;
        }
        if (numbers.length == 1) {
            return numbers[0];
        }
        int lastIndex = numbers.length;
        int index = 0;
        for (int i = 0; i < lastIndex - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                index = i + 1;
                break;
            }
        }
        return numbers[index];


    }

    /**
     * 用这种算法吧
     *
     * @param arr
     * @return
     */
    public static int findMinBinary(int[] arr) {
        // 空数组或单元素处理
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        if (arr.length == 1) {
            return arr[0];
        }

        int left = 0;
        int right = arr.length - 1;

        // 如果最后一个元素比第一个大，说明没旋转
        if (arr[right] > arr[left]) {
            return arr[0];
        }

        //todo 循环结束条件，感觉不需要 left <= right； left < right 就可以了。
        //while (left <= right) {
        while (left < right) {
            int mid = left + (right - left) / 2; // 防止溢出
            if (arr[mid] == arr[right]) {
                //如果和最右边的值相等，说明有重复元素，right 减 1，最小的值要么就是重复的值，要么就在左边，缩小区间
                right--;
            } else if (arr[mid] > arr[right]) {
                // 如果 mid 比 right 大，最小值在右半部分
                left = mid + 1;
            } else if (arr[mid] < arr[right]) {
                // 如果 mid 比 right 小，最小值在左半部分（包括 mid）
                right = mid;
            }
        }
        return arr[left]; // left 指向最小值
    }

}
