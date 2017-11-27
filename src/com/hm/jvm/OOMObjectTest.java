package com.hm.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/26 0026.
 */
public class OOMObjectTest {

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
        System.gc();
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
    }

    static class OOMObject {
        public byte[] placeHolder = new byte[64 * 1024];
    }
}
