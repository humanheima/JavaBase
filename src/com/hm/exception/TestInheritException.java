package com.hm.exception;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by dumingwei on 2017/12/13 0013.
 * 测试 在类继承的时候，方法覆盖时如何进行异常抛出声明
 * <p>
 * 1）父类的方法没有声明异常，子类在重写该方法的时候不能声明异常；
 * 2）如果父类的方法声明一个异常exception1，则子类在重写该方法的时候声明的异常不能是exception1的父类；
 * 3）如果父类的方法声明的异常类型只有非运行时异常（运行时异常），
 * 则子类在重写该方法的时候声明的异常也只能有非运行时异常（运行时异常），
 * 不能含有运行时异常（非运行时异常）。
 */
public class TestInheritException {

    public static void main(String[] args) {
        System.out.println(4/0);
    }
}

class A {

    protected void method() throws IOException {

    }

    protected void method1() {

    }
}

class B extends A {

    @Override
    protected void method() throws IOException {
        super.method();
    }

   /* @Override
    protected void method1() throws Exception {
        super.method1();
    }*/
}

class C extends A {

    @Override
    protected void method() {
    }
}

class E extends A {

    @Override
    protected void method() throws FileNotFoundException {
    }
}

class F extends A {

    @Override
    protected void method() throws FileNotFoundException, ArithmeticException {
    }
}

class G extends A {

   /* @Override
    protected void method() throws FileNotFoundException,ParseException {
    }*/
}

class D extends A {

    //Exception 是IOException的父类
    /*@Override
    protected void method() throws Exception{
    }*/
}
