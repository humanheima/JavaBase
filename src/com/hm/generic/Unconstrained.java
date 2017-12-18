package com.hm.generic;

/**
 * Created by dumingwei on 2017/12/13 0013.
 */
public class Unconstrained {

    public static void main(String[] args) {
        BasicOther b = new BasicOther(), b2 = new BasicOther();
        b.set(new Other());
        Other other = b.get();
        b.f();
    }

}

class Other {

}

class BasicOther extends BasicHolder<Other> {

}


