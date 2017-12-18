package com.hm.generic;

/**
 * Created by dumingwei on 2017/12/13 0013.
 */
public class ComparablePet implements Comparable<ComparablePet> {
    @Override
    public int compareTo(ComparablePet o) {
        return 0;
    }
}

class Cat extends ComparablePet implements Comparable<ComparablePet> {

    @Override
    public int compareTo(ComparablePet o) {
        return super.compareTo(o);
    }
}

