package com.hm.effective.cp_three;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dumingwei on 2017/9/18.
 */
public class TestPhoneNumber {

    private static Map<PhoneNumber, String> map;

    public static void main(String[] args) {
        map = new HashMap<>();
        map.put(new PhoneNumber(707, 867, 5309), "Jenny");
        System.out.println(map.get(new PhoneNumber(707, 867, 5309)));
    }
}
