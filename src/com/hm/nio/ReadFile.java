package com.hm.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * Created by dumingwei on 2017/6/2.
 */
public class ReadFile {

    public static void main(String args[]) {
        try {
            //jdk支持的所有字符集
            SortedMap<String, Charset> map = Charset.availableCharsets();
            for (String name : map.keySet()) {
                System.out.println("name=" + name + ",value=" + map.get(name));
            }
            System.out.println();
            FileInputStream fis = new FileInputStream("D:\\a.txt");
            FileChannel channel = fis.getChannel();
            ByteBuffer bytebuffer = ByteBuffer.allocate(256);
            while (channel.read(bytebuffer) != -1) {
                bytebuffer.flip();
                Charset charset = Charset.forName("UTF-8");
                CharsetDecoder decoder = charset.newDecoder();
                CharBuffer charBuffer = decoder.decode(bytebuffer);
                System.out.print(charBuffer.toString());
                //将buffer初始化，为下一次读数据做准备
                bytebuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
