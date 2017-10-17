package com.hm.nio;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by dumingwei on 2017/6/2.
 */
public class FileChannelTest {

    public static void main(String args[]) {

        File f = new File("D:\\FileInputStreamTest.java");
        try {
            FileChannel inChannel = new FileInputStream(f).getChannel();
            FileChannel outChannel = new FileOutputStream("D:\\a.txt").getChannel();
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
            Charset charset=Charset.forName("UTF-8");
            outChannel.write(buffer);
            buffer.clear();
            CharsetDecoder decoder=charset.newDecoder();
            CharBuffer charBuffer=decoder.decode(buffer);
            System.out.println(charBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
