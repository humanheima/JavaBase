package com.hm.base;

import java.io.*;

/**
 * Crete by dumingwei on 2020-02-21
 * Desc: 序列化和反序列化的原理
 * <p>
 * 参考链接：
 * https://blog.csdn.net/xlgen157387/article/details/79840134
 */
public class SerialDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //序列化
        FileOutputStream fos = new FileOutputStream("object.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        User user1 = new SerialDemo.User("xuliugen", "123456", "male");
        oos.writeObject(user1);
        oos.flush();
        oos.close();
        //反序列化
        FileInputStream fis = new FileInputStream("object.out");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user2 = (User) ois.readObject();
        System.out.println(user2.userName + " " +
                user2.password + " " + user2.sex);
        //反序列化的输出结果为：xuliugen 123456 male
    }

    static class User implements Serializable {

        private String userName;
        private String password;
        private String sex;

        public User(String userName, String password, String sex) {
            this.userName = userName;
            this.password = password;
            this.sex = sex;
        }
    }
}



