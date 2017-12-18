package com.hm.generic;

/**
 * Created by dumingwei on 2017/12/4 0004.
 */
public abstract class CreatorGeneric {

    public static void main(String[] args) {
        Creator c = new Creator();
        c.f();
    }
}

class X {
}

class Creator extends GenericWithCreate<X> {

    @Override
    X create() {
        return new X();
    }

    void f() {
        System.out.println(element.getClass().getSimpleName());
    }
}

abstract class GenericWithCreate<T> {

    final T element;

    public GenericWithCreate() {
        this.element = create();
    }

    abstract T create();
}

