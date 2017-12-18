package com.hm.generic;

import java.util.List;

/**
 * Created by dumingwei on 2017/12/6 0006.
 */
public class Holder<T> {

    private T value;

    public Holder() {
    }


    public Holder(T value) {
        this.value = value;
    }

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return value.equals(obj);
    }

    public static void main(String[] args) {
        Holder<Apple> apple = new Holder<>(new Apple());
        Apple d = apple.get();
        apple.set(d);
        //Holder<Fruit>fruit=apple;//cannot upcast
        Holder<? extends Fruit> fruit = apple;
        Fruit p = fruit.get();
        d = (Apple) fruit.get();

        try {
            Orange c = (Orange) fruit.get();
        } catch (Exception e) {
            System.out.println(e);
        }
        //fruit.set(new Apple());//cannot call set
        //fruit.set(new Fruit());//cannot call set
        System.out.println(fruit.equals(d));
    }
}

class Fruit {

    public <T> void test(T t) {
        System.out.println(t.getClass().getSimpleName());
    }
}

class Apple extends Fruit {

    public <T> void test(List<T> list) {

        T a;
    }
}

class Jonathan extends Apple {
}

class Orange extends Fruit {
}
