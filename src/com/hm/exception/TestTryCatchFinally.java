package com.hm.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by dumingwei on 2017/12/13 0013.
 * 在使用try..catch..finally块的时候，注意千万不要在finally块中使用return，因为finally中的return会覆盖已有的返回值
 */
public class TestTryCatchFinally {

    public static void main(String[] args) {
        String str = new TestTryCatchFinally().openFile();
        System.out.println(str);
    }

    private String openFile() {
        try {
            FileInputStream inputStream = new FileInputStream("x:/a.txt");
            int ch = inputStream.read();
            System.out.println("aaa");
            return "step1";
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return "step2";
        } catch (IOException e) {
            System.out.println("io exception");
            return "step3";
        } finally {
            System.out.println("finally block");
            //return "finally";
        }
    }
}
