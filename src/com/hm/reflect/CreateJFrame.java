package com.hm.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by dumingwei on 2017/6/25.
 */
public class CreateJFrame {

    public static void main(String[] args) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {

        Class<?>jframeClazz=Class.forName("javax.swing.JFrame");
        Constructor ctor=jframeClazz.getConstructor(String.class);
        Object obj=ctor.newInstance("测试窗口");
        System.out.println(obj);
    }
}
