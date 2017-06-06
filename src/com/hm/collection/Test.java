package com.hm.collection;

import org.omg.CORBA.IMP_LIMIT;

import java.util.*;

/**
 * Created by dumingwei on 2017/5/29.
 */
public class Test {

    private static ArrayList<String> list = new ArrayList<>(10);
    private static String string = "hello world I am a good boy";

    public static void main(String args[]) {
        sort();
    }

    private static void sort() {
        String[] arr = {"a", "b", "a", "b", "c", "a", "b", "c", "b"};
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                Integer value = map.get(arr[i]);
                value++;
                map.put(arr[i], value);
            }
        }
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key + "," + map.get(key));
        }
    }

    static class R implements Comparable<R> {

        int count;

        public R(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "R[count:" + count + "]";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj != null && obj.getClass() == R.class) {
                R r = (R) obj;
                return r.count == this.count;
            }
            return false;
        }

        @Override
        public int compareTo(R o) {

            return count > o.count ? 1 : count < o.count ? -1 : 0;
        }
    }
}
