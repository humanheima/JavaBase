package com.hm.reflect;

import com.hm.pattern.bridge.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dumingwei on 2017/6/25.
 */
public class GenericTest {

    private Map<String, Integer> score;

    public static void main(String[] args) throws NoSuchFieldException {
        /*Class<GenericTest> clazz = GenericTest.class;
        Field f = clazz.getDeclaredField("score");
        Class<?> a = f.getType();
        System.out.println("score的类型是:" + a);
        Type gType = f.getGenericType();
        if (gType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) gType;
            Type rType = pType.getRawType();
            System.out.println("原始类型是：" + rType);
            Type[] aType = pType.getActualTypeArguments();
            System.out.println("泛型信息是：");
            for (int i = 0; i < aType.length; i++) {
                System.out.println("第" + i + "个泛型类型是：" + aType[i]);
            }
        } else {
            System.out.println("获取泛型类型出错!");
        }*/

        List<String> stringList = new ArrayList<>();
        List<String> stringList1 = new ArrayList<>();
        stringList.add("1");
        //test(stringList);//编译不通过
    }


    public static void test0(List<Object> objectList) {
        for (Object o : objectList) {
            System.out.println(o);
        }
    }

    public static void test1(List<?> objectList) {
        for (Object o : objectList) {
            System.out.println(o);
        }

        //无法添加元素
        //objectList.add(new Object());
    }

    /**
     * 只能取数据
     *
     * @param objectList
     */
    public static void test(List<? extends Shape> objectList) {
        for (Object o : objectList) {
            System.out.println(o);
        }
        Shape shape = objectList.get(0);
    }
}
