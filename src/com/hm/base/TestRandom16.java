package com.hm.base;

import java.util.Random;

/**
 * Created by dumingwei on 2018/6/22 0022.
 */
public class TestRandom16 {

    public static void main(String[] args) {
        while (true) {
            long time = System.currentTimeMillis();
            Random ran = new Random(time);
            long ranTime = ran.nextLong();
            String ranTimeStr = String.valueOf(ranTime);
            System.out.println(ranTimeStr.getBytes().length);
        }
    }
}
