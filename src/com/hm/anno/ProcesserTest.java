package com.hm.anno;

import java.lang.reflect.Method;

/**
 * Created by dumingwei on 2017/8/6.
 */
public class ProcesserTest {

    public static void process(String clazz) throws ClassNotFoundException {
        int passed = 0;
        int failed = 0;
        for (Method method : Class.forName(clazz).getMethods()) {
            //如果该方法使用了@Testable修饰
            if (method.isAnnotationPresent(Testable.class)) {
                try {
                    //调用m方法
                    method.invoke(null);
                    passed++;
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("方法：" + method + "运行失败，异常：" + e.getCause());
                    failed++;
                }
            }
        }
        System.out.println("共运行了：" + (passed + failed) + " 个方法" + "passed:" + passed
                + ",failed:" + failed);
    }

    public static void main(String [] args) throws ClassNotFoundException {
        process("com.hm.anno.MyTest");
    }

}
