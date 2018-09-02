package com.hm.pattern.bridge;

public class Blue extends Color {
    @Override
    void drawColorShape(String shape) {
        System.out.println("blue:" + shape);
    }
}
