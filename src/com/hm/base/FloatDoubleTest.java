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
    }

    private static void test1() {
        String s = new String("xyz");//这个应该是创建了两个对象
    }
}
