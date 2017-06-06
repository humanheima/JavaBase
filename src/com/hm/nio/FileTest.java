package com.hm.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by dumingwei on 2017/6/4.
 */
public class FileTest {

    public static final String path = "D:\\IdeaProjects\\JavaBase\\src\\com\\hm\\nio\\FileTest.java";

    public static void main(String[] args) {
        try {
            Files.copy(Paths.get(path), new FileOutputStream("D:\\FileTest.txt"));
            System.out.println("FileTest.java是否为隐藏文件:" + Files.isHidden(Paths.get(path)));
            //一次性读取"FileTest.java"所有的行
            List<String> lines = Files.readAllLines(Paths.get(path), Charset.forName("UTF-8"));
            System.out.println(lines);
            //判断指定文件的大小
            System.out.println("FileTest.java的大小：" + Files.size(Paths.get(path)));
            List<String> poem = new ArrayList<>();
            poem.add("水晶潭底银鱼跃");
            poem.add("清徐风中碧竿横");
            Files.write(Paths.get("D:\\poem.txt"), poem, Charset.forName("UTF-8"));
            Files.list(Paths.get("D:\\test")).forEach(new Consumer<Path>() {
                @Override
                public void accept(Path path) {
                    System.out.println(path);
                }
            });
            Files.lines(Paths.get(path), Charset.forName("UTF-8"))
                    .forEach(new Consumer<String>() {
                        @Override
                        public void accept(String s) {
                            System.out.println(s);
                        }
                    });
            FileStore cStore = Files.getFileStore(Paths.get("C:\\"));
            System.out.println("C:总空间" + cStore.getTotalSpace());
            System.out.println("C:可用空间" + cStore.getUsableSpace());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
