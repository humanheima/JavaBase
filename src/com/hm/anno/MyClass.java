package com.hm.anno;

import java.lang.annotation.Annotation;

/**
 * Created by dumingwei on 2017/8/2.
 */
public class MyClass {

    @TestAnnotation(name = "dumingwei", age = 25, height = 163)
    public void info() {

    }

    @Testable
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        try {
            Annotation[] annotations = myClass.getClass().getMethod("info").getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof TestAnnotation) {
                    System.out.println(((TestAnnotation) annotation).name() + "," + ((TestAnnotation) annotation).age()
                            + "," + ((TestAnnotation) annotation).height());
                }
                System.out.println("-------------------");
                System.out.println(annotation);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
