package com.hm.java8;

import com.hm.java8.inter.DefaultInterface;

import java.util.function.Supplier;

/**
 * Created by dumingwei on 2017/3/13.
 * 接口中定义静态方法
 */
public interface DefaultFactory {

    static DefaultInterface create(Supplier<DefaultInterface> supplier) {
        return supplier.get();
    }
}
