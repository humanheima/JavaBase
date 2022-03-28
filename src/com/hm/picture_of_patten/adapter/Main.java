package com.hm.picture_of_patten.adapter;

/**
 * Created by dumingwei on 2022/3/27.
 *
 * Desc: 适配器模式的最大好处就是不用修改已经存在的类，不然修改了存在的类，还需要重新测试。
 * 适配器模式用于填补具有不同接口的两个类直接按的缝隙。
 */
public class Main {

    public static void main(String[] args) {
        Print p = new PrintBanner("Hello");
        p.printWeak();
        p.printStrong();
    }
}
