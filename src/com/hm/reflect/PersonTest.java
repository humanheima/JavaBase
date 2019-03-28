package com.hm.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PersonTest {

    public static void main(String[] args) {
        //testGetField();
        //testGetDeclaredField();
        //testModifyField();
        //testGetFields();
        //testGetDeclaredFields();
        //testGetMethod();
        //testGetDeclaredMethod();
        //testGetMethods();
        //testGetDeclaredMethods();
        //testGetConstructor();
        //testGetConstructors();
        testGetDeclaredConstructors();
    }

    private static void testGetConstructors() {
        Class c = Person.class;
        Constructor[] constructors = c.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
    }

    private static void testGetDeclaredConstructors() {
        Class c = Person.class;
        Constructor[] constructors = c.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
    }

    private static void testGetConstructor() {
        Class c = Person.class;
        Constructor constructor = null;
        try {
            constructor = c.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println(constructor.getName());
        //输出结果com.example.model.Person
    }

    private static void testGetDeclaredConstructor() {
        Class c = Person.class;
        Constructor constructor = null;
        try {
            constructor = c.getDeclaredConstructor(int.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println(constructor.getName());
    }

    private static void testGetField() {
        Class c = Person.class;
        Field field = null;
        try {
            field = c.getField("FieldInLiveInterface");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.println("found:" + field.getName());
    }

    private static void testGetDeclaredField() {
        Class c = Person.class;
        try {
            //注释1处
            Object o = c.newInstance();
            Field field1 = c.getDeclaredField("msg");
            //注释2处
            field1.setAccessible(true);
            String msg = (String) field1.get(o);
            System.out.println("found:" + msg);//输出good morning 2017
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testModifyField() {
        Class c = Person.class;
        try {
            Object o = c.newInstance();
            Field field1 = c.getDeclaredField("msg");
            field1.setAccessible(true);
            String msg = (String) field1.get(o);
            System.out.println(msg);
            field1.set(o, "good by 2016");
            String msgModified = (String) field1.get(o);
            System.out.println(msgModified);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testGetFields() {
        Class c = Person.class;
        Field[] fields = c.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

    private static void testGetDeclaredMethod() {
        Class c = Person.class;
        try {
            //Method method = c.getDeclaredMethod("getInfo");
            //System.out.println("name=" + method.getName() + ",return type= " + method.getReturnType());
            Method method1 = c.getDeclaredMethod("run");
            System.out.println("name=" + method1.getName() + ",return type= " + method1.getReturnType());
            Method method2 = c.getDeclaredMethod("info", String.class, int.class);
            System.out.println("name=" + method2.getName() + ",return type= " + method2.getReturnType());
            Method method3 = c.getDeclaredMethod("jump");
            System.out.println("name=" + method3.getName() + ",return type= " + method3.getReturnType());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static void testGetMethod() {
        Class c = Person.class;
        try {
            Method method = c.getMethod("setEyes", int.class);
            System.out.println("name=" + method.getName() + ",return type= " + method.getReturnType());
            Method method1 = c.getMethod("run");
            System.out.println("name=" + method1.getName() + ",return type= " + method1.getReturnType());
            Method method2 = c.getMethod("setAge", int.class);
            System.out.println("name=" + method2.getName() + ",return type= " + method2.getReturnType());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static void testGetMethods() {
        Class c = Person.class;
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }

    private static void testGetDeclaredMethods() {
        Class c = Person.class;
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }

    private static void testGetDeclaredFields() {
        Class c = Person.class;
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }
}
