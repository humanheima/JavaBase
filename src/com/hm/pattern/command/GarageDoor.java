package com.hm.pattern.command;

/**
 * Created by dumingwei on 2018/4/24 0024.
 */
public class GarageDoor {

    private String location;

    public GarageDoor(String location) {
        this.location = location;
    }

    public void up() {
        System.out.println("Garage Door is open");
    }

    public void down() {
        System.out.println("Garage Door is close");
    }

    public void stop() {
        System.out.println("Garage Door is stop");
    }

    public void lightOn() {
        System.out.println("Garage Door is lightOn");
    }

    public void lightOff() {
        System.out.println("Garage Door is lightOff");
    }
}
