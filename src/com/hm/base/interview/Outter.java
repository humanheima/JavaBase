package com.hm.base.interview;

public class Outter {

    private int a = 10;

    public Outter() {
    }

    public class Inner {
        public Inner() {

            System.out.println(a);
        }
    }
}
