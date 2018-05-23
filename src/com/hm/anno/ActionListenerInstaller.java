package com.hm.anno;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

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
                if (a != null && fObj instanceof AbstractButton) {
                    Class<? extends ActionListener> listenerClazz = a.listener();
                    //通过反射来创建listener对象
                    ActionListener al = listenerClazz.getDeclaredConstructor().newInstance();
                    AbstractButton ab = (AbstractButton) fObj;
                    ab.addActionListener(al);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
