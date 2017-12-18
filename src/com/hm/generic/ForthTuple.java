package com.hm.generic;

/**
 * Created by dumingwei on 2017/11/30 0030.
 */
public class ForthTuple<A, B, C, D> extends ThreeTuple<A, B, C> {

    public final D forth;

    public ForthTuple(A first, B second, C third, D forth) {
        super(first, second, third);
        this.forth = forth;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + "," + third + "," + forth + ")";
    }
}
