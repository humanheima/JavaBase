package com.hm.generic.erase;

/**
 * Created by dumingwei on 2020/10/16.
 * <p>
 * Desc:
 */
public class ObjectContainer<T> {

    private T contained;

    public ObjectContainer(T contained) {
        this.contained = contained;
    }

    public void setContained(T contained){
        this.contained = contained;
    }

    public T getContained() {
        return contained;
    }

    @Override
    public String toString() {
        return contained.toString();
    }
}