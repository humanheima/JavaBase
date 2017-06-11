package com.hm.thread;

/**
 * Created by dumingwei on 2017/6/9.
 */
public class CarOnwer {

    private Car car;

    public CarOnwer(String name) {
        car = new Car(name);
    }

    /**
     * 停车
     */
    public void park() {
        synchronized (car) {
            try {
                System.out.println("开始停车");
                Thread.sleep(2000);
                leave();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 开走
     */
    public void leave() {
        System.out.println("停车结束，把车从车库开走");
    }
}

class Car {

    private String name;

    public Car(String name) {
        this.name = name;
    }
}
