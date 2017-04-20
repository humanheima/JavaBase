package com.hm.pattern.state;

/**
 * Created by dumingwei on 2017/4/20.
 * 抽象状态角色
 */
public interface Mp3State {

    //开机
    void powerOn();

    //关机
    void powerOff();

    //上一首歌
    void preSong();

    //下一首歌
    void nextSong();
}
