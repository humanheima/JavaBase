package com.hm.base.interview.overload_override_test;

/**
 * Created by dumingwei on 2017/9/30.
 * overload 重载 参考链接 http://blog.csdn.net/zhu_apollo/article/details/1852542
 * 1) 方法重载是让类以统一的方式处理不同类型数据的一种手段。多个同名函数同时存在，具有不同的参数个数/类型。
 * 重载是一个类中多态性的一种表现。
 * 2) Java的方法重载，就是在类中可以创建多个方法，它们具有相同的名字，但具有不同的参数和不同的定义。
 * 调用方法时通过传递给它们的不同参数个数和参数类型来决定具体使用哪个方法, 这就是多态性。
 * 3) 重载的时候，方法名要一样，但是参数类型和个数不一样，返回值类型可以相同也可以不相同。
 * 无法以返回型别作为重载函数的区分标准。
 */
public class OverloadTest {

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.bark();
        System.out.println(dog.bark("hello"));
        dog.bark("hello", 3);
        dog.bark(3, "hello");
    }
}

