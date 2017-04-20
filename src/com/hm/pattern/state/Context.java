package com.hm.pattern.state;

/**
 * Created by dumingwei on 2017/4/20.
 * 环境角色
 */
public class Context {

    private Mp3State mp3State;

    public void setMp3State(Mp3State mp3State) {
        this.mp3State = mp3State;
    }

    public void powerOn() {
        mp3State.powerOn();
        setMp3State(new PowerOnState());
    }

    public void powerOff() {
        mp3State.powerOff();
        setMp3State(new PowerOffState());
    }

    public void preSong() {
        mp3State.preSong();
    }

    public void nextSong() {
        mp3State.nextSong();
    }
}
