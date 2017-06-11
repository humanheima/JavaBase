package com.hm.thread;

/**
 * Created by dumingwei on 2017/6/9.
 */
public class PracticeCar {

    public static void main(String[] args) {
        Park park=new Park();
        CarInThread carInThread=new CarInThread(park);
        CarOutThread carOutThread=new CarOutThread(park);
        carInThread.start();
        carOutThread.start();
    }
}

class CarInThread extends Thread {

    private Park park;

    public CarInThread(Park park) {
        this.park = park;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 1; i < 100; i++) {
            park.CarIn(i);
        }
    }
}


class CarOutThread extends Thread {

    private Park park;

    public CarOutThread(Park park) {
        this.park = park;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 1; i < 100; i++) {
            park.CarOut(i);
        }
    }
}

