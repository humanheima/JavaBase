package com.hm.picture_of_patten.visitor;

public interface Element {

    /**
     * 接受访问者的访问
     * @param v
     */
    public abstract void accept(Visitor v);
}
