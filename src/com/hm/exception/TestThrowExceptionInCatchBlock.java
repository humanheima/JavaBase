package com.hm.exception;

import java.io.File;
import java.io.IOException;

/**
 * 测试在 catch块中抛出异常
 */
public class TestThrowExceptionInCatchBlock {

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test() throws Exception{
        try {
            System.out.println("in try");
            //throw new IOException("io exception");
            throwIo();
            throw new IndexOutOfBoundsException("out of index");

        }catch (IOException io){
            System.out.println(io.getMessage());
        }catch (NullPointerException nu){
            System.out.println(nu.getMessage());
            System.out.println(nu.getMessage());
        }finally {
            System.out.println("finally");
        }
        System.out.println("hhaha");
    }

    private static void throwIo() throws IOException {
    }


}
