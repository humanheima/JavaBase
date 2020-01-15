package com.hm.inner_class;

/**
 * Created by dumingwei on 2017/6/7.
 */
public class StaticInnerClassTest {

    private int prop1 = 5;
    private static int prop2 = 9;


    public static void accessInnerStaticField() {
        int age = StaticInnerClass.age;
    }

    public static void main(String[] args) {

        StaticInnerClass innerClass = new StaticInnerClass();
        innerClass.accessOutProp();
    }

    static class StaticInnerClass {

        private static int age;

        public void accessOutProp() {
            //静态内部类无法访问外部类的非静态成员变量
            //System.out.println(prop1);
            System.out.println(prop2);
        }
    }

    class InnerClass {

        public void accessOutProp() {
            System.out.println(prop1);
            System.out.println(prop2);
        }
    }
}
