package com.hm.effective_java.chapter_three;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dumingwei on 2017/9/12.
 */
public class CounterPoint extends Point {

    private static final AtomicInteger counter = new AtomicInteger();

    public CounterPoint(int x, int y) {
        super(x, y);
        counter.incrementAndGet();
    }

    public int numberCreated() {
        return counter.get();
    }


}
