package com.hm.io;

import java.io.*;

/**
 * Created by dumingwei on 2017/5/31.
 */
public class FileTest {

    public static void main(String args[]) {


        File file = new File("E:\\apks");
        listFile(file);
        //listRoot();
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };
        //listFileFilter(filter);
    }

    private static void listFile(File file) {
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file1.getAbsoluteFile());
            if (file1.isDirectory())
                listFile(file1);
        }
    }

    /**
     * 过滤文件名
     */
    private static void listFileFilter(FilenameFilter filter) {
        File file = new File("D:\\test\\collection");
        File[] files = file.listFiles(filter);
        for (File file1 : files) {
            System.out.println(file1.getAbsoluteFile());
        }
    }

    /**
     * 列出所有磁盘根路径
     */
    private static void listRoot() {
        File[] roots = File.listRoots();
        for (File file1 : roots) {
            System.out.println(file1);
        }
    }

    /**
     * 传统操作文件的try catch
     *
     * @throws FileNotFoundException
     */
    public void readFile() throws FileNotFoundException {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("d:/input.txt");
            br = new BufferedReader(fr);
            String s = "";
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * try with resource 方法
     *
     * @throws FileNotFoundException
     */
    public void readFileNew() {

        try (
                FileReader fr = new FileReader("");
                BufferedReader br = new BufferedReader(fr);
        ) {
            String s = "";
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
