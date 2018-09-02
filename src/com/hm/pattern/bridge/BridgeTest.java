package com.hm.pattern.bridge;

public class BridgeTest {

    public static void main(String[] args) {

        Color red = new Red();
        Shape rectangle = new Rectangle(red);
        Shape circle = new Circle(red);

        Color blue = new Blue();

        Shape rectangleBlue = new Rectangle(blue);
        Shape circleBlue = new Circle(blue);

        rectangle.draw();
        circle.draw();

        rectangleBlue.draw();
        circleBlue.draw();
    }


}
