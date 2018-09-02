package com.hm.pattern.bridge;

public class Red extends Color {
    @Override
    void drawColorShape(String shape) {
        System.out.println("red:" + shape);
    }
}
