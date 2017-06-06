package com.hm.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by dumingwei on 2017/6/2.
 */
public class FileLockTest {

    public static void main(String[] args) {
        try {
            FileChannel channel = new FileInputStream("D:\\a.txt").getChannel();
            FileLock lock = channel.tryLock();
            Thread.sleep(2000);
            lock.release();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
