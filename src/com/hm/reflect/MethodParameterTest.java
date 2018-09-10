package com.hm.reflect;

import com.hm.anno.Header;
import com.hm.anno.Path;
import com.hm.anno.Query;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

class Test {
    public void replace(String str, List<String> list) {
    }

    public void testAnnotation(@Query(value = "id") List<String> id, @Query("page") int page, @Path("user") String user) {
    }
}

public class MethodParameterTest {
    public static void main(String[] args) throws Exception {
        // 获取String的类
        Class<Test> clazz = Test.class;
        // 获取String类的带两个参数的replace()方法
        Method replace = clazz.getMethod("replace"
                , String.class, List.class);
        // 获取指定方法的参数个数
        System.out.println("replace方法参数个数：" + replace.getParameterCount());
        // 获取replace的所有参数信息
        Parameter[] parameters = replace.getParameters();
        int index = 1;
        // 遍历所有参数
        for (Parameter p : parameters) {
            if (p.isNamePresent()) {
                System.out.println("---第" + index++ + "个参数信息---");
                System.out.println("参数名：" + p.getName());
                System.out.println("形参类型：" + p.getType());
                System.out.println("泛型类型：" + p.getParameterizedType());
            }
        }

        Method paramsAnnotation = clazz.getMethod("testAnnotation"
                , List.class, int.class, String.class);

        Annotation[][] annotations = paramsAnnotation.getParameterAnnotations();
        Type[] parameterTypes = paramsAnnotation.getGenericParameterTypes();
        for (Type parameterType : parameterTypes) {
            if (parameterType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) parameterType;

                // I'm not exactly sure why getRawType() returns Type instead of Class. Neal isn't either but
                // suspects some pathological case related to nested classes exists.
                Type rawType = parameterizedType.getRawType();
                System.out.println(rawType);
                System.out.println(rawType.getTypeName());
                Type[] rawTypes = parameterizedType.getActualTypeArguments();
                for (Type type : rawTypes) {
                    System.out.println(type.getTypeName());
                }

            }
        }

        int length = annotations.length;
        System.out.println(length);
        int i = 0;
       /* for (Annotation[] annotation : annotations) {
            System.out.println(annotation.length);
            //Class parameterType = parameterTypes[i++];
            for (Annotation annotation1 : annotation) {
                if (annotation1 instanceof Query) {
                    Query query = (Query) annotation1;
                    //System.out.println("param: " + parameterType.getName());
                    System.out.println("value: " + query.value());

                } else if (annotation1 instanceof Header) {
                    Header header = (Header) annotation1;
                    //System.out.println("param: " + parameterType.getName());
                    System.out.println("value: " + header.value());
                } else if (annotation1 instanceof Path) {
                    Path path = (Path) annotation1;
                    //System.out.println("param: " + parameterType.getName());
                    System.out.println("value: " + path.value());
                }
            }

        }

        Class<?>[] cClasses = paramsAnnotation.getParameterTypes();
        for (Class<?> cClass : cClasses) {
            System.out.println(cClass);
        }*/


    }
}
