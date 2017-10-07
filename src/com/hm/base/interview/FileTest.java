package com.hm.base.interview;

import java.io.IOException;

/**
 * Created by dumingwei on 2017/10/2.
 */
public class FileTest {

    public static void main(String[] args) {
        //MyUtil.walkDirectory(new File("D:\\fuDan"), 1);
        try {
            MyUtil.nioWalkDirectory("D:\\fuDan");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
