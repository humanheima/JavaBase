package com.hm.generic;

/**
 * Created by dumingwei on 2017/12/13 0013.
 */
public class CaptureConversion {

    static <T> void f1(Holder<T> holder) {
        T t = holder.get();
        System.out.println(t.getClass().getSimpleName());
    }

    static void f2(Holder<?> holder) {
        f1(holder);
    }

    public static void main(String[] args) {
        Holder raw = new Holder<Integer>(1);
        //f1(raw);
        f2(raw);

        Holder rawBasic = new Holder();
        rawBasic.set(new Object());
        f2(rawBasic);
        Holder<?> wildcarded = new Holder<Double>(1.0);
        f2(wildcarded);
    }

}
