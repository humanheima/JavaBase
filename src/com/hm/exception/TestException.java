package com.hm.exception;

/**
 * Created by dumingwei on 2017/10/20.
 */
public class TestException {

    public static void main(String[] args) {
        System.out.println(test());
    }

    private static boolean test() {
        try {
            int a = 2;
            System.out.println(2/0);
        } catch (ArithmeticException e) {
            e.printStackTrace();
            return false;
        } finally {
            System.out.println("finally");
        }
        return true;
    }
}
