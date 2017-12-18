package com.hm.generic;

/**
 * Created by dumingwei on 2017/12/4 0004.
 */
public class ErasureAndInheritance {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Derived2 d2 = new Derived2();
        Object object = d2.get();
        d2.set(object);
    }
}

class GenericBase<T> {

    private T element;

    public void set(T element) {
        this.element = element;
    }

    public T get() {
        return element;
    }
}

class Derived1<T> extends GenericBase<T> {

}

class Derived2 extends GenericBase {

}
