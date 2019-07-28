package com.hm.thread;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class ThreadLocalTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>(){

       /* @Override
        protected String initialValue() {
            return "hello world";
        }*/
    };

    public static void main(String[] args) {
        threadLocal.set("初始名称");
        System.out.println(Thread.currentThread().getName() + " ," + threadLocal.get());
        new TestThread("线程甲", threadLocal).start();
        new TestThread("线程乙", threadLocal).start();
    }
}

class TestThread extends Thread {

    private ThreadLocal<String> threadLocal;

    public TestThread(String name, ThreadLocal<String> threadLocal) {
        super(name);
        this.threadLocal = threadLocal;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i == 6) {
                //当i==6的时候替换成当前线程名
                threadLocal.set(getName());
            }
            //获取
            System.out.println(Thread.currentThread().getName() + " ," + threadLocal.get() + "，i= " + i);
        }
    }
}


