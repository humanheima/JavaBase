package com.hm.java8;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 这种方式比反射快，待研究。
 */
public class MethodHandleDemo {

    public static void main(String[] args) throws Throwable {
        // 1. 获取 Lookup
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        // 2. 定义构造器签名：返回类型为 void，参数为 (String, int)
        MethodType constructorType = MethodType.methodType(void.class, String.class, int.class);

        // 3. 查找构造器
        MethodHandle constructor = lookup.findConstructor(User.class, constructorType);

        // 4. 调用构造器创建对象
        User user = (User) constructor.invoke("张三", 25);

        System.out.println(user); // User{name='张三', age=25}
    }
}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}