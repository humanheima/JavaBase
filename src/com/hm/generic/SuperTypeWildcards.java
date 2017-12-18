package com.hm.generic;

import java.util.List;

/**
 * Created by dumingwei on 2017/12/12 0012.
 */
public class SuperTypeWildcards {

    static void writeTo(List<? super Apple> apples) {
        apples.add(new Apple());
        apples.add(new Jonathan());
        //apples.add(new Fruit());//error
    }

}
