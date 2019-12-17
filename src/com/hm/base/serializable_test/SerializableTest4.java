package com.hm.base.serializable_test;

import com.alibaba.fastjson.util.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Crete by dumingwei on 2019-11-29
 * Desc: 测试Serializable
 * ArrayList的序列化
 */
public class SerializableTest4 implements Serializable {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<String> stringList = new ArrayList<>();
        stringList.add("hello");
        stringList.add("world");
        stringList.add("hollis");
        stringList.add("chuang");
        System.out.println("init StringList" + stringList);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("stringlist"));
        objectOutputStream.writeObject(stringList);

        IOUtils.close(objectOutputStream);
        File file = new File("stringlist");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        List<String> newStringList = (List<String>) objectInputStream.readObject();
        IOUtils.close(objectInputStream);
        if (file.exists()) {
            file.delete();
        }
        System.out.println("new StringList" + newStringList);
    }

}
