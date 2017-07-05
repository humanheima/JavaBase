package com.hm.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * Created by dumingwei on 2017/6/2.
 */
public class CharsetTransform {

    public static void main(String[] args) {

        Charset charset = Charset.forName("GBK");
        CharsetEncoder encoder = charset.newEncoder();
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = CharBuffer.allocate(8);
        charBuffer.put('孙');
        charBuffer.put('悟');
        charBuffer.put('空');
        charBuffer.flip();

        try {
            ByteBuffer byteBuffer=encoder.encode(charBuffer);
            for (int i = 0; i < byteBuffer.capacity(); i++) {
                System.out.print(byteBuffer.get(i)+" ");
            }
            System.out.println(decoder.decode(byteBuffer));

        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
    }
}
