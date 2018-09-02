package com.hm.pattern.bridge;

public class Rectangle extends Shape {

    public Rectangle(Color color) {
        super(color);
    }

    @Override
    protected void draw() {
       color.drawColorShape("rectangle");
    }
}
