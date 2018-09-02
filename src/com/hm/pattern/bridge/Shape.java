package com.hm.pattern.bridge;

public abstract class Shape {

    Color color;

    public Shape(Color color) {
        this.color = color;
    }

    protected abstract void draw();
}
