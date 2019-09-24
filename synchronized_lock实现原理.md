# Synchronized底层实现原理

```java
public class MainTest {

    public synchronized void test() {
            System.out.println("Hello world");
    }

}
```

对应的字节码

```
public class com.hm.sync_test.MainTest {
  public com.hm.sync_test.MainTest();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public synchronized void test();
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #3                  // String Hello world
       5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}

```


```java
package com.hm.sync_test;

public class MainTest {

    public void test() {
        synchronized (MainTest.class){
            System.out.println("Hello world");
        }
    }

}

```

对应的字节码

```
public class com.hm.sync_test.MainTest {
  public com.hm.sync_test.MainTest();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public void test();
    Code:
       0: ldc           #2                  // class com/hm/sync_test/MainTest
       2: dup
       3: astore_1
       4: monitorenter
       5: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
       8: ldc           #4                  // String Hello world
      10: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      13: aload_1
      14: monitorexit
      15: goto          23
      18: astore_2
      19: aload_1
      20: monitorexit
      21: aload_2
      22: athrow
      23: return
    Exception table:
       from    to  target type
           5    15    18   any
          18    21    18   any
}


```

synchronized映射成字节码指令就是增加来两个指令：monitorenter和monitorexit。
1. 当一个线程进行执行的遇到monitorenter指令的时候，它会去尝试获得锁，如果没有获得锁，那么阻塞。
2. 当一个线程进行执行的遇到monitorenter指令的时候，它会去尝试获得锁，如果获得锁那么锁计数+1（为什么会加一呢，因为它是一个可重入锁，所以需要用这个锁计数判断锁的情况），如果没有获得锁，那么阻塞。当它遇到monitorexit的时候，锁计数器-1，当计数器为0，那么就释放锁。

我们注意到，上面的代码中有2个monitorexit。第14行和第20行。

马上回答这个问题：上面我以前写的文章也有表述过，synchronized锁释放有两种机制，一种就是执行完释放；另外一种就是抛出异常，虚拟机释放。图中第2个monitorexit就是发生异常时执行的流程。
而且，从图中我们也可以看到在第15行，有一个goto指令`15: goto          23`，也就是说如果正常运行结束会跳转到23行执行。

参考链接：

1. [详解synchronized与Lock的区别与使用](https://blog.csdn.net/u012403290/article/details/64910926)
