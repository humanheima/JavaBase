package com.hm.base;

/**
 * Created by dumingwei on 2017/10/2.
 */
public class TestBreak {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            System.out.println("i==" + i);
            for (int j = 0; j < 10; j++) {

                System.out.println("j==" + j);
                if (j == 5)
                    break;
            }
        }
    }
}
