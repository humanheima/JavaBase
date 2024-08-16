package com.hm.pattern.state.playerdemo;


/**
 * 播放器类
 */
class Player {

    private PlayerState state;

    public Player() {
        this.state = new PlayingState(this);
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public void play() {
        state.play();
    }

    public void pause() {
        state.pause();
    }

    public void fastForward() {
        state.fastForward();
    }

    public void rewind() {
        state.rewind();
    }
}