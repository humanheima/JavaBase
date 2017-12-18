package com.hm.generic;

/**
 * Created by dumingwei on 2017/12/6 0006.
 */
public class GenericArray<T> {

    private T[] array;

    public GenericArray(int size) {
        this.array = (T[]) new Object[size];
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] rep() {
        return array;
    }

    public static void main(String[] args) {
        GenericArray<Integer> gai = new GenericArray<>(10);
        Object[] oa = gai.rep();//this is ok
        //Integer[] ia = gai.rep();//causes  classCatException
    }


}
