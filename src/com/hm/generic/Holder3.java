package com.hm.generic;

/**
 * Created by dumingwei on 2017/11/30 0030.
 */
public class Holder3<T> {

    private T a;

    public Holder3(T a) {
        this.a = a;
    }

    public T get() {
        return a;
    }

    public static void main(String[] args) {
        Holder3<String> holder3 = new Holder3<>("hello world");
        System.out.println(holder3.get());
    }
}
