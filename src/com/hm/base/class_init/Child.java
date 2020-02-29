package com.hm.base.class_init;

public class Child extends Parent {


    private int c = 3;

    protected int d = 5;

    {
        System.out.println("child 初始化代码块 c = " + c);

    }

    public Child() {
        //test();
        System.out.println("child c= " + c);
        System.out.println("child d= " + d);
        test();
    }

    @Override
    public void test() {
        System.out.println("child test c = " + c);

    }
}
