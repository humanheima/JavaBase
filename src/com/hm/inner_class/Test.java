package com.hm.inner_class;

/**
 * Created by dumingwei on 2017/7/3.
 */
public class Test {

    private Test() {
        System.out.println("初始化");
    }

    public static Test getInstance() {
        return Inner.test;
    }

    private static class Inner {

        static {
            System.out.println("静态内部类初始化");
        }

        private final static Test test = new Test();

    }

    public static void main(String[] args) {

Test test=new Test();
        //TestAnnotation.getInstance();
    }


}
