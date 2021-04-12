package com.hm.generic.erase;

/**
 * Created by dumingwei on 2021/4/11.
 * <p>
 * Desc:
 */
public class AndroidDeveloper extends AbsDeveloper {

    public AndroidDeveloper(String name, int age) {
        super(name, age);
    }

    @Override
    public String workType() {
        return "Android";
    }
}
