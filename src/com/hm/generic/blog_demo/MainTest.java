package com.hm.generic.blog_demo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dumingwei on 2017/6/25.
 */

//Java的泛型是不变型的
class MyList<T extends Base> {

    T t;

    T get() {
        return t;
    }

}

public class MainTest {

    static class Erasure<T> {
        T object;

        public Erasure(T object) {
            this.object = object;
        }

        public void add(T object) {

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

        new Circle();

        genericRease0();
        genericRease1();

        genericRease2();

        Shape shape = new Shape() {

            @Override
            public void draw() {
                System.out.println("draw");
            }
        };
        shape.draw();

        test3(new ArrayList<Object>());

        List<? super Shape> list = new ArrayList<Shape>();

        list.contains(3);

        MyList<Sub> lsub = new MyList<>();
        MyList<Sub2> lsub2 = new MyList<>();
        MyList<? extends Base> lbase = lsub;


        //编译错误
        //List<Fruit> fruits = new ArrayList<Orange>();

        List<? extends Fruit> fruits = new ArrayList<Orange>();

        //编译错误:不能添加任何类型的对象
        //fruits.add(new Orange());
        //fruits.add(new Fruit());
        //fruits.add(new Object());
        //fruits.add(null);//可以这么做，但是没有意义
        //我们知道，返回值肯定是Fruit
        Fruit f = fruits.get(0);


        List<Object> objs = new ArrayList<>();
        objs.add(new Object());
        List<? super Fruit> canContainFruits = objs;
        //没有问题，可以写入Fruit类及其子类
        canContainFruits.add(new Orange());
        canContainFruits.add(new Fruit());
        //无法安全地读取,canContainFruits完全可能包含Fruit基类的对象，比如这里的Object
        //Fruit f = canContainFruits.get(0);
        //总是可以读取为Object，然而这并没有太多意义
        Object o = canContainFruits.get(0);


    }


    public <T extends Number & CharSequence> void sum(T num) {
        //do something
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
        for (Method m : methods) {
            System.out.println(" method:" + m.toString());
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

    /*public static <T> void test2_t(List<T extends Shape> shapeList) {
        for (Shape shape : shapeList) {
            shape.draw();
        }
        //shapeList.add(new Rectangle());//编译不通过
    }*/

    /**
     * 设置通配符的下限
     * <p>
     * 只能添加数据，不能取数据
     *
     * @param shapeList
     */
    public static void test3(List<? super Shape> shapeList) {
        shapeList.add(new Rectangle());
        shapeList.add(new Circle());
        Object obj = shapeList.get(0);
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


class Fruit {

}

class Orange extends Fruit {

}

class Base<T extends Number> {

    <T extends Number & Appendable> void test(T t) {

    }
}

class Sub extends Base {
}

class Sub2 extends Base {
}


