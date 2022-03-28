package com.hm.picture_of_patten.adapter;

/**
 * Created by dumingwei on 2022/3/27.
 * <p>
 * Desc:
 */
public class Banner {

    private String string;

    public Banner(String string) {
        this.string = string;
    }

    public void showWithParen() {
        System.out.println("(" + string + ")");

    }

    public void showWithAster() {
        System.out.println("*" + string + "*");
    }

}
