package com.hm.picture_of_patten.facade;


import com.hm.picture_of_patten.facade.pagemaker.PageMaker;

/**
 * Created by dumingwei on 2022/5/8
 * <p>
 * Desc:测试类
 */
public class Main {

    public static void main(String[] args) {
        PageMaker.makeWelcomePage("hyuki@hyuki.com", "welcome.html");
    }
}
