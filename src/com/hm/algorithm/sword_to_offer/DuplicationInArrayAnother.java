package com.hm.algorithm.sword_to_offer;


/**
 * Created by dumingwei on 2018/11/18
 * <p>
 * Desc:
 * 在一个长度为n+1的数组里的所有数字都在1~n的范围内，所以数组中至少有一个数字是重复的。请找出数组中任意一个重复的数字，
 * 但是不能修改输入的数组。例如，如果输入长度为8的数组{2,3,5,4,3,2,6,7}，那么对应的输出是重复的数字2或者3。
 * <p>
 * 解决方法:
 * 1. 由于不能修改输入的数组，我们可以创建一个长度为n+1的辅助数组，然后逐一把原数组的每个数字复制到辅助数组。如果原数组中被复制的数字是m，
 * 则把它复制到辅助数组中下标为m的位置。如果下标为m的位置上已经有数字了，则说明该数字重复了。由于使用了辅助空间，
 * 故该方案的空间复杂度是O(n)。
 * 2. 我们可以这样想：如果数组中有重复的数，那么n+1个1~n范围内的数中，一定有几个数的个数大于1。那么，我们可以利用这个思路解决该问题。
 * 例如长度为8的数组{1,2,3,4,5,6,7,1}，所有取值都在1-7之间，所以肯定至少又一个数字是重复的。我们可以利用这个思路解决该问题。
 * <p>
 * 我们把从1到n的数字从中间的数字m分为两部分，前面一半为1~m，后面一半为m+1~n。如果1~m的数字的数目等于m，
 * 则不能直接判断这一半区间是否包含重复的数字，反之，如果大于m，那么这一半的区间一定包含重复的数字；
 * 如果小于m，另一半m+1~n的区间里一定包含重复的数字。接下来，我们可以继续把包含重复的数字的区间一分为二，直到找到一个重复的数字。
 * <p>
 * 参考链接：{@see <a herf="https://blog.csdn.net/yz930618/article/details/76060160"></a>}
 * 参考链接：{@see <a herf="https://blog.csdn.net/weixin_37672169/article/details/79978096"></a>}
 */
public class DuplicationInArrayAnother {

    public static void main(String[] args) {
        int[] array = {2, 3, 5, 4, 3, 2, 6, 7};
        //System.out.println(duplicateUseAnotherArray(array));
        System.out.println(getDuplication(array));
    }

    /**
     * 方法1，使用辅助数组来查找
     *
     * @param numbers
     * @return -1 ，表示不存在重复的数
     */
    public static int duplicateUseAnotherArray(int[] numbers) {
        if (numbers == null || numbers.length < 1) {
            return -1;
        }
        /**
         * 判断输入是否在[1,numbers.length-1]之间
         */
        int[] newArray = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 1 || numbers[i] >= numbers.length) {
                return -1;
            }
            newArray[i] = -1;
        }
        for (int i = 0; i < numbers.length; i++) {
            if (newArray[numbers[i]] != numbers[i]) {
                newArray[numbers[i]] = numbers[i];
            } else {
                return numbers[i];
            }
        }
        return -1;

    }


    // TODO: 2018/11/18 这个算法还需要研究，不是很明白

    /**
     * 以数组 int[] array = {2, 3, 5, 4, 3, 2, 6, 7}; 为例
     * 在while 循环中
     * 1. 第一次 start=1，middle=4,整个数组中大于等1并且小于等于4的数字一共有5个。那么重复的数字肯定是在区间[1,4]中的。
     * 那么我们把 end值赋值为 end=middle=4.
     * <p>
     * 2. 我们把[1,4]区间一分为二。middle=2;
     * [1,2],[3,4],然后统计1，2出现的次数count=2。此时我们不能直接判断整个区间中是否有重复的数字。然后我们把start赋值为 middle+1=3
     * ，此时end=4。然后开始查找数字3，4出现的次数，两个数字出现的次数是3次。说明要么数字3重复了两次，要么数字4重复了两次。
     * 那么我们把end赋值为middle=3 继续统计[3]出现的次数，count=2。所以直接返回3。
     * <p>
     * 这种算法不能保证找出所有重复的数字，例如2也是重复了两次。
     *
     * @param arr{2, 3, 5, 4, 3, 2, 6, 7}
     * @return 重复的数字
     */
    public static int getDuplication(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 1 || arr[i] >= arr.length) {
                return -1;
            }
        }

        int start = 1;
        int end = arr.length - 1;
        while (end >= start) {
            int middle = start + ((end - start) >> 1);
            int count = countRange(arr, start, middle);
            if (end == start) {
                if (count > 1) {
                    return start;
                } else {
                    break;
                }
            }
            if (count > (middle - start + 1)) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    private static int countRange(int[] arr, int start, int end) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= start && arr[i] <= end) {
                count++;
            }
        }
        return count;
    }


}
