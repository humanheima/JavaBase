package com.hm.generic;

/**
 * Created by dumingwei on 2017/12/6 0006.
 */
public class ArrayOfGeneric {

    static final int SIZE = 100;
    static Generic<Integer>[] gia;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        //cause java.lang.ClassCastException
        //gia = (Generic<Integer>[]) new Object[SIZE];
        gia = (Generic<Integer>[]) new Generic[SIZE];
        System.out.println(gia.getClass().getSimpleName());//Generic[]
        gia[0] = new Generic<Integer>();
        //gia[1]=new Object();//Compile-time error
        //gia[2]=new Generic<Double>();//Incompatiple type
    }
}
