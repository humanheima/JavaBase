package com.hm.nio;

import java.nio.CharBuffer;

/**
 * Created by dumingwei on 2017/6/1.
 */
public class BufferTest {

    public static void main(String args[]) {

        CharBuffer buff = CharBuffer.allocate(8);
        System.out.println("capacity:" + buff.capacity());
        System.out.println("limit:" + buff.limit());
        System.out.println("position:" + buff.position());

        buff.put('a');
        buff.put('b');
        buff.put('c');

        System.out.println("加入三个元素后,position=" + buff.position());
        //将limit设置为position处，把position设为0
        buff.flip();
        System.out.println("执行flip()后，limit=" + buff.limit());
        System.out.println("position:" + buff.position());
        //取出第一个元素
        System.out.println("第一个元素 （position=0）=" + buff.get());
        System.out.println("取出第一个元素后position=" + buff.position());
        //position设为0 limit=capacity
        buff.clear();
        System.out.println("执行clear后 limit=" + buff.limit());
        System.out.println("执行clear后 position" + buff.position());
        System.out.println("执行clear后 缓冲区并没有被清除" + buff.get(2));
        System.out.println("执行绝对读取后，position=" + buff.position());
    }
}
