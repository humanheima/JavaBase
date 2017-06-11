package com.hm.inner_class;

/**
 * Created by dumingwei on 2017/6/7.
 */
public class Cow {

    private double weight;

    public Cow(double weight) {
        this.weight = weight;
    }

    public static void main(String[] args) {
        Cow cow = new Cow(1024.0D);
        cow.test();
    }

    public void test() {
        CowLeg cl = new CowLeg(1.12D, "black");
        cl.info();
    }

    private class CowLeg {

        private double length;
        private String color;

        public CowLeg(double length, String color) {
            this.length = length;
            this.color = color;
        }

        private void info() {
            System.out.println("当前牛腿的颜色：" + color + ",高：" + length);
            //直接访问外部类的private修改时的成员变量
            System.out.println("本牛腿所在的牛的重量" + weight);
        }


        public double getLength() {
            return length;
        }

        public void setLength(double length) {
            this.length = length;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }


}
