package com.hm.base.interview.overload_override_test;

/**
 * Created by dumingwei on 2017/9/30.
 * override  重写又称覆盖
 * 1) 父类与子类之间的多态性，对父类的函数进行重新定义。如果在子类中定义某方法与其父类有相同的名称和参数，
 * 我们说该方法被重写 (Overriding)。在Java中，子类可继承父类中的方法，而不需要重新编写相同的方法。
 * 但有时子类并不想原封不动地继承父类的方法，而是想作一定的修改，这就需要采用方法的重写。方法重写又称方法覆盖。
 * 2) 若子类中的方法与父类中的某一方法具有相同的方法名、返回类型和参数表，则新方法将覆盖原有的方法。
 * 如需父类中原有的方法，可使用super关键字，该关键字引用了当前类的父类。
 * 3) 子类函数的访问修饰权限不能少于父类的；
 */
public class OverrideTest {

    public static void main(String[] args) {

        BaseEntity baseEntity = new SalesWaterEntity();
        baseEntity.test(0);
        baseEntity.test(((byte) 0));
    }
}
