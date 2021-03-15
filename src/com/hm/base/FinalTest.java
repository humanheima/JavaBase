package com.hm.base;

/**
 * Created by dumingwei on 2017/7/10.
 * final 关键字
 */
public class FinalTest {

    public FinalTest() {
    }

    public static void main(String[] args) {
        MyFinalClass myFinalClass = new MyFinalClass();
        StringBuffer buffer = new StringBuffer("hello");
        myFinalClass.changeValue(buffer);
        System.out.println(buffer.toString());

        //test1();
    }

    private static void test1() {
        MyFinalClass myFinalClass1 = new MyFinalClass();
        MyFinalClass myFinalClass2 = new MyFinalClass();
        System.out.println(myFinalClass1.i);
        System.out.println(myFinalClass1.j);
        System.out.println(myFinalClass2.i);
        System.out.println(myFinalClass2.j);
    }
}



