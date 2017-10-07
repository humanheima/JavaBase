package com.hm.base.interview;

/**
 * Created by dumingwei on 2017/9/30.
 * 关于字符串的比较  参考
 * http://wulc.me/2016/05/18/Java%E4%B8%ADString%E7%9A%84%E6%AF%94%E8%BE%83%E6%96%B9%E5%BC%8F%EF%BC%88==%20%E5%92%8C%20equals%EF%BC%89/
 */
public class StringEqualTest {

    /**
     * 字符串的+操作其本质是创建了StringBuilder对象进行append操作，然后将拼接后的StringBuilder对象用toString方法处理成String对象
     *
     * @param args
     */
    public static void main(String[] args) {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        /**
         *s5由”Program”、”ming”两个常量拼接而成，
         * 因为”Program”、”ming”为两个为常量，不可变，
         * 在编译期间编译器会把s5=”Program”+”ming”优化为s5=”Programming”。
         */
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);//false
        System.out.println(s1 == s5);//true
        /**
         * s6由s3和s4拼接而成，由于两个变量的相加所以编译器无法优化，
         * 在运行时，会有新的String地址空间的分配，而不是指向缓冲池中的“Programming”。
         * 所以结果false。
         */
        System.out.println(s1 == s6);//false
        System.out.println(s1 == s6.intern());//true
        System.out.println(s2 == s2.intern());//false

        final String s7 = "a";
        final String s8 = "b";
        String s9 = s7 + s8;
        String s10 = "ab";
        /**
         * s9虽让也是由两个变量拼接而成，但是这两个变量已经声明为final不可变的了
         * 所以类似于s5，在编译期间编译器也进行了优化确定了s9的值。
         */
        System.out.println("s9==s10? " + (s10 == s9));//
    }
}
