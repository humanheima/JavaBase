package com.hm.anno;

import java.lang.annotation.*;

/**
 * Created by dumingwei on 2018/3/19 0019.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(FkTags.class)
public @interface FkTag {

    String name() default "疯狂软件";

    int age();

}
