package com.hm.anno;

import java.lang.annotation.*;

/**
 * Created by dumingwei on 2017/8/6.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Id {

    String column();

    String type();

    String generator();


}
