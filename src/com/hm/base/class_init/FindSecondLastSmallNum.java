package com.hm.base.class_init;

/**
 * Crete by dumingwei on 2020-02-24
 * Desc: 查找数组中倒数第二小的数
 * <p>
 * 参考链接：
 * https://blog.csdn.net/u010647035/article/details/88628394
 */
public class FindSecondLastSmallNum {

    private static int[] arr = {3, 9, 4, 5, 6, 8, 7};

    public static void main(String[] args) {

        System.out.println(find1(arr));

        System.out.println(find2(arr));
    }


    /**
     * 定义一个长度为2的数组，用来存放两个最小的数字，默认两个元素值都为 int.MAX_VALUE
     *
     * @param arr 原始数组
     */
    public static int find1(int[] arr) {
        //存放最小的两个数值
        int[] minNum = new int[2];
        //最小数值
        minNum[0] = Integer.MAX_VALUE;
        //第二小数值
        minNum[1] = Integer.MAX_VALUE;
        //遍历数组
        for (int i = 0; i < arr.length; i++) {
            //如果（minNum[0]）预设最小值，大于原数组索引为 i 的元素，则将该元素赋值给 minNum[0]
            //将 minNum[0] 值赋给 minNum[1]（第二最小值）
            if (minNum[0] > arr[i]) {
                minNum[1] = minNum[0];
                minNum[0] = arr[i];
            } else if (minNum[1] > arr[i]) {
                minNum[1] = arr[i];
            }
        }
        return minNum[1];
    }


    /**
     * 初始化2个最小值:min1、min2。min1代表最小的数，min2代表倒数第二小的数
     * 遍历所有元素,如果当前元素小于min1,那么将更新min1、min2。
     * 如果大于min1小于min2只更新min2即可。
     */
    public static int find2(int[] arr) {
        int min1; //存储最小的数值
        int min2; //存储第二小的数值

        //判断前两个大小
        if (arr[0] >= arr[1]) {
            min2 = arr[0];
            min1 = arr[1];
        } else {
            min2 = arr[1];
            min1 = arr[0];
        }
        for (int i = 2; i < arr.length; i++) {
            //比最小值小的情况
            if (arr[i] < min1) {
                min2 = min1;
                min1 = arr[i];
            }
            //大于最小值且小于第二最小值
            if (arr[i] > min1 && arr[i] < min2) {
                min2 = arr[i];
            }
        }
        return min2;
    }
}
