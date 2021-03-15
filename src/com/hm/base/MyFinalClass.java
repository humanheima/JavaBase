package com.hm.base;

/**
 * Created by dumingwei on 2020/7/22.
 * <p>
 * Desc:
 */
class MyFinalClass {

    public final int i = (int) (100 * Math.random());
    public static int j = (int) (100 * Math.random());

    void changeValue(StringBuffer buffer) {
        buffer = new StringBuffer();
    }
}