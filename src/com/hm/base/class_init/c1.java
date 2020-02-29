package com.hm.base.class_init;

public class c1 {

    public static void main(String[] args) {
        c1 c = new c2();
        //下面这里打印的是c1的成员变量s=AAAA
        System.out.println(c.s);
    }

    public String s = "AAAA";

    //父类构造函数
    public c1() {
        call();
    }

    public void call() {
        System.out.println(s);
    }

}

class c2 extends c1 {
    public String s = "BBBB";

    //子类构造函数
    public c2() {
        System.out.println(s);
    }

    //重写父类中的call函数
    @Override
    public void call() {
        System.out.println(s);
    }
}