package com.hm.inner_class;

/**
 * Created by dumingwei on 2017/6/7.
 */
public class Outer {

    private int outProp = 9;

    public static void main(String[] args) {
        //test();
    }

    public static void test() {
        Outer outer = new Outer();
        outer.accessInnerVariable();
    }

    public void accessInnerVariable() {
        //如果需要访问内部类的成员变量，必须显式创建内部类对象
        System.out.println(new Inner().innerProp);
    }

    private class Inner {

        private int innerProp = 5;

        public void accessOutVariable() {
            System.out.println(outProp);
        }
    }
}
