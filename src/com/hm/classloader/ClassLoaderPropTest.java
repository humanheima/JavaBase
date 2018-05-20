package com.hm.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by dumingwei on 2017/6/25.
 */
public class ClassLoaderPropTest {

    public static void main(String[] args) {
        //获取类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器：" + systemClassLoader);
        try {
            //获取系统加载器的加载路径
            Enumeration<URL> eml = systemClassLoader.getResources("");
            while (eml.hasMoreElements()) {
                System.out.println(eml.nextElement());
            }
            ClassLoader extensionLoader = systemClassLoader.getParent();
            System.out.println("扩展类加载器:" + extensionLoader);
            System.out.println("扩展类加载器的加载路径：" + System.getProperty("java.ext.dirs"));
            System.out.println("扩展类加载器的parent:"+extensionLoader.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
