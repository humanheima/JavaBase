package com.hm.picture_of_patten.flyweight;

/**
 * Created by dumingwei on 2022/5/11
 * <p>
 * Desc:
 */

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Main digits");
            System.out.println("Example: java Main 1212123");
            System.exit(0);
        }
        BigString bs = new BigString(args[0]);
        bs.print();
    }
}
