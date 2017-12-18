package com.hm.generic;

/**
 * Created by dumingwei on 2017/12/13 0013.
 */
public class CRGWithBasicHolder {

    public static void main(String[] args) {
        SubType st1 = new SubType();
        SubType st2 = new SubType();
        st1.set(st2);
        SubType st3 = st1.get();
        st1.f();
    }
}

class SubType extends BasicHolder<SubType> {

}
