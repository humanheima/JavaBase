package com.hm.picture_of_patten.state.otherdemo;

public class PausedState extends PlayerState {

    public PausedState(IPlayer player) {
        super(player);
    }
    @Override
    public void handle(int action) {
        switch (action) {
        case PlayingState.PLAY_OR_PAUSE:
            mPlayer.palyVedio();
            mPlayer.setState(new PlayingState(mPlayer));
            break;
        case PlayerState.STOP:
            mPlayer.stop();
            mPlayer.setState(new StoppedState(mPlayer));
            break;
        default:
            throw new IllegalArgumentException("ERROE ACTION:"+action+",current state:"+this.getClass().getSimpleName());
        }
    }
}

