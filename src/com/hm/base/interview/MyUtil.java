package com.hm.base.interview;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by dumingwei on 2017/10/2.
 */
public class MyUtil {

    private MyUtil() {
        throw new AssertionError();
    }

    public static void fileCopy(String source, String target) throws IOException {

        InputStream in = new FileInputStream(source);
        OutputStream out = new FileOutputStream(target);
        byte[] buffer = new byte[4096];
        int bytesToRead;
        while ((bytesToRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesToRead);
        }
    }

    public static void fileCopyNIO(String source, String target) throws IOException {
        FileInputStream in = new FileInputStream(source);
        FileOutputStream out = new FileOutputStream(target);
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        while (inChannel.read(buffer) != -1) {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }
    }

    public static int countWordInFile(String fileName, String word) {
        int counter = 0;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                int index = -1;
                while (line.length() >= word.length() && (index = line.indexOf(word)) >= 0) {
                    counter++;
                    line = line.substring(index + word.length());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }


    public static void walkDirectory(File f, int level) {
        if (f.isDirectory()) {
            for (File file : f.listFiles()) {
                walkDirectory(file, level + 1);
            }
        } else {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("\t");
            }
            System.out.println(f.getName());
        }
    }

    public static void nioWalkDirectory(String fileName) throws IOException {
        Path initPath = Paths.get(fileName);
        Files.walkFileTree(initPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                System.out.println(file.getFileName().toString());
                return FileVisitResult.CONTINUE;
            }
        });
    }


}
