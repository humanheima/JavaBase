package com.hm.classloader;

import sun.misc.Launcher;

import java.net.URL;

/**
 * Created by dumingwei on 2017/6/25.
 */
public class BootstrapTest {


    public static void main(String[] args) {
        try {
            URL[] urls = Launcher.getBootstrapClassPath().getURLs();
            for (int i = 0; i < urls.length; i++) {

                System.out.println(urls[i].toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //bootStrap();
        //ext();

        //system();


        ClassLoader loader = BootstrapTest.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader);
            loader = loader.getParent();
        }
    }

    /**
     * Bootstrap ClassLoader所加载的目录
     */
    private static void bootStrap() {
        String property = System.getProperty("sun.boot.class.path");
        String[] paths = property.split(":");

        for (String path : paths) {
            System.out.println(path);
        }

    }

    /**
     * 扩展类加载器 ExtClassLoader所加载的目录
     */
    private static void ext() {
        String extensions = System.getProperty("java.ext.dirs");
        String[] pathExtensions = extensions.split(":");

        for (String path : pathExtensions) {
            System.out.println(path);
        }
    }

    /**
     * 系统类加载器 AppClassLoader所加载的目录
     */
    private static void system() {
        String system = System.getProperty("java.class.path");
        String[] pathSystem = system.split(":");

        for (String path : pathSystem) {
            System.out.println(path);
        }
    }

}
