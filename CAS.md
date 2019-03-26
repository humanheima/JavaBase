关于CompareAndSwap（CAS）的一些理解

以AtomicInteger为例


AtomicInteger中的一些变量
```java
    //unsafe实例
    private static final Unsafe unsafe = Unsafe.getUnsafe();

    //用来记录偏移量，这是一个final变量
    private static final long valueOffset;

    static {
        try {
            //valueOffset默认值是0
            valueOffset = unsafe.objectFieldOffset
                (AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    //value值
    private volatile int value;

```

构造函数
```java
/**
 * 使用0作为初始值来构建AtomicInteger实例
 */
public AtomicInteger() {
}

/**
 * 使用指定的初始值构建AtomicInteger实例
 * 
 * @param initialValue 初始值
 */
public AtomicInteger(int initialValue) {
    value = initialValue;
}
```

AtomicInteger的getAndIncrement方法
```java
/**
  * 原子性的把当前值加1。
  *
  * @return 返回以前的值
  */
public final int getAndIncrement() {
   return unsafe.getAndAddInt(this, valueOffset, 1);
}
```

Unsafe的getAndAddInt方法

```java
/**
 * 原子性的更新一个对象在偏移量为offset处的成员变量的值，或者原子性的更新一个数组在偏移量为offset处的元素的值。
 *
 * @param o 更新成员变量的对象，或者更新元素的数组
 * @param offset 成员变量或者数组元素的偏移
 * @param delta 要增加到的量
 * @return 先前的值
 * @since 1.8
 */
public final int getAndAddInt(Object o, long offset, int delta) {
    int v;
    do {
        //获取AtomicInteger对象在内存中偏移量为offset处的值
        v = getIntVolatile(o, offset);
    } while (!compareAndSwapInt(o, offset, v, v + delta));
    return v;
}

```

Unsafe的getIntVolatile方法
```java
/** Volatile version of {@link #getInt(Object, long)}  */
public native int getIntVolatile(Object o, long offset);
```

Unsafe的compareAndSwapInt方法
```java
/**
* 如果Java变量的当前值是expected，则原子性的把该变量的值更新为x。
* @return 成功返回true
*/
public final native boolean compareAndSwapInt(Object o, long offset,int expected, int x);

```
用文字叙述一下getAndIncrement这个过程。我们假设现在只有两个线程，一个是主线程，一个A线程

1. 在主线程第1次进入 do while循环，执行 `v = getIntVolatile(o, offset);`获取到的AtomicInteger的value是0赋值给v。

2. 执行`compareAndSwapInt(o, offset, v, v + delta);`如果此时`A`线程没有修改AtomicInteger的value，
那么获取AtomicInteger对象在内存中偏移量为offset处的值和v相等，执行成功，
将AtomicInteger的value更新为1，返回true，循环结束。然后返回AtomicInteger的更新之前的value 0。

3. 执行`compareAndSwapInt(o, offset, v, v + delta);`如果此时A线程修改了AtomicInteger的value（value值为1），
那么获取AtomicInteger对象在内存中偏移量为offset处的值和v已经不相等了（内存中的值已经不变成1了，而v是0），执行失败，进入下一次循环。
4. 在主线程第2次进入 do while循环，执行 `v = getIntVolatile(o, offset);`,因为A线程修改了AtomicInteger的value，
获取到的AtomicInteger的value是1赋值给v。

5. 执行`compareAndSwapInt(o, offset, v, v + delta);`如果此时`A`线程没有修改AtomicInteger的value，
那么获取AtomicInteger对象在内存中偏移量为offset处的值和v相等（都是1），执行成功，
将AtomicInteger的value更新为2，返回true，循环结束。然后返回AtomicInteger的更新之前的value 1。

上面的循环可以保证，在不同的线程更新value值不会出现被覆盖的情况。也就是说实现了线程同步。

### CAS 的问题

1. ABA问题

CAS需要在操作值的时候检查下值有没有发生变化，如果没有发生变化则更新，但是如果一个值原来是A，变成了B，又变成了A，
那么使用CAS进行检查时会发现它的值没有发生变化，但是实际上却变化了。这就是CAS的ABA问题。
常见的解决思路是使用版本号。在变量前面追加上版本号，每次变量更新的时候把版本号加一，那么A-B-A 就会变成1A-2B-3A。
目前在JDK的atomic包里提供了一个类AtomicStampedReference来解决ABA问题。
这个类的compareAndSet方法作用是首先检查当前引用是否等于预期引用，并且当前标志是否等于预期标志，
如果全部相等，则以原子方式将该引用和该标志的值设置为给定的更新值。

2. 循环时间长开销大

上面我们说过如果CAS不成功，则会原地自旋，如果长时间自旋会给CPU带来非常大的执行开销。

参考链接：

1. [Java CAS 原理剖析](https://juejin.im/post/5a73cbbff265da4e807783f5)
2. [Unsafe.java](http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/687fd7c7986d/src/share/classes/sun/misc/Unsafe.java)