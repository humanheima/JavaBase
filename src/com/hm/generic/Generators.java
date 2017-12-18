package com.hm.generic;

import com.hm.generic.coffee.Coffee;
import com.hm.generic.coffee.CoffeeGenerator;
import com.hm.generic.coffee.Generator;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by dumingwei on 2017/11/30 0030.
 */
public class Generators {

    public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++) {
            coll.add(gen.next());
        }
        return coll;
    }

    public static void main(String[] args) {
        Collection<Coffee> coffees = fill(new ArrayList<Coffee>(), new CoffeeGenerator(), 4);
        for (Coffee coffee : coffees) {
            System.out.println(coffee);
        }
        Collection<Integer> numbers = fill(new ArrayList<>(), new Fibonacci(), 12);
        for (Integer number : numbers) {
            System.out.print(number+" ");
        }
    }
}
