package com.hm.pattern.bridge;

public class Triangle extends Shape {

    public Triangle(Color color) {
        super(color);
    }

    @Override
    protected void draw() {
        color.drawColorShape("triangle");
    }
}
