package com.hm.generic.erase;

/**
 * Created by dumingwei on 2021/4/11.
 * <p>
 * Desc:
 */
public class JavaDeveloper extends AbsDeveloper {

    public JavaDeveloper(String name, int age) {
        super(name, age);
    }

    @Override
    public String workType() {
        return "Java";
    }
}
