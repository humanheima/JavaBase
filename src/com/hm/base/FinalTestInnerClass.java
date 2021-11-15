package com.hm.base;

/**
 * Created by dumingwei on 2017/7/10.
 * final 关键字
 */
public class FinalTestInnerClass {

    public FinalTestInnerClass() {
    }


    private Address address;

    public static void main(String[] args) throws InterruptedException {
        FinalTestInnerClass finalTestInnerClass = new FinalTestInnerClass();
        Address address = new Address();
        address.setAdd("成华大道");
        finalTestInnerClass.address = address;

        finalTestInnerClass.test1(address);

        //test1();
    }

    private void test1(Address address) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.println(address.getAdd());
                System.out.println("内部类里面修改传入的对象地址");
                address.setAdd("二仙桥");
            }
        }.start();

        Thread.sleep(3000);
        System.out.println("睡眠过后，修改过后的对象的地址：" + address.getAdd());
    }
}



