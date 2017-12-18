package com.hm.generic;

import java.lang.reflect.Array;

/**
 * Created by dumingwei on 2017/12/6 0006.
 */
public class GenericArrayWithTypeToken<T> {

    private T[] array;

    @SuppressWarnings("unchecked")
    public GenericArrayWithTypeToken(Class<T> type, int size) {
        array = (T[]) Array.newInstance(type, size);
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
        GenericArrayWithTypeToken<Integer> gai = new GenericArrayWithTypeToken<>(Integer.class
                , 10);
        Integer[] ia = gai.rep();//this now works
    }


}
