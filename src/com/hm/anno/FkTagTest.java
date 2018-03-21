package com.hm.anno;

/**
 * Created by dumingwei on 2018/3/19 0019.
 */
@FkTag(age = 5)
@FkTag(name = "疯狂java", age = 9)
public class FkTagTest {

    public static void main(String[] args) {
        Class<FkTagTest> clazz = FkTagTest.class;
        //使用Java 8 新增的getDeclaredAnnotationsByType()方法获取
        //FkTagTest类的多个FkTag注解
        FkTag[] tags = clazz.getDeclaredAnnotationsByType(FkTag.class);
        for (FkTag tag : tags) {
            System.out.println(tag.name() + "-->" + tag.age());
        }
        FkTags container = clazz.getDeclaredAnnotation(FkTags.class);
        System.out.println(container);
    }
}
