package com.hm.anno;

import java.lang.annotation.*;

/**
 * Created by dumingwei on 2017/8/6.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Persistent {
    String table();
}
