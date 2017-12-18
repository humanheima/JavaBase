package com.hm.generic;

import com.hm.generic.coffee.Generator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by dumingwei on 2017/12/4 0004.
 */
public class Store extends ArrayList<Aisle> {

    private ArrayList<CheckoutStand> checkouts = new ArrayList<>();
    private Office office = new Office();

    public Store(int nAisles, int nShelves, int nProducts) {
        for (int i = 0; i < nAisles; i++) {
            add(new Aisle(nShelves, nProducts));
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Aisle aisle : this) {
            for (Shelf shelf : aisle) {
                for (Product product : shelf) {
                    builder.append(product);
                    builder.append("\n");
                }
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Store(14,5,10));
    }
}

class Product {

    private final int id;
    private String description;
    private double price;

    public Product(int id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return id + ":" + description + ",price:$" + price;
    }

    public void priceChange(double change) {
        price += change;
    }

    public static Generator<Product> generator = new Generator<Product>() {

        private Random rand = new Random(47);

        @Override
        public Product next() {
            return new Product(rand.nextInt(1000), "test",
                    Math.round(rand.nextDouble() * 1000.0) + 0.99);
        }
    };

}

class Shelf extends ArrayList<Product> {

    public Shelf(int nProducts) {
        Generators.fill(this, Product.generator, nProducts);
    }
}

class Aisle extends ArrayList<Shelf> {

    public Aisle(int nShelves, int nProducts) {
        for (int i = 0; i < nShelves; i++) {
            add(new Shelf(nProducts));
        }
    }
}

class CheckoutStand {
}

class Office {
}

