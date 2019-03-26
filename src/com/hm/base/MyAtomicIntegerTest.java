package com.hm.base;

import sun.misc.Unsafe;

public class MyAtomicIntegerTest {

    private static final Unsafe unsafe = Unsafe.getUnsafe();
    public static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                    (MyAtomicIntegerTest.class.getDeclaredField("value"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    private volatile int value;

    /**
     * Creates a new AtomicInteger with the given initial value.
     *
     * @param initialValue the initial value
     */
    public MyAtomicIntegerTest(int initialValue) {
        value = initialValue;
    }

    public static void main(String[] args) {
        MyAtomicIntegerTest myAtomicIntegerTest=new MyAtomicIntegerTest(100);
        System.out.println(MyAtomicIntegerTest.valueOffset);
    }


}
