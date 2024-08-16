package com.hm.pattern.state.playerdemo;

// 播放器状态基类
abstract class PlayerState {
    protected Player player;

    public PlayerState(Player player) {
        this.player = player;
    }

    public abstract void play();

    public abstract void pause();

    public abstract void fastForward();

    public abstract void rewind();
}