package com.hm.reflect;

import com.hm.pattern.bridge.Blue;
import com.hm.pattern.bridge.Color;
import com.hm.pattern.bridge.Rectangle;
import com.hm.pattern.bridge.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumingwei on 2017/6/25.
 */
public class GenericTest {

    public static void main(String[] args) {

        List<Shape> des = new ArrayList<>();
        List<Shape> src = new ArrayList<>();

        src.add(new Rectangle(new Blue()));

        copy(des, src);
    }


    public static void test0(List<Object> objectList) {
        for (Object o : objectList) {
            System.out.println(o);
        }
    }

    public static void test1(List<?> objectList) {
        for (Object o : objectList) {
            System.out.println(o);
        }

        //无法添加元素
        //objectList.add(new Object());
    }

    /**
     * 只能取数据，不能加数据，因为你不知道
     *
     * @param objectList
     */
    public static void test(List<? extends Shape> objectList) {
        for (Object o : objectList) {
            System.out.println(o);
        }
    }


    /**
     * @param des
     * @param src
     * @param <T>
     * @return
     */
    public static <T> T copy(List<? super T> des, List<T> src) {
        T last = null;
        for (T t : src) {
            last = t;
            des.add(t);
        }
        T t = (T) des.get(0);
        System.out.println(t);
        return last;
    }
}
