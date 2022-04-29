package com.hm.picture_of_patten.prototype.framework;


/**
 * Created by dumingwei on 2022/4/29.
 * <p>
 * Desc: 需要继承Cloneable
 */
public interface Product extends Cloneable {

    public abstract void use(String s);

    public abstract Product createClone();
}
