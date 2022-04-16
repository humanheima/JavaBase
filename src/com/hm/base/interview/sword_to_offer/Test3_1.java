package com.hm.base.interview.sword_to_offer;


/**
 * Created by dumingwei on 2018/11/18
 * <p>
 * Desc: 数组中重复对数字
 * <p>
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是重复的数字2或者3。
 * 解决方法:
 * 1. 把数组排序，然后再遍历数组，如果存在 array[i]==array[i+1] 就说明存在重复数字，返回array[i]
 * <p>
 * 2. 使用哈希表来解决，从头到尾扫描数组，如果哈希表中不存在这个数字，就加入哈希表，如果哈希表中存在这个数字，那么就找到了一个重复的数字。
 * 这个算法的时间复杂度是O(n)，但它提高时间效率是以一个大小为O(n)的哈希表为代价的。
 * <p>
 * 3. 我们注意到数组中的数字都在0到n-1中。如果这个数组中没有重复的数字，那么当数组排序之后数字i将出现在下标为i的位置。
 * 由于数组中有重复的数字，有些位置可能存在多个数字，同时有些位置可能没有数字。
 * <p>
 * 现在让我们重排这个数组，依然从头到尾一次扫描这个数组中的每个数字。当扫描到下标为i的数字时，首先比较这个数字（用m表示）是不是等于i。
 * 如果是，接着扫描下一个数字。如果不是，再拿它和第m个数字进行比较。如果它和第m个数字相等，就找到了一个重复的数字
 * （该数字在下标为i和m的位置都出现了）。如果它和第m个数字不相等，就把第i个数字和第m个数字交换，把m放到属于它的位置。
 * 接下来再重复这个比较、交换的过程，直到我们发现一个重复的数字。
 * <p>
 * 以数组{2,3,1,0,2,5,3}为例来分析找到重复数字的步骤。数组的第0个数字是2，与它的下标不相等，于是把它和下标为2的数字1交换。
 * 交换之后的数组是{1.3.2.0.2.5.3}。此时第0个数字是1，仍然与它的下标不相等，继续把它和下标为1的数字3交换，得到数组{3,1,2,0,2,5,3}.
 * 接下来继续交换第0个数字3和第3个数字0，得到数组{0,1,2,3,2,5,3}。此时第0个数字的数值为0，接着扫描下一个数字。在接下来的几个数字中，
 * 下标为1,2,3的三个数字分别为1,2,3，它们的下标和数值都分别相等，因此不需要做任何操作。接下来扫描到下标为4的数字2.
 * 由于它的数值与它的下标不相等，再比较它和下标为2的数字。注意到此时数组中下标为2的数字也是2，也就是数字在下标为2和下标为4的两个位置都出现了，
 * 因此找到一个重复的数字。
 * <p>
 * 参考链接：{@see <a herf="https://blog.csdn.net/derrantcm/article/details/46811855 "></a>}
 */
public class Test3_1 {

    public static void main(String[] args) {

        Test3_1 test3_1 = new Test3_1();
        int[] numbers = {1, 3, 2, 0, 2, 5, 3};

        System.out.println(test3_1.duplicate(numbers) + " , " + test3_1.findRepeatNumber(numbers));

        int[] numbers1 = {1, 2, 3, 4, 7, 5, 6, 0};
        System.out.println(test3_1.duplicate(numbers1) + " , " + test3_1.findRepeatNumber(numbers1));

        int[] numbers2 = {1, 10, 3};
        System.out.println(test3_1.duplicate(numbers2) + " , " + test3_1.findRepeatNumber(numbers2));

        System.out.println(test3_1.duplicate(null) + " , " + test3_1.duplicate(null));

        int[] numbers3 = {3, 4, 2, 0, 0, 1};
        System.out.println(test3_1.duplicate(numbers3) + " , " + test3_1.findRepeatNumber(numbers3));
    }

    /**
     * 交换位置
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length == 1) {
            return -1;
        }
        /**
         * 判断输入是否在[0,numbers.length-1]之间
         */
        for (int number : nums) {
            if (number < 0 || number >= nums.length) {
                return -1;
            }
        }
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            while (nums[i] != i) {
                int dest = nums[nums[i]];
                if (nums[i] == dest) {
                    return nums[i];
                } else {
                    int temp = dest;
                    nums[nums[i]] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        return -1;
    }


    public int duplicate(int[] numbers) {
        if (numbers == null || numbers.length < 1) {
            return -1;
        }
        /**
         * 判断输入是否在[0,numbers.length-1]之间
         */
        for (int number : numbers) {
            if (number < 0 || number >= numbers.length) {
                return -1;
            }
        }
        for (int i = 0; i < numbers.length; i++) {

            /**
             * 如果number[i]与i不同的时候一致交换，我们交换的原因是让number[i]等于它的下标
             */
            while (numbers[i] != i) {
                //如果i位置的数字和numbers[i]位置的数字相等，说明存在重复的数字
                if (numbers[i] == numbers[numbers[i]]) {
                    return numbers[i];
                } else {
                    //如果不同就交换
                    int temp = numbers[i];
                    numbers[i] = numbers[temp];
                    numbers[temp] = temp;
                }
            }
        }
        return -1;

    }


}
