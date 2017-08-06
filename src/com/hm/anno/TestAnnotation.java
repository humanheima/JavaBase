package com.hm.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by dumingwei on 2017/7/28.
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {

    //定义带两个成员变量的Annotation
    //Annotation 中的成员变量以方法的形式来定义
    //其方法名和返回值定义了该成员变量的名字和类型
    String name();

    int age();

    int height() default 163;
}
