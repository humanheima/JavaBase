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
    }
}

class MyClass {

    void changeValue(StringBuffer buffer) {
        buffer = new StringBuffer();
    }
}

