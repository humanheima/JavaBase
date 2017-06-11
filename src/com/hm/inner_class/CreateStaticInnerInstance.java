package com.hm.inner_class;

/**
 * Created by dumingwei on 2017/6/7.
 */
public class CreateStaticInnerInstance {

    public static void main(String[] args) {
        StaticOut.StaticIn in = new StaticOut.StaticIn();
    }
}

class StaticOut {

    static class StaticIn {

        public StaticIn() {
            System.out.println("静态内部类的构造器");
        }
    }
}
