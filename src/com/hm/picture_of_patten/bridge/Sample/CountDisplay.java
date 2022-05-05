package com.hm.picture_of_patten.bridge.Sample;

/**
 * Created by dumingwei on 2022/5/5
 * <p>
 * Desc:
 */
public class CountDisplay extends Display {

    public CountDisplay(DisplayImpl impl) {
        super(impl);
    }

    public void multiDisplay(int times) {       // 循环显示times次
        open();
        for (int i = 0; i < times; i++) {
            print();
        }
        close();
    }
}
