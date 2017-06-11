package com.hm.inner_class;

/**
 * Created by dumingwei on 2017/6/7.
 */
public class AccessStaticInnerClass {

    public static void main(String[] args) {
        AccessStaticInnerClass obj = new AccessStaticInnerClass();
        obj.accessInnerProp();
    }

    public void accessInnerProp() {
        System.out.println(StaticInnerClass.prop1);
        System.out.println(new StaticInnerClass().prop2);
    }

    static class StaticInnerClass {

        private static int prop1 = 5;
        private int prop2 = 9;
    }
}
