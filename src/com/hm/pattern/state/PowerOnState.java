package com.hm.pattern.state;

/**
 * Created by dumingwei on 2017/4/20.
 * 具体状态角色
 */
public class PowerOnState implements Mp3State {
    @Override
    public void powerOn() {
        System.out.println("已开机");
    }

    @Override
    public void powerOff() {
        System.out.println("关机");
    }

    @Override
    public void preSong() {
        System.out.println("上一首歌");
    }

    @Override
    public void nextSong() {
        System.out.println("下一首歌");
    }
}
