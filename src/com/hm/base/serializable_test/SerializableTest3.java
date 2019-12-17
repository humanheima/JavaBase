package com.hm.base.serializable_test;

import java.io.*;

/**
 * Crete by dumingwei on 2019-11-29
 * Desc:序列化存储规则:Java 序列化机制为了节省磁盘空间，具有特定的存储规则，
 * 当写入文件的为同一对象时，并不会再将对象的内容进行存储，而只是再次存储一份引用，
 * 本例中增加的5字节的存储空间就是新增引用和一些控制信息的空间。
 * 反序列化时，恢复引用关系，使得中的 t1 和 t2 指向唯一的对象，二者相等，输出 true。该存储规则极大的节省了存储空间。
 */
public class SerializableTest3 implements Serializable {

    private String name;

    private int i = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        testOne();
        testTwo();
    }

    private static void testOne() throws IOException, ClassNotFoundException {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("result.obj"));
        SerializableTest3 test = new SerializableTest3();
        //试图将对象两次写入文件
        out.writeObject(test);
        out.flush();
        System.out.println(new File("result.obj").length());
        out.writeObject(test);
        out.close();
        System.out.println(new File("result.obj").length());

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                "result.obj"));
        //从文件依次读出两个文件
        SerializableTest3 t1 = (SerializableTest3) oin.readObject();
        SerializableTest3 t2 = (SerializableTest3) oin.readObject();
        oin.close();

        //判断两个引用是否指向同一个对象
        System.out.println(t1 == t2);
    }

    /**
     * 这个方法的目的是希望将 test 对象两次保存到 result.obj 文件中，写入一次以后修改对象属性值再次保存第二次，
     * 然后从 result.obj 中再依次读出两个对象，输出这两个对象的 i 属性值。这两个对象的i属性值我们期望是不一样的。
     * 案例代码的目的原本是希望一次性传输对象修改前后的状态。
     * <p>
     * 结果两个输出的都是 1， 原因就是第一次写入对象以后，第二次再试图写的时候，虚拟机根据引用关系知道已经有一个相同对象已经写入文件，
     * 因此只保存第二次写的引用，所以读取时，都是第一次保存的对象。读者在使用一个文件多次 writeObject 需要特别注意这个问题。
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static void testTwo() throws IOException, ClassNotFoundException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("result.obj"));
        SerializableTest3 test = new SerializableTest3();
        test.i = 1;
        out.writeObject(test);
        out.flush();
        test.i = 2;
        out.writeObject(test);
        out.close();
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                "result.obj"));
        SerializableTest3 t1 = (SerializableTest3) oin.readObject();
        SerializableTest3 t2 = (SerializableTest3) oin.readObject();
        System.out.println(t1.i);
        System.out.println(t2.i);
    }


}
