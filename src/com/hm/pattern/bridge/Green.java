package com.hm.pattern.bridge;

public class Green extends Color {
    @Override
    void drawColorShape(String shape) {
        System.out.println("green:" + shape);
    }
}
