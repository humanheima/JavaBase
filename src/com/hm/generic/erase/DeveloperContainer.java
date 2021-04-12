package com.hm.generic.erase;

/**
 * Created by dumingwei on 2020/10/16.
 * <p>
 * Desc:
 */
public class DeveloperContainer<T extends AbsDeveloper> {

    private T contained;

    public void setContained(T contained) {
        this.contained = contained;
    }

    public T getContained() {
        return contained;
    }

}