package com.hm.base;

/**
 * Created by dumingwei on 2017/10/11.
 * 测试break标签
 */
public class BreakTest {

    public static void main(String[] args) {
        //test0();
        //test();
        testTwo();
    }

    private static void test0() {
        first:
        {
            second:
            {
                third:
                {
                    System.out.println("Before the break");
                    for (int i = 0; i < 10; i++) {
                        System.out.println("i==" + i);
                        if (i == 3) {
                            break second;// break out of second block
                            //break;
                        }

                    }
                }
                System.out.println("This won't execute");
            }
            System.out.println("This is after second block.");
        }
    }

    private static void test() {
        outer:
        for (int i = 0; i < 3; i++) {
            System.out.print("Pass " + i + ":");
            for (int j = 0; j < 100; j++) {
                if (j == 10)
                    break outer;
                System.out.print(j + " ");
            }
            System.out.println("This will not print");
        }
        System.out.println("loops complete.");
    }

    private static void testTwo() {
        outer:
        for (int i = 0; i < 4; i++) {
            System.out.print("Pass " + i + ":");
            for (int j = 0; j < 100; j++) {
                if (j == 10)
                    continue outer;
                System.out.print(j + " ");
            }
            System.out.println("This will not print");
        }
        System.out.println("loops complete.");
    }
}
