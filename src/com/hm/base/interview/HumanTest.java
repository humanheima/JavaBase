package com.hm.base.interview;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dumingwei on 2017/10/1.
 */
public class HumanTest {

    Map map;
    List list;
    static Set<String> set;

    public static void main(String[] args) throws Exception {
        try {
            try {
                throw new Sneeze();
            } catch (Annoyance a) {
                System.out.println("Caught Annoyance");
                throw a;
            }
        } catch (Sneeze s) {
            System.out.println("Caught Sneeze");
            return;
        } finally {
            System.out.println("Hello world!");
        }

    }
}

class Annoyance extends Exception {

}

class Sneeze extends Annoyance {

}
