package com.hm.classloader;

/**
 * Created by dumingwei on 2017/6/25.
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        try {
            cl.loadClass("com.hm.classloader.Tester");
            System.out.println("系统加载Tester类");
            Class.forName("com.hm.classloader.Tester");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

