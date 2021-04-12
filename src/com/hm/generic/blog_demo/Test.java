package com.hm.generic.blog_demo;

import java.util.Date;

/**
 * Created by dumingwei on 2021/4/2.
 * <p>
 * Desc:
 */
public class Test {

    public static void main(String[] args) {

        DateInter dateInter = new DateInter();
        dateInter.setValue(new Date());
        //dateInter.setValue(new Object()); //编译错误
    }
}
