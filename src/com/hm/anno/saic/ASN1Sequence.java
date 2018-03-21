package com.hm.anno.saic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dumingwei on 2018/3/19 0019.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})//可以修饰类，接口（包括注解类型），和枚举定义
public @interface ASN1Sequence {

    String name();

    boolean isSet() default false;
}
