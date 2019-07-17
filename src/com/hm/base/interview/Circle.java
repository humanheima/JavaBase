package com.hm.base.interview;

public class Circle {

    private double radius = 0;
    public static int count = 1;

    public Circle(double radius) {
        this.radius = radius;
    }

    class Draw {     //内部类

        private double radius = 0;

        public void drawSahpe() {
            System.out.println(Circle.this.radius);  //外部类的private成员
            System.out.println(this.radius);  //自己的private成员
            System.out.println(count);   //外部类的静态成员
        }
    }

}
