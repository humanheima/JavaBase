package com.hm.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumingwei on 2017/6/5.
 */
public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("string1");
        arrayList.add("string2");
        arrayList.add("string3");
        arrayList.add("string4");
        arrayList.add("string4");
        System.out.println(arrayList);
        arrayList.add(0, new String("string1"));
        arrayList.get(0);
        arrayList.set(0, "easy");
        arrayList.contains("easy");
        arrayList.remove(0);
        arrayList.remove("easy");
       // arrayList.subList(1, 3).clear();
       // System.out.println(arrayList);
        arrayList.trimToSize();
        arrayList.iterator();
        //arrayList.clear();

    }
}
