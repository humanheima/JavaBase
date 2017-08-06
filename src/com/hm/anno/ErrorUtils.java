package com.hm.anno;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by dumingwei on 2017/8/2.
 */
public class ErrorUtils {

    @SafeVarargs
    public static void faultyMethod(List<String>... listStrArray) {
      /*  List[] listArray = listStrArray;
        List<Integer> myList = new ArrayList<>();
        myList.add(new Random().nextInt());
        listArray[0] = myList;
        String s = listStrArray[0].get(0);*/
    }

    public static void main(String[] args) {
        //faultyMethod(Arrays.asList("hello"));
    }

}
