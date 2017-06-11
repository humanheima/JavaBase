package com.hm.inner_class;

/**
 * Created by dumingwei on 2017/6/7.
 */
public class CreateInnerInstance {

    public static void main(String[] args) {
        Out.In in;
        Out out = new Out();
        in = out.new In("测试信息");
    }
}

class Out {

    class In {

        public In(String msg) {
            System.out.println(msg);
        }
    }
}
