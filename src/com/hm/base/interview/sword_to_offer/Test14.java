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
 * 参考链接：
 * * https://blog.csdn.net/qq_25827845/article/details/73351134 这个竟然收费了 艰难
 * * http://codingdict.com/blog/article/2018/12/18/655.html
 */
public class Test14 {

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
        //上面本别返回了长度小于等于3时候的最优解，下面是计算长度大于3时候的情况
        int[] products = new int[length + 1];
        products[0] = 0;
        products[1] = 1;
        /**
         * 为什么这里products[2]等于2？例如当绳子长度为3的时候，我们剪成两段，其中一段为2，一段为1。这样长度为2的那段最大值就是2，
         * 而不是1，因为这一段我们不需要再剪了。
         */
        products[2] = 2;
        /**
         * 初始化的时候为什么products[3]=3？
         * 因为当length<=3的时候，我们直接返回了结果。如果整个绳子的长度为3，我们必须把绳子剪开，因为题目要求m>1，其中一段为2，另一段为1，
         * 这样结果就是2。当length>=4的时候，我们可以把绳子剪成两段，其中一段为3,另一段为１，这样长度为3的那一段的最大值就是3而不是2，
         * 因为这一段我们不需要再剪了。当然长度为4的最大值是2和2的组合，我们已经存储了2的长度。
         *
         * 例如绳子长度是4的时候剪断绳子的剪法有
         * 1 3
         * 2 2
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
     * <p>
     * 当 n>=5 时，尽可能多地剪长度为 3 的绳子
     * 当剩下的绳子长度为 4 时，就把绳子剪成两段长度为 2 的绳子。
     * <p>
     * 证明：
     * <p>
     * 当 n>=5 时，可以证明 2(n-2)>n，并且 3(n-3)>n。
     * 也就是说，当绳子剩下长度大于或者等于 5 的时候，可以把它剪成长度为 3 或者 2 的绳子段。
     * 当 n>=5 时，3(n-3)>=2(n-2)，因此，应该尽可能多地剪长度为 3 的绳子段。
     * 当 n=4 时，剪成两根长度为 2 的绳子，其实没必要剪，只是题目的要求是至少要剪一刀。
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
        //尽可能多的减去长度为3的绳子段，这个需要去证明，长度为3的段数越多乘积越大
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
