package com.hm.base.interview.overload_override_test;

/**
 * Created by dumingwei on 2017/9/27.
 */
public class SalesWaterEntity extends BaseEntity {

    private static final String TAG = "SalesWaterEntity:";

    @Override
    void test(int i) {
        super.test(i);
        i++;
        System.out.println(TAG + "test(int i)" + i);
    }
}
