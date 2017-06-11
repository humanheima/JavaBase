package com.hm.inner_class;

/**
 * Created by dumingwei on 2017/6/7.
 */
public class DiscernVeriable {

    private String prop = "外部类变量";

    public static void main(String[] args) {
        new DiscernVeriable().test();
    }

    public void test() {
        InnerClass inner = new InnerClass();
        inner.info();
    }

    private class InnerClass {

        private String prop = "内部类变量";

        public void info() {
            String prop = "内部类方法内的变量";
            System.out.println(prop);
            System.out.println(this.prop);
            System.out.println(DiscernVeriable.this.prop);
        }
    }
}
