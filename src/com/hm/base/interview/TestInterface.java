package com.hm.base.interview;

/**
 * Created by dumingwei on 2017/10/8.
 */
public class TestInterface extends InterFirst implements InterSecond {

    public static void main(String[] args) {
        InterSecond testInterface = new TestInterface();
        testInterface.run();
    }


    @Override
    public void run() {
    }
}

class InterFirst {
    void run() {
        System.out.println("InterFirst");
    }


}

interface InterSecond {
    void run();
}
