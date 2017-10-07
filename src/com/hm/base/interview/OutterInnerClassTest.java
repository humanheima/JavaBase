package com.hm.base.interview;

/**
 * Created by dumingwei on 2017/9/30.
 */
public class OutterInnerClassTest {

    class Inner {
    }

    public static void food() {
        // new Inner();
    }

    public void bar() {
        new Inner();
    }

    public static void main(String [] args) {
     //new Inner();
    }
}
