package com.hm.anno;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

/**
 * Created by dumingwei on 2017/8/6.
 */
public class ActionListenerInstaller {

    public static void processAnnotations(Object obj) {

        Class cl = obj.getClass();
        for (Field f : cl.getDeclaredFields()) {
            f.setAccessible(true);
            ActionListenerFor a = f.getAnnotation(ActionListenerFor.class);
            try {
                Object fObj = f.get(obj);
                if (a != null && fObj != null && fObj instanceof AbstractButton) {
                    Class<? extends ActionListener> listenerClazz = a.listener();
                    //通过反射来创建listener对象
                    ActionListener al = listenerClazz.newInstance();
                    AbstractButton ab = (AbstractButton) fObj;
                    ab.addActionListener(al);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

}
