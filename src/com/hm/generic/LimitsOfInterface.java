package com.hm.generic;

import java.util.List;
import java.util.Map;

/**
 * Created by dumingwei on 2017/12/2 0002.
 */
public class LimitsOfInterface {

    static void f(Map<String, List<String>> args) {

    }

    public static void main(String[] args) {
        f(New.map());
        f(New.<String, List<String>>map());
    }

}
