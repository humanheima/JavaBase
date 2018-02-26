package com.hm.pattern.observe.build_in;

import java.util.Observable;

/**
 * Created by dumingwei on 2018/2/26 0026.
 */
public class MyObservable extends Observable {

    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }
}
