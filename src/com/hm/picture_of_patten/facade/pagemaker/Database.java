package com.hm.picture_of_patten.facade.pagemaker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by dumingwei on 2022/5/8
 * <p>
 * Desc:用来从邮件地址中获取用户名字的数据库类
 */
public class Database {

    private Database() {    // 防止外部new出Database的实例，所以声明为private方法
    }

    public static Properties getProperties(String dbname) { // 根据数据库名获取Properties
        String filename = dbname + ".txt";
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(filename));
        } catch (IOException e) {
            System.out.println("Warning: " + filename + " is not found.");
        }
        return prop;
    }
}
