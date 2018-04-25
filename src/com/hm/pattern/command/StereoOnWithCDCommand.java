package com.hm.pattern.command;

/**
 * Created by dumingwei on 2018/4/25 0025.
 */
public class StereoOnWithCDCommand implements Command {

    private Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void undo() {

    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11);
    }
}
