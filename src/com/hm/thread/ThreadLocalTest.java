package com.hm.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        ConcurrentHashMap map=new ConcurrentHashMap();
        Map<String, String> m = Collections.synchronizedMap(new HashMap<String, String>());
        Acc acc = new Acc("初始名");
        new MyTest("甲", acc).start();
        new MyTest("乙", acc).start();
    }
}

class MyTest extends Thread {

    private Acc acc;

    public MyTest(String name, Acc acc) {
        super(name);
        this.acc = acc;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i == 6) {
                //设置名字为当前线程的名字
                acc.setName(getName());
            }
            System.out.println(acc.getName() + ",i=" + i);
        }
    }
}

class Acc {

    private ThreadLocal<String> name = new ThreadLocal<>();

    public Acc(String string) {
        this.name.set(string);
        System.out.println("----" + this.name.get());
    }

    public String getName() {
        return name.get();
    }

    public void setName(String str) {
        this.name.set(str);
    }
}