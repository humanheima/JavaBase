package com.hm.pattern.state;

/**
 * Created by dumingwei on 2017/4/20.
 * 具体状态角色
 */
public class PowerOffState implements Mp3State {
    @Override
    public void powerOn() {
        System.out.println("开机");
    }

    @Override
    public void powerOff() {
    }

    @Override
    public void preSong() {
    }

    @Override
    public void nextSong() {
    }
}
