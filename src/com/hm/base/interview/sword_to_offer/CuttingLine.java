package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2018/11/30
 * <p>
 * Desc:剪绳子。感觉这道题目书上描述并不准确，有的地方有错误。
 * <p>
 * 注意：
 * 1. 初始化的时候为什么f[3]=3？
 * 因为当length<=3的时候，我们直接返回了结果。如果整个绳子的长度为3，我们必须把绳子剪开，因为题目要求m>1，其中一段为2，另一段为1，
 * 这样结果就是2。当length>=4的时候，我们可以把绳子剪成两段，其中一段为3另一段为１，这样长度为3的那一段的最大值就是3而不是2，
 * 因为这一段我们不需要再剪了。当然长度为4的最大值是2和2的组合，我们已经存储了2的长度。
 * <p>
 * 参考链接：https://blog.csdn.net/qq_25827845/article/details/73351134
 */
public class CuttingLine {

    public static void main(String[] args) {
        System.out.println(maxProduceAfterCutting(10));
        System.out.println(maxProduceAfterCuttingGreedy(10));
    }

    /**
     * 动态规划
     *
     * @param length 绳子的长度
     * @return
     */
    public static int maxProduceAfterCutting(int length) {
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }
        int products[] = new int[length + 1];
        products[0] = 0;
        products[1] = 1;
        /**
         * 为什么这里products[2]等于2，例如当绳子长度为3的时候，我们剪成两段，其中一段为2，一段为1。这样长度为2的那段最大值就是2，
         * 而不是1，因为这一段我们不需要再剪了。
         */
        products[2] = 2;
        /**
         * 初始化的时候为什么products[3]=3？
         * 因为当length<=3的时候，我们直接返回了结果。如果整个绳子的长度为3，我们必须把绳子剪开，因为题目要求m>1，其中一段为2，另一段为1，
         * 这样结果就是2。当length>=4的时候，我们可以把绳子剪成两段，其中一段为3,另一段为１，这样长度为3的那一段的最大值就是3而不是2，
         * 因为这一段我们不需要再剪了。当然长度为4的最大值是2和2的组合，我们已经存储了2的长度。
         */
        products[3] = 3;

        int result = 0;

        for (int i = 4; i <= length; i++) {

            int max = 0;
            for (int j = 0; j <= i / 2; j++) {
                int product = products[j] * products[i - j];
                if (max < product) {
                    max = product;
                }
                products[i] = max;
            }
        }
        result = products[length];
        return result;
    }


    /**
     * 贪婪算法
     *
     * @param length 绳子的长度
     * @return
     */
    public static int maxProduceAfterCuttingGreedy(int length) {
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }
        //尽可能多的减去长度为3的绳子段
        int timesOf3 = length / 3;
        /**
         * 当绳子最后剩下的长度为4的时候，不能再减去长度为3的绳子段，此时更好的方法是把绳子减去长度为e的两段，因为2*2>3*1
         */
        if (length - timesOf3 * 3 == 1) {
            timesOf3 -= 1;
        }
        int timeOf2 = (length - timesOf3 * 3) / 2;
        return (int) Math.pow(3, timesOf3) * (int) Math.pow(2, timeOf2);

    }


}
