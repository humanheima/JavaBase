package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2018/11/28
 * <p>
 * Desc:把一个数组最开始的若干个元素搬到数组的末尾， 我们称之数组的旋转。输入一个递增排序的数组的一个旋转， 输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2 ｝为｛1,2,3,45}的一个旋转，该数组的最小值为1
 * <p>
 * 参考链接：https://blog.csdn.net/DERRANTCM/article/details/45457859
 */
public class RotateSmallestNumInArray {

    public static void main(String[] args) {

        // 旋转0个元素
        int[] array0 = {1, 2, 3, 4, 5};
        System.out.println(min(array0));

        int[] array1 = {3, 4, 5, 1, 2};
        System.out.println(min(array1));

        // 有重复数字，并且重复的数字刚好的最小的数字
        int[] array2 = {3, 4, 5, 1, 1, 2};
        System.out.println(min(array2));

        // 有重复数字，但重复的数字不是第一个数字和最后一个数字
        int[] array3 = {3, 4, 5, 1, 2, 2};
        System.out.println(min(array3));

        // 有重复的数字，并且重复的数字刚好是第一个数字和最后一个数字
        int[] array4 = {1, 0, 1, 1, 1};
        System.out.println(min(array4));

        // 数组中只有一个数字
        int[] array6 = {2};
        System.out.println(min(array6));

        // 数组中数字都相同
        int[] array7 = {1, 1, 1, 1, 1, 1, 1};
        System.out.println(min(array7));

        // 输入NULL
        System.out.println(min(null));

    }

    private static int min(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Invalid input.");
        }
        int low = 0;
        int high = numbers.length - 1;
        //设置初始值
        int mid = low;
        while (numbers[low] >= numbers[high]) {
            if (high - low == 1) {
                mid = high;
                break;
            }
            mid = (low + high) / 2;
            if (numbers[mid] == numbers[low] && numbers[mid] == numbers[high]) {
                return minInOrder(numbers, low, high);
            }
            if (numbers[mid] >= numbers[low]) {
                low = mid;
            } else if (numbers[mid] <= numbers[high]) {
                high = mid;
            }
        }
        return numbers[mid];
    }

    /**
     * 顺序查找
     *
     * @param numbers
     * @param low
     * @param high
     * @return
     */
    private static int minInOrder(int[] numbers, int low, int high) {
        int result = numbers[low];
        for (int i = low + 1; i <= high; i++) {
            if (result > numbers[i]) {
                result = numbers[i];
            }
        }
        return result;
    }


}
