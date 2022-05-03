package com.hm.picture_of_patten.abstract_factory.Sample.factory;

/**
 * Created by dumingwei on 2022/5/3.
 * <p>
 * Desc: 这里扮演的角色是：抽象的零件
 */
public abstract class Item {

    //标题
    protected String caption;

    public Item(String caption) {
        this.caption = caption;
    }

    public abstract String makeHTML();
}
