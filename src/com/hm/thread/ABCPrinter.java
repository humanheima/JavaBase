package com.hm.thread;


/**
 * Created by p_dmweidu on 2024/8/16
 * Desc: 交替打印ABC
 * 使用同步代码块synchronized、wait、notifyAll控制线程执行顺序
 *
 * 参考链接：https://blog.csdn.net/jike11231/article/details/118303660
 */
public class ABCPrinter {

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadPrinter pa = new ThreadPrinter("A", c, a);
        ThreadPrinter pb = new ThreadPrinter("B", a, b);
        ThreadPrinter pc = new ThreadPrinter("C", b, c);

        new Thread(pa).start();
        Thread.sleep(10);// 保证初始ABC的启动顺序
        new Thread(pb).start();
        Thread.sleep(10);
        new Thread(pc).start();
        Thread.sleep(10);
    }

    public static class ThreadPrinter implements Runnable {
        private String name;//线程名称
        private Object prev;//前一个线程
        private Object self;//当前线程

        private ThreadPrinter(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        public void run() {
            int count = 9;
            while (count > 0) { //多线程并发，不能用if，必须使用while循环
                synchronized (prev) { //先获取prev锁
                    synchronized (self) { //再获取self锁
                        System.out.print(name+"\n");
                        count--;
                        self.notifyAll(); //self锁执行完，唤醒其他线程竞争self锁，，注意此时self锁并未立即释放。
                        //notifyAll()方法（唤醒所有 wait 线程）
                        //notify()方法（只随机唤醒一个 wait 线程）
                    }
                    // 此时执行完self的同步块，这时self锁才释放。
                    try {
                        if (count == 0) { //代表最后一次打印，则唤醒所有prev进程，释放prev对象锁
                            prev.notifyAll();
                        } else {
                            prev.wait(); // 立即释放 prev锁，当前线程休眠，等待唤醒
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

}
