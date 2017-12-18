package com.hm.generic;

import java.util.ArrayList;

/**
 * Created by dumingwei on 2017/12/4 0004.
 */
public class ErasedTypeEquivalence {

    public static void main(String[] args) {

        Class c1=new ArrayList<String>().getClass();
        Class c2=new ArrayList<Integer>().getClass();
        System.out.println(c1==c2);
    }
}
