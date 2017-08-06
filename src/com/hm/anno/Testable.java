package com.hm.anno;

import java.lang.annotation.*;

/**
 * Created by dumingwei on 2017/8/2.
 */
//注解可以保留到运行时，JVM可以提取该Annotation的信息
@Retention(value = RetentionPolicy.RUNTIME)
//用来修饰方法
@Target(ElementType.METHOD)
@Documented
public @interface Testable {
}
