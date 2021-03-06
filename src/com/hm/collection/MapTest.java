package com.hm.collection;

import com.hm.base.interview.Student;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by dumingwei on 2017/6/6.
 */
public class MapTest {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        Map<String, Student> map = new HashMap<>();
        map.put("java", new Student("dmw", 18));
        map.put("android", new Student("hm", 18));
       /* for (Map.Entry<String, Student> entry : map.entrySet()) {
            System.out.println("key:" + entry.getKey() + ",value" + entry.getValue());
        }*/
        System.out.println(map);

        Student student = map.get("java");
        System.out.println(student);
        student.setAge(19);

        System.out.println(student);


        System.out.println(map);

        //Map<String, Integer> map1=Collections.synchronizedMap(new HashMap<>());
        // map1.put("haha",33);
      /*  System.out.println(map.put("ios", 99));
        System.out.println(map.containsKey("ios"));
        System.out.println(map.containsValue(99));*/
        /*for (String key : map.keySet()) {
            System.out.println(key+",value:"+map.get(key));
        }
        map.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                System.out.println(s+","+integer);
            }
        });
        map.remove("java");
        System.out.println(map);
        System.out.println(3|3);*/
        // System.out.println(tableSizeFor(20));
       /* int a=2;
        int c=a<<1;
        System.out.println(a);
        System.out.println(c);*/
    }

    private static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
