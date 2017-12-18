package com.hm.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumingwei on 2017/12/4 0004.
 */
public class ListMaker<T> {

    List<T> create() {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        ListMaker<String> stringListMaker = new ListMaker<>();
        List<String> stringList = stringListMaker.create();
    }
}
