package com.hm.base;

public class FloatDoubleTest {

    public static void main(String[] args) {
        test0();
    }


    private static void test0() {
        Float t = new Float(0.9F);
        Float s = new Float(0.9F);
        Double u = new Double(0.9);

        System.out.println(t == s);
        System.out.println(t.equals(s));//这个才是正确的
        //System.out.println(t==u);//无法编译
        System.out.println(t.equals(u));
        double str = 2.0;
        System.out.println(String.format("%.2fkm", 0.2));
        System.out.println(String.format("%.2fkm", 1.21111));
        System.out.println(String.format("%.2fkm", 1.20));
        System.out.println(String.format("%.2fkm", 0.2));
    }

}
