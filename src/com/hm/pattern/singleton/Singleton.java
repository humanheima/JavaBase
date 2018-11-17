package com.hm.pattern.singleton;

/**
 * Created by dumingwei on 2017/6/5.
 * 单例模式 确保某个类只有一个实例，而且自行实例化并向整个系统提供这个实例
 * <p>
 * 饿汉式单例类.在类初始化时，已经自行实例化
 * <p>
 * 懒汉式单例类.在第一次调用的时候实例化自己
 */
public enum Singleton {

    INSTANCE;

    public void method() {
        System.out.println(" I am singleton");
    }

}

class Singleton4 {
    private static Singleton4 instance = null;

    static {
        System.out.println("static block");
        instance = new Singleton4();
    }

    private Singleton4() {
        System.out.println("Singleton4");
    }

    public static void main(String[] args) {

    }

    public static Singleton4 getInstance() {
        return instance;
    }

}
