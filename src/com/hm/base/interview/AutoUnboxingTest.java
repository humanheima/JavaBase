package com.hm.base.interview;

/**
 * Created by dumingwei on 2017/9/30.
 */
public class AutoUnboxingTest {

    public static void main(String[] args) {
        Integer a = new Integer(3);
        Integer b = 3;// 将3自动装箱成Integer类型
        int c = 3;
        System.out.println(a == b);// false 两个引用没有引用同一对象
        System.out.println(a == c);// true a自动拆箱成int类型再和c比较
        System.out.println("----------------------------------");
        test();
    }

    /**
     * {{@link Integer#valueOf(int)}
     * 如果整型字面量的值在-128到127之间，那么不会new新的Integer对象，而是直接引用常量池中的Integer对象，
     * 所以上面的面试题中f1==f2的结果是true，而f3==f4的结果是false。
     */
    private static void test() {
        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
        //System.out.println(f1 == f2);//输出true
        //System.out.println(f3 == f4);//输出false
        String s1 = new StringBuilder("go").append("od").toString();
        System.out.println(s1.intern() == s1);
        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(s2.intern() == s2);
    }
}
