package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2018/12/6
 * <p>
 * Desc:输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位予数组的后半部分。
 * <p>
 * 解题思路：这个题目要求把奇数放在数组的前半部分， 偶数放在数组的后半部分，因此所有的奇数应该位于偶数的前面。也就是说我们在扫描这个数组的时候， 如果发现有偶数出现在奇数的前面，我们可以交换它们的顺序，交换之后就符合要求了。
 * <p>
 * 因此我们可以维护两个指针，第一个指针初始化时指向数组的第一个数字，它只向后移动：第二个指针初始化时指向数组的最后一个数字，
 * 它只向前移动。在两个指针相遇之前，第一个指针总是位于第二个指针的前面。如果第一个指针指向的数字是偶数，并且第二个指针指向的数字是奇数，
 * 我们就交换这两个数字。
 * <p>
 * 参考链接：https://blog.csdn.net/derrantcm/article/details/46669015
 */
public class Test21 {

    public static void main(String[] args) {
        //test1();
        test2();
        //test3();
        test4();
    }

    private static void test1() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        //reorderOddEven(array);
        //printArray(array);

        Test21 test21 = new Test21();
        test21.exchange(array);
        printArray(array);
    }

    private static void test2() {
        int[] array = {1, 2, 3, 4};
        //reorderOddEven(array);
        //printArray(array);

        Test21 test21 = new Test21();
        test21.exchange(array);
        printArray(array);
    }

    private static void test3() {
        int[] array = {1, 2, 3, 4, 5};
        //reorderOddEven(array);
        //printArray(array);

        Test21 test21 = new Test21();
        test21.exchange(array);
        printArray(array);
    }

    private static void test4() {
        //int[] array = {1, 3, 5};
        int[] array = {2, 16, 3, 5, 13, 1, 16, 1, 12, 18, 11, 8, 11, 11, 5, 1};
        Test21 test21 = new Test21();
        test21.exchange(array);
        printArray(array);
    }


    /**
     * 第一个想到的是前后两个指针
     *
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int mid = nums.length / 2;
        int left = 0;
        int right = nums.length - 1;

        while (left < mid && right > mid) {
            while (right > mid && nums[right] % 2 == 0) {
                right--;
            }
            while (left < mid && nums[left] % 2 == 1) {
                left++;
            }
            if (right < mid) {
                break;
            }
            if (left >= mid) {
                break;
            }
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
            left++;
            right--;
        }
        return nums;
    }


    public static void reorderOddEven(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            while (start < end && nums[start] % 2 != 0) {
                start++;
            }
            while (start < end && nums[end] % 2 == 0) {
                end--;
            }
            // 找到后就将奇数和偶数交换位置
            // 对于start=end的情况，交换不会产生什么影响
            // 所以将if判断省去了
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
    }

    public static void printArray(int[] arr) {
        if (arr != null && arr.length > 0) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

}
