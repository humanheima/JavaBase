package com.hm.picture_of_patten.builder.Sample;

/**
 * Created by dumingwei on 2022/5/2.
 * <p>
 * Desc: 构建者模式，用来生成复杂对象。
 */
public abstract class Builder {

    public abstract void makeTitle(String title);

    public abstract void makeString(String str);

    public abstract void makeItems(String[] items);

    public abstract void close();
}
