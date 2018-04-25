package com.hm.pattern.command;

/**
 * Created by dumingwei on 2018/4/24 0024.
 */
public class Light {

    private String location = "";

    public Light(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println(location + "Light is On");
    }

    public void off() {
        System.out.println(location + "Light is Off");
    }
}
