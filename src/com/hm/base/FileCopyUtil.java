package com.hm.base;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileCopyUtil {

    private static final String APK = "apk";
    private static final String E_APKS = "E:\\apks\\";
    private static final String DEST_PREFIX = "E:\\test\\";

    private static List<File> fileList = new ArrayList<>();
    private static int count = 0;

    public static void main(String[] args) {
        File file = new File(E_APKS);
        listFile(file);
        for (File copyFile : fileList) {
            System.out.println(copyFile.getPath());
            copy(copyFile);
        }
    }

    private static void listFile(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.getName().endsWith(APK)) {
                    fileList.add(file1);
                }
                if (file1.isDirectory()) {
                    listFile(file1);
                }
            }
        }
    }

    private static void copy(File source) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(source));
            String destPath = DEST_PREFIX + source.getName();
            System.out.println(destPath);
            bos = new BufferedOutputStream(new FileOutputStream(destPath));
            int hasRead;
            while ((hasRead = bis.read()) != -1) {
                bos.write(hasRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            count++;
            System.out.println("have copy：" + count + " apk");
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
