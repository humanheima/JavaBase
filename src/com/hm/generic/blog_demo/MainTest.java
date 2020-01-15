package com.hm.generic.blog_demo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dumingwei on 2017/6/25.
 */
public class MainTest {

    static class Erasure<T> {
        T object;

        public Erasure(T object) {
            this.object = object;
        }

        public void add(T object){

        }

    }

    static class Erasure1<T extends String> {
        //	public class Erasure <T>{
        T object;

        public Erasure1(T object) {
            this.object = object;
        }
    }

    public static void main(String[] args) {


        //加入圆形
        //List<Circle> shapeCircles = new ArrayList<>();
        //shapeCircles.add(new Circle());
        //test2(shapeCircles);
        //加入长方形
        //List<Rectangle> shapeRectangle = new ArrayList<>();
        //shapeRectangle.add(new Rectangle());
        //test2(shapeRectangle);

        /*Collection<Integer> src = new ArrayList<>();
        Collection<String> dest = new ArrayList<>();
        fromArrayToCollection(src, dest);*/

        /*List<Circle> src = new ArrayList<>();

        src.add(new Circle());

        List<Shape> dest = new ArrayList<>();

        fromArrayToCollection2(src, dest);
        fromArrayToCollection3(src, dest);*/

        genericRease0();
        genericRease1();

        genericRease2();
    }


    /**
     * 测试泛型擦除
     */
    public static void genericRease0() {
        Class class1 = new ArrayList<String>().getClass();
        Class class2 = new ArrayList<Integer>().getClass();
        System.out.println(class1);
        System.out.println(class2);
    }

    /**
     * 测试泛型擦除
     */
    public static void genericRease1() {
        Erasure<String> erasure = new Erasure<String>("hello");
        Class eclz = erasure.getClass();
        System.out.println("erasure class is:" + eclz.getName());

        Field[] fs = eclz.getDeclaredFields();
        for (Field f : fs) {
            System.out.println("Field name " + f.getName() + " type:" + f.getType().getName());
        }

        Method[] methods = eclz.getDeclaredMethods();
        for ( Method m:methods ){
            System.out.println(" method:"+m.toString());
        }

    }

    /**
     * 测试泛型擦除
     */
    public static void genericRease2() {
        Erasure1<String> erasure = new Erasure1<String>("hello");
        Class eclz = erasure.getClass();
        System.out.println("erasure class is:" + eclz.getName());

        Field[] fs = eclz.getDeclaredFields();
        for (Field f : fs) {
            System.out.println("Field name " + f.getName() + " type:" + f.getType().getName());
        }

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
     * 设置通配符的上限
     * <p>
     * 只能取数据，不能添加数据
     *
     * @param shapeList
     */
    public static void test2(List<? extends Shape> shapeList) {
        for (Shape shape : shapeList) {
            shape.draw();
        }
        //shapeList.add(new Rectangle());//编译不通过
    }

    /**
     * 设置通配符的下限
     * <p>
     * 只能添加数据，不能取数据
     *
     * @param shapeList
     */
    public static void test3(List<? super Shape> shapeList) {
        shapeList.add(new Rectangle());
    }

    private static <T> void fromArrayToCollection(Collection<T> src, Collection<T> dest) {
        dest.addAll(src);
    }

    /**
     * 注意和
     *
     * @param src
     * @param dest
     * @param <T>
     * @see #test2(List < ? extends Shape >)
     * 的区别
     */
    private static <T> void fromArrayToCollection2(Collection<? extends T> src, Collection<T> dest) {
        dest.addAll(src);

    }

    /**
     * 注意和
     *
     * @param src
     * @param dest
     * @param <T>
     * @see #test2(List < ? extends Shape >)
     * 的区别
     */
    private static <T> void fromArrayToCollection3(Collection<T> src, Collection<? super T> dest) {
        dest.addAll(src);

    }

}
