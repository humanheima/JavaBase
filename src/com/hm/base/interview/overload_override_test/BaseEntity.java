package com.hm.base.interview.overload_override_test;

/**
 * Created by dumingwei on 2017/9/27.
 */
public class BaseEntity {

    private static final String TAG = "BaseEntity:";

    void test(int i) {
        System.out.println(TAG + "test(int i)" + i);
    }

    void test(byte b) {
        System.out.println(TAG + "test(byte b)" + b);
    }
}
