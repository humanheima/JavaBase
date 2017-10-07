package com.hm.base.interview;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by dumingwei on 2017/9/30.
 */
public class MyStack<T> {

    private T[] elements;
    private int size = 0;

    private static final int INIT_CAPACITY = 16;

    public MyStack() {
        elements = ((T[]) new Object[INIT_CAPACITY]);

    }

    public void push(T elem) {
        ensureCapacity();
    }

    public T pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        elements[size] = null;
        T e = elements[--size];
        elements[size] = null;
        return e;
    }


    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
