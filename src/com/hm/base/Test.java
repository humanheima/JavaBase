package com.hm.base;

/**
 * Created by dumingwei on 2017/7/11.
 */
public class Test {

    public static void main(String[] args) {
        Shape shape = new Circle();
        System.out.println("shape.name=" + shape.name);
        shape.printType();
        shape.printName();
        System.out.println(Integer.valueOf("1.0"));
    }
}

class Shape {
    public String name = "shape";

    public Shape() {
        System.out.println("shape constructor");
    }

    public void printType() {
        System.out.println("this is shape");
    }

    public static void printName() {
        System.out.println("shape");
    }
}

class Circle extends Shape {

    public static String name = "circle";

    public Circle() {
        System.out.println("circle constructor");
    }

    public void printType() {
        System.out.println("this is circle");
    }

    public static void printName() {
        System.out.println("circle");
    }
}