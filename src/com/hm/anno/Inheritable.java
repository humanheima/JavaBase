package com.hm.anno;

import java.lang.annotation.*;

/**
 * Created by dumingwei on 2017/8/2.
 */
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
public @interface Inheritable {
}
