package com.hm.classloader;


import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dumingwei on 2017/6/25.
 */
public class MethodParameterTest {

    public static void main(String[] args) {
        Class<Test> clazz = Test.class;
        try {
            Method replace = clazz.getMethod("replace", String.class, List.class);
            System.out.println("replace方法参数个数:" + replace.getParameterCount());
            Parameter[] parameters = replace.getParameters();
            int index = 1;
            for (Parameter parameter : parameters) {
                if (parameter.isNamePresent()) {
                    System.out.println("第" + index++ + "个参数信息....");
                    System.out.println("参数名：" + parameter.getName());
                    System.out.println("形参类型：" + parameter.getType());
                    System.out.println("泛型类型：" + parameter.getParameterizedType());
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

class Test {

    public void replace(String List, List<String> list) {

    }
}
