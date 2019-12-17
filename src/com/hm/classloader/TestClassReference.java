package com.hm.classloader;

public class TestClassReference {

    public static void main(String[] args) {
        //System.out.println(SubClass.const_value);// 被动应用1
        //System.out.println(SubClass.value);// 被动应用1
        //SubClass[] sca = new SubClass[10];// 被动引用2

        //SingleTon singleTon = SingleTon.getInstance();
        //System.out.println("count1=" + singleTon.count1);
        //System.out.println("count2=" + singleTon.count2);
        SuperClass subClass = new SuperClass();

    }

}

class SuperClass {
    static {
        System.out.println("superclass init");
    }

    int a = 3;

    {
        a = 2;
    }

    public SuperClass() {
        System.out.println(a);
    }


    public static int value = 123;
    public static final int const_value = 123;
}

class SubClass extends SuperClass {
    static {
        System.out.println("subclass init");
    }
}

class SingleTon {
    private static SingleTon singleTon = new SingleTon();
    public static int count1;
    public static int count2 = 0;

    private SingleTon() {
        count1++;
        count2++;
    }

    public static SingleTon getInstance() {
        return singleTon;
    }
}

