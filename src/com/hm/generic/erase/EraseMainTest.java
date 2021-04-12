package com.hm.generic.erase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by dumingwei on 2021/4/11.
 * <p>
 * Desc:
 */
public class EraseMainTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ObjectContainer<Person> container = new ObjectContainer<>();

       /* Person forrest = new Person("Forrest", 110);
        container.setContained(forrest);

        Person outPerson = container.getContained();

        System.out.println(outPerson);*/

       /* try {
            Method method =ObjectContainer.class.getDeclaredMethod("setContained",Object.class);
            Person jenny = new Person("Jenny", 110);
            method.invoke(container,jenny);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(container.getContained());

        System.out.println("==============================");*/

       /* Method method = ObjectContainer.class.getDeclaredMethod("setContained", Object.class);
        String xiaodu = "xiaodu";
        method.invoke(container, xiaodu);
        //这样输出是没问题的
        System.out.println(container.getContained());*/

        Method method = ObjectContainer.class.getDeclaredMethod("setContained", Object.class);
        String xiaoya = "xiaoya";
        method.invoke(container, xiaoya);
        //这里就会有问题
        Person contained = container.getContained();
        System.out.println(contained);
    }
}
