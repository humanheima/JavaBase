package com.hm.generic;

/**
 * Created by dumingwei on 2017/11/30 0030.
 */
public class TwoTuple<A, B> {

    public final A first;
    public final B second;

    public TwoTuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }
}
