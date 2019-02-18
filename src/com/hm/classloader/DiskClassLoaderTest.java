package com.hm.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by dumingwei on 2019/2/18
 * <p>
 * Desc:
 */
public class DiskClassLoaderTest {

    public static void main(String[] args) {
        DiskClassLoader diskClassLoader = new DiskClassLoader("/Users/dumingwei/IdeaProjects/JavaBase");
        try {
            Class c =diskClassLoader.loadClass("com.hm.classloader.Jobs");
            if (c!=null){
                try {
                    Object obj=c.newInstance();
                    System.out.println(obj.getClass().getClassLoader());
                    Method method=c.getDeclaredMethod("say",null);
                    method.invoke(obj,null);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
