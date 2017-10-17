package com.hm.base;

/**
 * Created by dumingwei on 2017/7/10.
 * final 关键字
 */
public class FinalTest {

    public FinalTest() {
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        StringBuffer buffer = new StringBuffer("hello");
        myClass.changeValue(buffer);
        System.out.println(buffer.toString());

        //test1();
    }

    private static void test1() {
        MyClass myClass1 = new MyClass();
        MyClass myClass2 = new MyClass();
        System.out.println(myClass1.i);
        System.out.println(myClass1.j);
        System.out.println(myClass2.i);
        System.out.println(myClass2.j);
    }
}

class MyClass {

    public final int i = (int) (100 * Math.random());
    public static int j = (int) (100 * Math.random());

    void changeValue(StringBuffer buffer) {
        buffer = new StringBuffer();
    }
}

