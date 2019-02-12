package com.hm.base;

/**
 * Created by dumingwei on 2017/7/11.
 */
public class Test {


    public static void main(String[] args) {
        /*Shape shape = new Circle();
        System.out.println("shape.name=" + shape.name);
        shape.printType();
        shape.printName();
        System.out.println(Integer.valueOf("1.0"));*/
        //System.out.println(sum(10000));
         Shape shape=new Shape();
         shape=new Shape();


    }

    public static long sum(int n) {
        if (n <= 0) return 0;
        return n + sum(n - 1);
    }

}

class Shape {

    static {
        System.out.println("Shape static block");
    }
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