package com.hm.base;

import com.hm.base.interview.Outter;

/**
 * Created by dumingwei on 2017/7/11.
 */
public class Test {


    public static void main(String[] args) {
        Outter outter = new Outter();
        Outter.Inner inner = outter.new Inner();

    }


}