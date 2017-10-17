package com.hm.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by dumingwei on 2017/10/4.
 */
public class FileReaderWriterTest {

    public static void main(String[] args) {
        //testWrite();
        testRead();
    }

    private static void testRead() {
        FileReader reader = null;
        try {
            reader = new FileReader("FileWriterDemo.txt");
            char[] buffer = new char[1024];
            StringBuilder builder = new StringBuilder();
            int count;
            while ((count = reader.read(buffer)) != -1) {
                builder.append(buffer, 0, count);
            }
            System.out.println(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void testWrite() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("FileWriterDemo.txt", true);
            //要被写入的字符串
            String str = "helloworld";
            fw.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    //关闭数据流
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
