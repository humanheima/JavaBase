package com.hm.lambda.quickstart;

/**
 * Created by dumingwei on 2021/6/29.
 * <p>
 * Desc:
 */
public class Test05 {

    public static void main(String[] args) {

        Test05 test05 = new Test05();

        test05.test(new FunctionInterface() {
            @Override
            public void doAction() {
                System.out.println("我是接口实现");
            }
        });
        test05.test(() -> {
            System.out.println("我是lambda表达式");

        });

        test05.test(() -> System.out.println("我是lambda表达式"));

        test05.test(test05::doAction);
    }

    public void doAction() {
        System.out.println("我是一个函数");
    }

    private void test(FunctionInterface function) {
        try {
            function.doAction();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }
}
