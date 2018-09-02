package com.hm.pattern.bridge;

public class Circle extends Shape {

    public Circle(Color color) {
        super(color);
    }

    @Override
    protected void draw() {
        color.drawColorShape("circle");

    }
}
