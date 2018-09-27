package com.hm.algorithm;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Test {

    private Scanner input = new Scanner(System.in);
    private String a;
    private String b;
    private int k;

    public Test() {
    }

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println("intput a string");
        test.a = test.input.nextLine();

        System.out.println("intput b string");
        test.b = test.input.nextLine();

        System.out.println("intput k");
        test.k = test.input.nextInt();

        //test.soutK("abab", "ababab", 2);
        test.soutK(test.a, test.b, test.k);
    }

    private void soutK(String a, String b, int k) {
        int result = 0;
        Set<String> aResult = new HashSet<>();
        for (int i = 0; i + k <= a.length(); i++) {
            aResult.add(a.substring(i, i + k));
        }
        for (String s : aResult) {
            for (int i = 0; i + k <= b.length(); i++) {
                String substring = b.substring(i, i + k);
                if (substring.equals(s)) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
