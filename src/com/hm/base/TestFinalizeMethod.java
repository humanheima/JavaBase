package com.hm.base;

import java.util.GregorianCalendar;

/**
 * Created by dumingwei on 2022/3/13.
 * <p>
 * Desc: 参考链接 https://juejin.cn/post/6844903880086536199
 */

/**
 * 当 GC (垃圾回收器) 确定不存在对该对象的引用时，对象的垃圾回收器就会调用这个方法。子类可以覆盖finalize方法来处理系统资源或者执行清理操作。
 * <p>
 * finalize的一般约定是，当JVM已经确定，任何尚未终止的线程都无法再通过任何方式访问该对象（除非是由于其他已准备好终结的对象或类的终结操作产生的结果），那么就会调用该对象的finalize方法。
 * <p>
 * "除非是由于其他已准备好终结的对象或类的终结操作产生的结果"，这一段我的理解就是其他对象在finalize方法中引用了该对象。
 * <p>
 * <p>
 * finalize方法内部可以执行任何操作，包括让该对象重新在其他线程可用；但是，finalize的通常作用是在对象不再需要的时候执行清理操作。例如，关闭输入输出流等等。
 * <p>
 * Java语言不保证哪个线程会调用指定对象的finalize方法。但是，Java语言保证，调用finalize方法不会持有任何用户可见的同步锁。当在finalize方法中抛出一个一个未捕获的异常时，异常会被忽略，该对象的`finalization`会终止。
 * <p>
 * 在一个对象的finalize方法被调用后，不会有任何其他操作，直到JVM重新确定任何尚未终止的线程都无法再通过任何方式访问该对象(包括其他已准备好终结的对象或类)，那么此时，该对象会被永久抛弃(回收)。
 * <p>
 * 任何一个对象的finalize方法只会被JVM调用一次。
 * <p>
 * finalize中抛出的未捕获的异常会导致该对象的`finalization`终止，但是异常会被忽略。
 */
public class TestFinalizeMethod extends GregorianCalendar {

    public static void main(String[] args) {
        Cake c1 = new Cake(1);
        Cake c2 = new Cake(2);
        Cake c3 = new Cake(3);
        Person person = new Person(c2);
        c2 = c3 = null;
        person = null;
        System.gc(); //调用Java垃圾收集器
    }

    static class Cake {

        private int id;

        public Cake(int id) {
            this.id = id;
            System.out.println(this.toString() + " id = " + id + " is created");
        }

        public void test() {
            System.out.println(this + " id = " + id + " test");
        }

        protected void finalize() throws java.lang.Throwable {
            super.finalize();  //finalize的调用方法
            System.out.println(this.toString() + " id = " + id + " is disposed");

        }

    }

    static class Person {

        Cake cake;

        public Person(Cake cake) {
            this.cake = cake;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println(this + " finalize");
            cake.test();
        }
    }

}
