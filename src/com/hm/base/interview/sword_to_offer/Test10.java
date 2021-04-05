package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2018/11/26
 * <p>
 * Desc:斐波那契数列。参看根fibonacci.png。如果使用递归来解决的话，会存在很多重复的计算问题。更简单的方法是从下往上计算，
 * 首先根据f(0)和f(1)计算出f(2),再根据f(1)和f(2)计算出f(3)...以此类推就可以计算出f(n)了。这种方法的时间复杂度是O(n)。
 * 青蛙跳台阶，也可以看作是斐波那契数列问题。
 *
 *
 * <p>
 * 参考链接：参考链接：{@see <a herf="https://blog.csdn.net/DERRANTCM/article/details/45476095">斐波那契数列</a>}
 */
public class Test10 {

    public static void main(String[] args) {
        for (int counter = 0; counter <= 10; counter++) {
            System.out.printf("Fibonacci of %d is: %d\n",
                    counter, fibonacci(counter));
            System.out.printf("fibonacciRecursively of %d is: %d\n",
                    counter, fibonacciRecursively(counter));
        }
    }

    public static long fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        //记录第n-2个数的fibonacci值
        long preTwo = 1;
        //记录第n-1个数的fibonacci值
        long preOne = 1;
        //记录前两个（第n个）的Fibonacci数的值
        long current = 2;
        for (int i = 3; i <= n; i++) {
            current = preTwo + preOne;
            preTwo = preOne;
            preOne = current;
        }
        return current;
    }

    public static long fibonacciRecursively(long number) {
        if ((number == 0) || (number == 1))
            return number;
        else
            return fibonacciRecursively(number - 1) + fibonacciRecursively(number - 2);
    }

}
