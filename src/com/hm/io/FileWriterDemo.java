package com.hm.io;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {

    public static void main(String[] args) {
        writeString();
    }

    /**
     * 向文件中写入一个字符串
     */
    private static void writeString() {
        FileWriter fw = null;
        try {
            //在工程的根目录下创建一个FileWriter对象，如果该目录下已有同名文件，旧的文件将被覆盖
            fw = new FileWriter("FileWriterDemo.txt", true);
            //要被写入的字符串
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                //bufw.write(i);
                builder.append(i);
                builder.append(',');
            }
            //String str = "\nhello world";
            fw.write(builder.toString());

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
