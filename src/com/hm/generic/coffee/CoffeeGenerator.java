package com.hm.generic.coffee;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by dumingwei on 2017/11/29 0029.
 */
public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {

    private Class[] types = {Latte.class, Mocha.class, Cappuccino.class,
            Americano.class, Breve.class};

    public static Random rand = new Random(47);

    private int size = 0;

    public CoffeeGenerator() {
    }

    public CoffeeGenerator(int size) {
        this.size = size;
    }

    @Override
    public Coffee next() {
        try {
            return (Coffee) types[rand.nextInt(types.length)].newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    public static void main(String[] args) {
        CoffeeGenerator generator = new CoffeeGenerator();
        for (int i = 0; i < 5; i++) {
            System.out.println(generator.next());
        }
        for (Coffee coffee : new CoffeeGenerator(5)) {
            System.out.println(coffee);
        }
    }

    class CoffeeIterator implements Iterator<Coffee> {

        int count = size;

        public boolean hasNext() {
            return count > 0;
        }

        public Coffee next() {
            count--;
            return CoffeeGenerator.this.next();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
