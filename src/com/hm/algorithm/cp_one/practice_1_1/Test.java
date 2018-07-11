package com.hm.algorithm.cp_one.practice_1_1;

import com.hm.algorithm.algs4.StdIn;
import com.hm.algorithm.algs4.StdOut;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Scanner;

/**
 * Created by dumingwei on 2018/6/6 0006.
 */
public class Test {

    public static void main(String[] args) throws IOException {
     /*   System.out.println(15 / 2);
        System.out.println(2.0e-6 * 100000000.1);
        System.out.println();
        System.out.println((1 + 2.236) / 2);
        System.out.println(1 + 2 + 3 + 4.0);
        System.out.println(4.1 >= 4);
        System.out.println(1 + 2 + "3");*/
        //test6();
        //test7();
        //test8();
       /* for (int i = 1; i < 10; i++) {
            test9(i);
        }*/
        //test10();
        //test13();
       /* for (int i = 1; i <= 10; i++) {
            test14(i);
        }*/

       /* int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = i % 2 + 1;
        }
        a[9] = 100;
        test15(a, a.length);*/

        /*for (int i = 0; i < 10; i++) {
            System.out.println(exR1(i));
        }*/

//        System.out.println(mystery(2,25));
//        System.out.println(mystery(3,31));
//        System.out.println(mystery(3,4));

        //test21();

//        int[] arr = {1, 3, 4, 5, 7, 8, 10, 17};
//        int index = rank(10, arr, 0, arr.length - 1, 0);
//        System.out.println(index);

        //test24();
        /*int[] arr = {1, 2, 2, 3, 3, 4, 4, 4};
        int index = BinarySearch.rank(arr, 4);
        int result = BinarySearch.count(arr, 4);
        System.out.println(index);
        System.out.println(result);*/
        //testScanner();
        System.out.println("dog".compareTo("cat"));
    }

    private static void testScanner() {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in),"UTF-8");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
        /*while (!StdIn.isEmpty()){
            System.out.println(StdIn.readString());
        }*/
    }

    private static void test24() {
        Scanner scanner = new Scanner(System.in);

        int a;
        int b;
        while (true) {
            System.out.println("please input two numbers,-1 means quit");
            a = scanner.nextInt();
            if (a == -1) {
                break;
            }
            b = scanner.nextInt();
            int gcd = gcd(a, b, 0);

            System.out.println("Greatest common divisor is:" + gcd);
        }
    }

    /**
     * 计算两个数的最大公约数
     *
     * @param p
     * @param q
     * @param times 递归调用次数
     */
    private static int gcd(int p, int q, int times) {
        for (int i = 0; i < times; i++) {
            System.out.print("    ");
        }
        System.out.println("p=" + p + ",q=" + q);
        if (q == 0) {
            return p;
        }
        int r = p % q;
        times++;
        return gcd(q, r, times);
    }

    /**
     * @param key   要查找的值
     * @param a     数组
     * @param lo    0
     * @param hi    数组长度减去1
     * @param times 用来记录递归的次数
     * @return
     */
    private static int rank(int key, int[] a, int lo, int hi, int times) {
        for (int i = 0; i < times; i++) {
            System.out.print("    ");
        }
        System.out.println("lo=" + lo + ",hi=" + hi);
        if (lo > hi) {
            return -1;
        }
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) {
            times++;
            return rank(key, a, lo, mid, times);
        } else if (key > a[mid]) {
            times++;
            return rank(key, a, mid, hi, times);
        } else {
            return mid;
        }
    }

    private static void test21() {
        List<String> strList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;
        try {
            while (!"break".equals(line = br.readLine())) {
                strList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String name;
        double a, b;
        for (String s : strList) {
            String[] result = s.split(" ");
            name = result[0];
            a = Double.parseDouble(result[1]);
            b = Double.parseDouble(result[2]);
            if (b != 0) {
                StdOut.printf("%s %f %f %.3f", name, a, b, a / b);
            }
            StdOut.println();

        }

    }

    private static int mystery(int a, int b) {
        if (b == 0) return 1;
        if (b % 2 == 0) return mystery(a * a, b / 2);
        return mystery(a * a, b / 2) * a;
    }

    private static String exR1(int n) {
        if (n <= 0) {
            return "";
        }
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }

    private static void test15(int[] a, int m) {
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            int ai = a[i];
            System.out.print(" " + ai);
            if (a[i] < m) {
                arr[ai]++;
            }
        }
        System.out.println();
        int total = 0;
        for (int i = 0; i < m; i++) {
            total += arr[i];
            System.out.print(" " + arr[i]);
        }
        System.out.println();
        System.out.println(total);

    }

    private static void test14(int n) {
        System.out.println(Math.pow(2, n));
        n = n - n % 2;
        int i = 0;
        while (n / 2 > 0) {
            i++;
            n = n / 2;
        }
        System.out.println(i);
    }

    /**
     * 矩阵倒置 一个 3*4 的矩阵，倒置以后是 4*3的矩阵
     */
    private static void test13() {
        int[][] arr = new int[3][4];
        int row = arr.length;
        int column = arr[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                arr[i][j] = (i + 1) * (j + 1);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(" " + arr[i][j]);
            }
            System.out.println();
        }

        System.out.println("------------after reserve---------------");
        int[][] reserveArr = new int[column][row];
        int reserveRow = reserveArr.length;
        int reserveColumn = reserveArr[0].length;
        for (int i = 0; i < reserveRow; i++) {
            for (int j = 0; j < reserveColumn; j++) {
                reserveArr[i][j] = arr[j][i];
            }
        }

        for (int i = 0; i < reserveRow; i++) {
            for (int j = 0; j < reserveColumn; j++) {
                System.out.print(" " + reserveArr[i][j]);
            }
            System.out.println();
        }


    }

    private static void test10() {
        boolean[][] arr = new boolean[3][3];
        int row = arr.length;
        int column = arr[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if ((i * j) % 2 == 0) {
                    arr[i][j] = true;
                } else {
                    arr[i][j] = false;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            System.out.print("row:" + i);
            for (int j = 0; j < column; j++) {
                System.out.print(" " + arr[i][j]);
            }
            System.out.println();
        }

    }

    private static void testThreeNumberWhereEqual() throws IOException {
        int a, b, c;
        Scanner scanner = new Scanner(System.in);
        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();
        if (a == b && a == c) {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }

    }


    private static void test6() {
        int f = 0;
        int g = 1;
        for (int i = 0; i <= 15; i++) {
            StdOut.println(f);
            f = f + g;
            g = f - g;

        }
    }

    private static void test7() {
        double t = 9.0;
        while (Math.abs(t - 9.0 / t) > 0.001) {
            t = (9.0 / t + t) / 2.0;
        }
        StdOut.printf("%.5f\n", t);

    }

    private static void test8() {
        System.out.println('b');
        System.out.println('b' + 'c');
        System.out.println((char) ('a' + 4));

    }

    private static void test9(int n) {
        StringBuilder s = new StringBuilder();
        for (int i = n; i > 0; i = i / 2) {
            s.insert(0, (i % 2));
        }
        System.out.println(s.toString());
    }


}
