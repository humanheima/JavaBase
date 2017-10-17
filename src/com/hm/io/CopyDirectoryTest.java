package com.hm.io;

import java.io.*;

/**
 * Created by dumingwei on 2017/10/5.
 * 递归拷贝一个文件夹下的所有文件
 */
public class CopyDirectoryTest {

    public static void main(String[] args) {
        copyDirectory(new File("D:\\test"), new File("D:\\"));
    }

    private static void copyDirectory(File source, File directory) {
        if (source == null || directory == null)
            throw new NullPointerException("source and copyTo can't be null!");
        if (source.isDirectory()) {
            File[] files = source.listFiles();
            if (files != null) {
                File childDirectory = new File(directory.getPath() + "\\copy" + source.getName());
                if (childDirectory.mkdir()) {
                    for (File file : files) {
                        copyDirectory(file, childDirectory);
                    }
                }
            }
        } else {
            copy(source, new File(directory.getPath() + "\\copy" + source.getName()));
        }
    }

    private static void copy(File source, File destination) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(source));
            bos = new BufferedOutputStream(new FileOutputStream(destination));
            int hasRead;
            while ((hasRead = bis.read()) != -1) {
                bos.write(hasRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
