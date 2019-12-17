package com.hm.base.serializable_test;

import java.io.*;

/**
 * Crete by dumingwei on 2019-11-29
 * Desc: 测试Serializable
 * 序列化并不保存静态变量。
 */
public class SerializableTest1 implements Serializable {

    private static final long serialVersionUID = 1L;

    public static int staticVar = 5;

    public static void main(String[] args) {
        try {
            //初始时staticVar为5
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("result.obj"));
            out.writeObject(new SerializableTest1());
            out.close();

            //序列化后修改为10
            SerializableTest1.staticVar = 10;

            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                    "result.obj"));
            SerializableTest1 t = (SerializableTest1) oin.readObject();
            oin.close();

            //再读取，通过t.staticVar打印新的值,输出是10
            System.out.println(t.staticVar);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
