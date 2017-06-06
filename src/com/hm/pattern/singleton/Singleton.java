package com.hm.pattern.singleton;

/**
 * Created by dumingwei on 2017/6/5.
 * 单例模式 确保某个类只有一个实例，而且自行实例化并向整个系统提供这个实例
 * <p>
 * 饿汉式单例类.在类初始化时，已经自行实例化
 * <p>
 * 懒汉式单例类.在第一次调用的时候实例化自己
 */
public class Singleton {

    //懒汉式单例类写法2 双重锁定
    private volatile static Singleton INSTANCE;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
    //懒汉式单例类写法1 静态内部类写法
    /*private Singleton() {

    }

    private static class LazyLoader {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return LazyLoader.INSTANCE;
    }*/


    //饿汉式单例类写法
   /* private Singleton() {

    }

    private static final Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
*/
}
