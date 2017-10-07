package com.hm.base;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by dumingwei on 2017/10/3.
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
        SoftReference<String> softReference = new SoftReference<String>("hi");
        WeakReference<String> str = new WeakReference<String>(new String("hello"));
        System.out.println(str.get());
        System.gc();
        System.out.println(str.get());
    }
}
