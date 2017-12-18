package com.hm.generic;

import com.hm.generic.coffee.Generator;

import java.util.*;

/**
 * Created by dumingwei on 2017/12/4 0004.
 * 泛型用于内部类和匿名内部类
 */
public class BankTeller {

    public static void serve(Teller t, Customer c) {
        System.out.println(t + " serves " + c);
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        Queue<Customer> line = new LinkedList<>();
        Generators.fill(line, Customer.generator(), 15);
        List<Teller> tellers = new ArrayList<>();
        Generators.fill(tellers, Teller.generator, 4);
        for (Customer customer : line) {
            serve(tellers.get(rand.nextInt(tellers.size())), customer);
        }
    }

}

class Customer {

    private static long counter = 1;
    private final long id = counter++;

    private Customer() {
    }

    @Override
    public String toString() {
        return "Customer " + id;
    }

    public static Generator<Customer> generator() {
        return new Generator<Customer>() {
            @Override
            public Customer next() {
                return new Customer();
            }
        };
    }

}

class Teller {

    private static long counter = 1;
    private final long id = counter++;

    private Teller() {
    }

    @Override
    public String toString() {
        return "Teller " + id;
    }

    public static Generator<Teller> generator = new Generator<Teller>() {
        @Override
        public Teller next() {
            return new Teller();
        }
    };
}
