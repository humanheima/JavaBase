package com.hm.anno.saic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dumingwei on 2018/3/19 0019.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})

public @interface ASN1SizeConstraint {

    long max();
}
