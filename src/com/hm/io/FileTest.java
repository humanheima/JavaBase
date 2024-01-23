package com.hm.io;

import java.io.*;

/**
 * Created by dumingwei on 2017/5/31.
 */
public class FileTest {

    private static FileWriter fw = null;

    public static void main(String args[]) throws IOException {

        fw = new FileWriter("so_files.txt", true);

        File file = new File("/Users/dumingwei/OLD_AS_Project/dreamder-android-two/app/build/outputs/apk/common/debug/app-common-debug/lib");
        listFile(file);
        //listRoot();
        /*FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };*/
        //listFileFilter(filter);

        //testRenameTo();
//        try {
//            testCreateFileInMac();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Person person = new Person();
//        System.out.println(person.isB());
    }

    private static void testRenameTo() {
        /*File file1=new File("/Users/dumingwei/IdeaProjects/JavaBase/file1.java");
        file1.mkdir();
        File file2=new File("/Users/dumingwei/IdeaProjects/JavaBase/file2.java");
        file2.mkdir();*/

        File file1 = new File("/Users/dumingwei/IdeaProjects/JavaBase/file1.java");
        File file2 = new File("/Users/dumingwei/IdeaProjects/JavaBase/file2.java");
        if (file1.exists()) {
            file1.delete();
        }
        //直接将file2重命名为file1
        file2.renameTo(file1);
    }

    private static void testCreateFileInMac() throws IOException {
        /*File file1=new File("/Users/dumingwei/IdeaProjects/JavaBase/file1.java");
        file1.mkdir();
        File file2=new File("/Users/dumingwei/IdeaProjects/JavaBase/file2.java");
        file2.mkdir();*/

        File file1 = new File("/Users/xmly/IdeaProjects/JavaBase/src/com/hm/io/", "FileTest.md");
        file1.createNewFile();

    }

    private static void listFile(File file) throws IOException {
        File[] files = file.listFiles();

        for (File file1 : files) {
            if (file1.isDirectory()) {
                File[] soFiles = file1.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        if (name.endsWith(".so")) {
                            return true;
                        }
                        return false;

                    }
                });

                System.out.println("soFiles.length = " + soFiles.length);
                for (File soFile : soFiles) {
                    String replace = soFile.getAbsolutePath().replace("/Users/dumingwei/OLD_AS_Project/dreamder-android-two/app/build/outputs/apk/common/debug/app-common-debug", "");
                    String finalPath = "\t\t\t'" + replace + "',";

                    writeString(finalPath);
                }

            }
        }

        fw.flush();
        fw.close();

        System.out.println("遍历结束");

    }


    /**
     * 向文件中写入一个字符串
     */
    private static void writeString(String text) {
        try {
            fw.write(text);
            fw.write("\n");

        } catch (IOException e) {
            e.printStackTrace();
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
