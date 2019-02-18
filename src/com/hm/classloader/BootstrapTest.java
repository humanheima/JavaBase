package com.hm.classloader;

import java.net.URL;

/**
 * Created by dumingwei on 2017/6/25.
 */
public class BootstrapTest {


    public static void main(String[] args) {
       /* try {
            URL[] urls = Launcher.getBootstrapClassPath().getURLs();
            for (int i = 0; i < urls.length; i++) {

                System.out.println(urls[i].toExternalForm());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /**
         * Bootstrap ClassLoader所加载的目录
         */
        String property = System.getProperty("sun.boot.class.path");
        String[] paths = property.split(":");

        for (String path : paths) {
            System.out.println(path);
        }

        System.out.println("----------------------------");
        /**
         * ExtClassLoader所加载的目录
         */
        String extensions = System.getProperty("java.ext.dirs");
        String[] pathExtensions = extensions.split(":");

        for (String path : pathExtensions) {
            System.out.println(path);
        }

        System.out.println("----------------------------");
        /**
         * AppClassLoader所加载的目录
         */
        String system = System.getProperty("java.class.path");
        String[] pathSystem = system.split(":");

        for (String path : pathSystem) {
            System.out.println(path);
        }

        System.out.println("----------------------------");

        ClassLoader loader = BootstrapTest.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader);
            loader = loader.getParent();
        }
    }
}
