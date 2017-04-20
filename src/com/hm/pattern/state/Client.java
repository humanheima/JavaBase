package com.hm.pattern.state;

/**
 * Created by dumingwei on 2017/4/20.
 * 客户端调用
 */
public class Client {

    public static void main(String[] args) {

        Context context = new Context();
        context.setMp3State(new PowerOffState());
        context.preSong();
        context.powerOn();
        context.nextSong();
        context.powerOff();
    }
}
