package com.hm.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by dumingwei on 2017/6/2.
 */
public class PathTest {

    public static void main(String[] args) {
        Path path = Paths.get("D:\\IdeaProjects\\JavaBase\\nio\\PathTest.java");
        System.out.println("path里包含的路径数量：" + path.getNameCount());
        System.out.println("path的根路径：" + path.getRoot());
        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);
        System.out.println("absolutePath的根路径：" + absolutePath.getRoot());
        System.out.println("absolutePath里包含的路径数量：" + absolutePath.getNameCount());
        System.out.println(absolutePath.getName(2));
        Path path2 = Paths.get("D:", "publish", "codes");
        System.out.println(path2);
    }
}
