package com.hm.pattern.bridge;

/**
 * Created by dumingwei on 2023/12/18
 *
 * 在这个例子中，Device 是抽象部分，Radio 和 TV 是实现部分，Remote 是桥接。
 * 通过这种方式，我们可以在不改变 Remote 类的情况下，添加更多的设备类型。
 */
public class Client {

    // 使用
    public static void main(String[] args) {
        Remote radioRemote = new Remote(new Radio());
        radioRemote.turnOn();
        radioRemote.turnOff();

        Remote tvRemote = new Remote(new TV());
        tvRemote.turnOn();
        tvRemote.turnOff();
    }

}

// 抽象部分
interface Device {
    void turnOn();

    void turnOff();
}

// 实现部分
class Radio implements Device {
    public void turnOn() {
        System.out.println("Radio is on");
    }

    public void turnOff() {
        System.out.println("Radio is off");
    }
}

class TV implements Device {
    public void turnOn() {
        System.out.println("TV is on");
    }

    public void turnOff() {
        System.out.println("TV is off");
    }
}

// 桥接
class Remote {
    protected Device device;

    public Remote(Device device) {
        this.device = device;
    }

    public void turnOn() {
        device.turnOn();
    }

    public void turnOff() {
        device.turnOff();
    }
}


