package com.hm.base;


public class IsAssignableFromTest {

    public static void main(String[] args) {

        //判断当前类是否是参数类的父类或者父接口
        System.out.println(A.class.isAssignableFrom(B.class));//true
        System.out.println(I.class.isAssignableFrom(B.class));//true
        System.out.println(B.class.isAssignableFrom(A.class));//false
        System.out.println(B.class.isAssignableFrom(I.class));//false
        System.out.println(int.class.isAssignableFrom(int.class));//false
    }


    class A {

        protected String name;
    }

    interface I {

    }

    class B extends A implements I {

    }

}
