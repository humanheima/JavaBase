package com.hm.picture_of_patten.abstract_factory.Sample.factory;

import java.util.ArrayList;

/**
 * Created by dumingwei on 2022/5/3.
 * <p>
 * Desc: 这里扮演的角色是：抽象的零件。
 */
public abstract class Tray extends Item {

    protected ArrayList<Item> tray = new ArrayList<>();

    public Tray(String caption) {
        super(caption);
    }

    public void add(Item item) {
        tray.add(item);
    }
}
