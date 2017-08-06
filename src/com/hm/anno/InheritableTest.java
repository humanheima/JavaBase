package com.hm.anno;

/**
 * Created by dumingwei on 2017/8/2.
 */
public class InheritableTest extends Base {

    public static void main(String[] args) {
        System.out.println(InheritableTest.class.isAnnotationPresent(Inheritable.class));
    }
}

@Inheritable
class Base {

}
