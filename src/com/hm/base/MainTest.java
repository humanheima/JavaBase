package com.hm.base;


/**
 * Crete by dumingwei on 2020-01-10
 * Desc: 测试Java 多态之方法调用顺序
 */
public class MainTest {

    public static void main(String[] args) {

        A a = new B();
        a.show();

    }


}


class A {


    public void show() {
        System.out.println("A");
    }
}

class B extends A {

    @Override
    public void show() {
        System.out.println("B");
    }
}
