package com.hm.effective_java.chapter_two;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dumingwei on 2018/3/17 0017.
 */
public class ChapterTwoTest {

    public static void main(String[] args) {

       Elvis elvis1= Elvis.INSTANCE;
       Elvis elvis2= Elvis.INSTANCE;
       Elvis elvis3= Elvis.INSTANCE;
       elvis1.test();
       elvis2.test();
       elvis3.test();
        Map<String,String>map=new HashMap<>();
    }
}

