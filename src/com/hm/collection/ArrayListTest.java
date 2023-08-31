package com.hm.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dumingwei on 2017/6/5.
 */
public class ArrayListTest {

    public static void main(String[] args) {
        /*ArrayList<String> arrayList = new ArrayList();
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
         arrayList.subList(1, 3).clear();
         System.out.println(arrayList);
        arrayList.trimToSize();
        arrayList.iterator();
        arrayList.clear();
        testSubList();
        testRemoveInForEach();*/
        //testRemoveInForEach();
        testSubListWithSize();
    }

    private static void testSubList() {

        List<String> arrayList = new ArrayList<>();
        String[] strings = new String[arrayList.size()];
        arrayList.toArray(strings);
        Arrays.asList("a", "b", "c");
        arrayList.add("string1");
        arrayList.add("string2");
        arrayList.add("string3");
        arrayList.add("string4");
        arrayList.add("string4");
        List<String> subList = arrayList.subList(0, 2);
        System.out.println(arrayList);
        System.out.println(subList);
        subList.clear();
        System.out.println(arrayList);
        System.out.println(subList);
    }

    private static void testRemoveInForEach() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        Iterator<String> it = arrayList.iterator();
        int i = 0;
        /**
         * arrayList 返回的迭代器的hasNext()方法
         *  public boolean hasNext() {
         *             return cursor != size;
         *         }
         */
        while (it.hasNext()) {
            System.out.println("size=" + arrayList.size());
            System.out.println(i++);
            String temp = it.next();
            if ("2".equals(temp)) {
                arrayList.remove(temp);
            }
        }
        System.out.println(arrayList);

    }

    public static void testSubListWithSize() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add("" + i);
        }
        List<List<String>> sublists = sublistBySize(list, 30);
        for (List<String> sublist : sublists) {
            System.out.println(sublist);
            System.out.println();
        }

    }

    public static List<List<String>> sublistBySize(List<String> list, int size) {
        List<List<String>> sublists = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            int endIndex = Math.min(i + size, list.size());
            sublists.add(list.subList(i, endIndex));
        }
        return sublists;
    }

}
