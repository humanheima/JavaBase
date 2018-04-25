package com.hm.pattern.command;

/**
 * Created by dumingwei on 2018/4/24 0024.
 */
public class SimpleRemoteControlTest {

    public static void main(String[] args) {
        SimpleRemoteControl control = new SimpleRemoteControl();
        Light light = new Light("");
        LightOnCommand lightOnCommand = new LightOnCommand(light);

        GarageDoor garageDoor = new GarageDoor("");
        GarageDoorUpCommand garageDoorUpCommand = new GarageDoorUpCommand(garageDoor);

        control.setSlot(lightOnCommand);
        control.buttonWasPressed();

        control.setSlot(garageDoorUpCommand);
        control.buttonWasPressed();
    }
}
