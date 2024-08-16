package com.hm.pattern.state.playerdemo;

/**
 * Created by p_dmweidu on 2024/6/7
 * Desc: 快退状态
 */
class RewindingState extends PlayerState {

    public RewindingState(Player player) {
        super(player);
    }

    @Override
    public void play() {
        System.out.println("已切换到播放状态");
        player.setState(new PlayingState(player));
    }

    @Override
    public void pause() {
        System.out.println("已切换到暂停状态");
        player.setState(new PausedState(player));
    }

    @Override
    public void fastForward() {
        System.out.println("已切换到快进状态");
        player.setState(new FastForwardingState(player));
    }

    @Override
    public void rewind() {
        System.out.println("已在快退状态,无需再次快退");
    }
}