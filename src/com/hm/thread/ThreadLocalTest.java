package com.hm.thread;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocalAccount account = new ThreadLocalAccount("初始名");
        new TestThread("线程甲", account).start();
        new TestThread("线程乙", account).start();
    }
}

class TestThread extends Thread {

    private ThreadLocalAccount account;

    public TestThread(String name, ThreadLocalAccount account) {
        super(name);
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i == 6) {
                //当i==6的时候替换成当前线程名
                account.setName(getName());
            }
            System.out.println(account.getName() + "账户的i值：" + i);
        }
    }
}

class ThreadLocalAccount {

    private ThreadLocal<String> name = new ThreadLocal<>();

    public ThreadLocalAccount(String name) {
        this.name.set(name);
        System.out.println(this.name.get());
    }

    public String getName() {
        return name.get();
    }

    public void setName(String str) {
        this.name.set(str);
    }
}


