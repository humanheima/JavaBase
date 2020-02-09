package com.hm.sort;

/**
 * Created by dumingwei on 2017/5/22.
 * 快速排序
 * 快速排序使用分治法（Divide and conquer）策略来把一个序列（list）分为两个子序列（sub-lists）。
 * 步骤为：
 * 从数列中挑出一个元素，称为"基准"（pivot），
 * 重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（相同的数可以到任一边）。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
 * 递归地（recursively）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 */
public class FastSort {

    private static int a[] = {8, 4, 9, 1, 10, 6};

    public static void main(String args[]) {
        //sortNormalArray();
        System.out.println(Integer.MAX_VALUE);
        //sortBigArray();
    }

    private static void sortNormalArray() {
        quickSort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
    }

    /**
     * 对一个大数组排序
     */
    private static void sortBigArray() {
        long currTime = System.currentTimeMillis();
        int[] random = new int[1000000];
        for (int i = 0; i < random.length; i++) {
            random[i] = (int) (Math.random() * 10000000);
        }
        quickSort(random, 0, random.length - 1);
        System.out.println("耗时" + (System.currentTimeMillis() - currTime) + "ms");
        for (int i = 0; i < random.length; i++) {
            System.out.println(random[i] + "");
        }


    }

    /**
     * 调用者请确保传入正确的参数
     *
     * @param a     要排序的数组
     * @param left  0
     * @param right 数组的长度减去1
     */
    private static void quickSort(int[] a, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int pivot = a[left];
            while (i < j) {
                while (i < j && a[j] >= pivot) {
                    j--;
                }
                if (i < j) {
                    a[i] = a[j];
                    i++;
                }
                while (i < j && a[i] < pivot) {
                    i++;
                }
                if (i < j) {
                    a[j] = a[i];
                    j--;
                }
            }
            a[i] = pivot;

            //打印每次排序后的结果，存在重复排序的情况
            /*for (int k = 0; k < a.length; k++) {
                System.out.print(a[k] + ",");
            }*/
            System.out.println();
            quickSort(a, left, i - 1);
            quickSort(a, i + 1, right);
        }
    }

    private static void sort(int[] a, int left, int right) {
        int dp;
        if (left < right) {
            dp = partition(a, left, right);
            sort(a, left, dp - 1);
            sort(a, dp + 1, right);
        }
    }

    private static int partition(int[] a, int left, int right) {
        int pivot = a[left];
        while (left < right) {
            while (left < right && a[right] >= pivot) {
                right--;
            }
            if (left < right) {
                a[left] = a[right];
                left++;
            }
            while (left < right && a[left] <= pivot) {
                left++;
            }
            if (left < right) {
                a[right] = a[left];
                right--;
            }
        }
        a[left] = pivot;
        return left;
    }
}
