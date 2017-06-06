package com.hm.io;

import java.io.Serializable;

/**
 * Created by dumingwei on 2017/6/1.
 */
public class Animal implements Serializable {

    protected int eyes;


    public int getEyes() {
        return eyes;
    }

    public void setEyes(int eyes) {
        this.eyes = eyes;
    }
}
