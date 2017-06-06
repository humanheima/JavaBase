package com.hm.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by dumingwei on 2017/3/23.
 */
public class RandFileChannelTest {


    public static void main(String args[]) {
        //map();
        //cp();
        //write();
        test();
    }

    private static void test() {
        File f = new File("D:\\a.txt");
        try {
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            FileChannel channel = raf.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, f.length());
            channel.position(f.length());
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void map() {
        RandomAccessFile rf;
        FileChannel fc;
        int size = 0x80000;
        MappedByteBuffer mb;
        try {
            rf = new RandomAccessFile("D://RandFileChannelTest.txt", "rwd");
            fc = rf.getChannel();
            mb = fc.map(FileChannel.MapMode.READ_WRITE, 0, size);
            for (int i = 0; i < size; i++) {
                mb.put((byte) 'x');
            }
            for (int i = size / 2; i < size / 2 + 6; i++) {
                System.out.println((char) mb.get(i));
            }
            rf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cp() {
        RandomAccessFile rf;
        RandomAccessFile rfCopy;
        try {
            rf = new RandomAccessFile("D://RandFileChannelTest.txt", "rwd");
            rfCopy = new RandomAccessFile("D://RandAccessFileCopy.txt", "rwd");
            rf.seek(0);
            byte[] b = new byte[(int) rf.length()];
            rf.readFully(b);
            rfCopy.write(b);
            rf.close();
            rfCopy.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void write() {
        RandomAccessFile rf;
        try {
            rf = new RandomAccessFile("D://RandFileChannelTest.txt", "rwd");
            rf.writeBytes("hello world!!!\r\n");
            rf.writeBytes("hehe\r\n");
            rf.close();
            rf = new RandomAccessFile("D://RandFileChannelTest.txt", "rwd");
            String content = rf.readLine();
            System.out.println(content);
            content = rf.readLine();
            System.out.println(content);
            rf.close();
           /* rf = new RandomAccessFile("D://RandFileChannelTest.txt", "rwd");
            rf.writeInt(3);
            rf.writeInt(4);
            rf.writeInt(5);
            rf.close();
            rf = new RandomAccessFile("D://RandFileChannelTest.txt", "rwd");
            System.out.println(rf.readInt());
            System.out.println(rf.readInt());
            System.out.println(rf.readInt());*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}