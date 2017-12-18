package com.hm.generic;

/**
 * Created by dumingwei on 2017/11/30 0030.
 */
public class FiveTuple<A, B, C, D, E> extends ForthTuple<A, B, C, D> {

    public final E fifth;

    public FiveTuple(A first, B second, C third, D forth, E fifth) {
        super(first, second, third, forth);
        this.fifth = fifth;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + "," + third + "," + forth + "," + fifth + ")";
    }
}
