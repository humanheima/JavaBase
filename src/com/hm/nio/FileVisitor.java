package com.hm.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by dumingwei on 2017/6/4.
 */
public class FileVisitor {

    public static void main(String[] args) {

        try {
            Files.walkFileTree(Paths.get("D:\\test"), new SimpleFileVisitor<Path>() {

                //访问文件时触发该方法
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("正在访问" + file + "文件");
                    if (file.endsWith("target.java")) {
                        System.out.println("已经找到目标文件");
                        return FileVisitResult.TERMINATE;
                    }
                    return FileVisitResult.CONTINUE;
                }

                //开始访问目录的时候触发该方法
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("正在访问路径" + dir);
                    return FileVisitResult.CONTINUE;
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
