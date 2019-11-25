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
        //test();
        test1();
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

    private static void test1() {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 2L;

        System.out.println(c == d);//true
        System.out.println(e == f);//false
        System.out.println(c == (a + b));//true
        System.out.println(c.equals(a + b));//true a+b 先拆箱计算数值后再装箱成Integer类型，equals方法比较的是数值
        System.out.println(g == (a + b));//true 比较的是拆箱后的数值
        System.out.println(g.equals(a + b));//false 没有疑问 a+b 先拆箱计算数值后再装箱成Integer类型，不是Long类型，Long类型的equals方法
        System.out.println(g.equals(a + h));//a+h 先拆箱计算处数值然后在装箱成Long类型
    }
}
