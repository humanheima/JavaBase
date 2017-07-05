package com.hm.classloader;

import java.net.URL;

/**
 * Created by dumingwei on 2017/6/25.
 */
public class BootstrapTest {

    public static void main(String[] args) {
        try {
            URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
            for (int i = 0; i < urls.length; i++) {

                System.out.println(urls[i].toExternalForm());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
