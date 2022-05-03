package com.hm.picture_of_patten.abstract_factory.Sample.factory;

/**
 * Created by dumingwei on 2022/5/3.
 *
 * Desc: 这里扮演的角色是：抽象的零件
 */
public abstract class Link extends Item {

    protected String url;

    public Link(String caption, String url) {
        super(caption);
        this.url = url;
    }

}
