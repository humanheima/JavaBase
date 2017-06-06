package com.hm.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Consumer;

/**
 * Created by dumingwei on 2017/6/5.
 */
public class Util {

    public static void main(String[] args) {
        //listFiles("D:\\test");
        // new Util().countFiles("D:\\test");
        File srcFile = new File("D:\\test");
        File destFile = new File("D:\\test_copy");
       /* try {
            new Util().copyFolder(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        new Util().copyFile(srcFile, destFile);
    }

    /**
     * 列出一个文件下的所有文件
     *
     * @param path 文件路径
     */
    private static void listFiles(String path) {
        try {
            Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println(dir);
                    return super.preVisitDirectory(dir, attrs);
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println(file);
                    return super.visitFile(file, attrs);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 统计一个路径下文件夹的数量和文件的数量
     *
     * @param path 文件路径
     */
    private void countFiles(String path) {
        MyFileVisitor visitor = new MyFileVisitor();
        try {
            Files.walkFileTree(Paths.get(path), visitor);
            System.out.println("目录数：" + visitor.getDirCount());
            System.out.println("文件数：" + visitor.getFileCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 拷贝文件或者文件夹
     *
     * @param src  原始路径
     * @param dest 目标路径
     */
    private void copyFile(File src, File dest) {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }
            String files[] = src.list();
            for (String file : files) {
                System.out.println(file);
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                copyFile(srcFile, destFile);
            }
        } else {
            try {
                FileChannel inChannel = new FileInputStream(src).getChannel();
                FileChannel outChannel = new FileOutputStream(dest).getChannel();
                ByteBuffer buf = ByteBuffer.allocate(32);
                while (inChannel.read(buf) != -1) {
                    buf.flip();
                    outChannel.write(buf);
                    buf.clear();
                }
                inChannel.close();
                outChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 复制一个目录及其子目录、文件到另外一个目录
     *
     * @param src
     * @param dest
     * @throws IOException
     */
    private void copyFolder(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }
            String files[] = src.list();
            for (String file : files) {
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                // 递归复制
                copyFolder(srcFile, destFile);
            }
        } else {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
        }
    }


    private class MyFileVisitor extends SimpleFileVisitor<Path> {

        private int dirCount;
        private int fileCount;

        public int getDirCount() {
            return dirCount;
        }

        public int getFileCount() {
            return fileCount;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            dirCount++;
            System.out.println(dir);
            return super.preVisitDirectory(dir, attrs);
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            fileCount++;
            System.out.println(file);
            return super.visitFile(file, attrs);
        }
    }

}
