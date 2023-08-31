package com.hm.base;

/**
 * 可变参数测试，jdk1.5之后的新特性
 * 可变参数只能作为函数的最后一个参数，一个函数最多只能有一个可变参数
 * 可变参数方法的调用
 * 调用可变参数方法，可以给出零到任意多个参数，编译器会将可变参数转化为一个数组。也可以直接传递一个数组
 * <p>
 * 优先匹配固定参数
 * <p>
 * Created by dmw on 2032/8/31
 */
public class VarArgsTest {

    public static void main(String[] args) {
        VarArgsTest test = new VarArgsTest();
        test.test();//可以不传参数
        test.test(1, 2);//优先匹配固定参数
        test.test(1, 2, 3, 4, 5);
    }

    public void test(int... args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }
    //编译不过
//    public void test(int[] args) {
//        for (int i = 0; i < args.length; i++) {
//            System.out.println(args[i]);
//        }
//    }

    public void test(int a, int b) {
        System.out.println("a + b = " + (a + b));
    }
}
