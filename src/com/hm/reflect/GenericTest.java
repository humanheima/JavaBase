package com.hm.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by dumingwei on 2017/6/25.
 */
public class GenericTest {

    private Map<String, Integer> score;

    public static void main(String[] args) throws NoSuchFieldException {
        Class<GenericTest> clazz = GenericTest.class;
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
        }
    }
}
