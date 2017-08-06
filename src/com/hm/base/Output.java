package com.hm.base;

/**
 * Created by dumingwei on 2017/7/11.
 * 接口定义
 */
public interface Output {

    //接口里定义的成员变量只能是常量
    public static final int A = 10;

    void out();

    //接口里定义的普通方法只能是public的抽象方法
    public abstract void getData(String msg);

    //在接口里定义默认方法，需要使用default修饰
    default void print(String... msgs) {
        for (String msg : msgs) {
            System.out.println(msg);
        }
    }

    //在接口中定义类方法，需要使用static修饰
    static String staticTet() {
        return "接口里的类方法";
    }
}
