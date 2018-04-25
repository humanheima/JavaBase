package com.hm.pattern.command;

/**
 * Created by dumingwei on 2018/4/24 0024.
 */
public class SimpleRemoteControl {

    private Command slot;

    public SimpleRemoteControl() {
    }

    public void setSlot(Command slot) {
        this.slot = slot;
    }

    public void buttonWasPressed() {
        slot.execute();
    }
}
