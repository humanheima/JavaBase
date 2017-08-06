package com.hm.anno;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.List;

/**
 * Created by dumingwei on 2017/8/6.
 */
@Target(ElementType.TYPE_USE)
@interface NotNull {
}

//定义类时使用Type Annotation
@NotNull
public class TypeAnnotationTest implements/*implements时使用Type Annotation*/ @NotNull Serializable {

    //方法形参中使用Type Annotation
    // throws时使用Type Annotation
    public static void main(@NotNull String[] args) throws @NotNull FileNotFoundException {

        Object obj = "fkjava.org";
        //强制类型转换时使用Type Annotation
        String str = (@NotNull String) obj;
        //创建对象时
        Object win = new @NotNull JFrame("疯狂软件");
    }

    //泛型中使用Type Annotation
    public void foo(List<@NotNull String> info) {

    }
}
