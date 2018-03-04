## 进程和线程
操作系统支持同时运行多个任务，一个任务通常就是一个程序，每个运行中的程序就是一个进程。
当一个程序运行时，内部可能包含了多个顺序执行流，每个顺序执行流就是一个线程。
当一个程序进入内存运行时，即变成一个进程。进程是处于运行中的程序，具有一定的独立功能，
进程是系统进行资源分配和调度的一个独立单位。

### 创建线程的三种方式
1. 继承Thread类，重写 run方法。Thread类也是继承了Runnable接口的
2. 实现Runnable接口，重写run方法,然后把Runnable传入Thread的构造函数
3. 使用 Callable接口，可以返回线程执行结果
```java
public class ThreeWayUseThread {

    public static void main(String[] args) {
        firstWay();
        secondWay();
        thirdWay();
    }

    private static void firstWay() {
        new MyThread("firstWay").start();
    }

    private static void secondWay() {
        new Thread(new MyRunnable()).start();
    }

    private static void thirdWay() {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int result = 0;
                for (int i = 0; i < 100; i++) {
                    result += i;
                }
                return result;
            }
        });
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
```

![线程状态图](thread_lifecycle.jpg)

也可以将blocked、waiting、time waiting统称为阻塞状态

### Thread类中的方法
Thread类实现了Runnable接口，在Thread类中，有一些比较关键的属性，比如name是表示Thread的名字，可以通过Thread类的构造器中的参数来指定线程名字，
priority表示线程的优先级（最大值为10，最小值为1，默认值为5），daemon表示线程是否是守护线程，target表示要执行的任务。

1. start方法：用来启动一个线程，当调用start方法后，系统才会开启一个新的线程来执行用户定义的子任务，在这个过程中，会为相应的线程分配需要的资源。
```java
public synchronized void start() {
        /**
         * This method is not invoked for the main method thread or "system"
         * group threads created/set up by the VM. Any new functionality added
         * to this method in the future may have to also be added to the VM.
         *
         * A zero status value corresponds to state "NEW".
         */
        if (threadStatus != 0)
            throw new IllegalThreadStateException();

        /* Notify the group that this thread is about to be started
         * so that it can be added to the group's list of threads
         * and the group's unstarted count can be decremented. */
        group.add(this);

        boolean started = false;
        try {
            start0();
            started = true;
        } finally {
            try {
                if (!started) {
                    group.threadStartFailed(this);
                }
            } catch (Throwable ignore) {
                /* do nothing. If start0 threw a Throwable then
                  it will be passed up the call stack */
            }
        }
    }

    private native void start0();
```
2. run方法：不需要用户来调用的，当通过start方法启动一个线程之后，当线程获得了CPU执行时间，便进入run方法体去执行具体的任务。注意，继承Thread类
必须重写run方法，在run方法中定义具体要执行的任务。
3. sleep方法
```java
public static native void sleep(long millis) throws InterruptedException;

public static void sleep(long millis, int nanos)
    throws InterruptedException {
        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                                "nanosecond timeout value out of range");
        }

        if (nanos >= 500000 || (nanos != 0 && millis == 0)) {
            millis++;
        }

        sleep(millis);
    }

```
sleep相当于让线程睡眠，交出CPU，让CPU去执行其他的任务。但是有一点要非常注意，sleep方法不会释放锁，也就是说如果当前线程持有对某个对象的锁，
则即使调用sleep方法，其他线程也无法访问这个对象。当线程睡眠时间满后，不一定会立即得到执行，因为此时可能CPU正在执行其他的任务。所以说调用sleep
方法相当于让线程进入阻塞状态。
4. yield方法：这个方法极少使用 调用yield方法会让当前线程交出CPU权限，让CPU去执行其他的线程。它跟sleep方法类似，同样不会释放锁。但是yield不能控制
具体的交出CPU的时间，另外，yield方法只能让拥有相同优先级的线程有获取CPU执行时间的机会。注意，调用yield方法并不会让线程进入阻塞状态，而是让线程重
回就绪状态，它只需要等待重新获取CPU执行时间，这一点是和sleep方法不一样的。
```java
public static native void yield();
```
5. join方法：实际上调用join方法是调用了Object的wait方法,wait方法会让线程进入阻塞状态，并且会释放线程占有的锁，并交出CPU执行权限.。由于wait方法会
让线程释放对象锁，所以join方法同样会让线程释放对一个对象持有的锁。
```java
public final void join() throws InterruptedException {
        join(0);
    }

public final synchronized void join(long millis)
    throws InterruptedException {
        long base = System.currentTimeMillis();
        long now = 0;

        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (millis == 0) {
            while (isAlive()) {
                wait(0);
            }
        } else {
            while (isAlive()) {
                long delay = millis - now;
                if (delay <= 0) {
                    break;
                }
                wait(delay);
                now = System.currentTimeMillis() - base;
            }
        }
    }

public final synchronized void join(long millis, int nanos)
    throws InterruptedException {

        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                                "nanosecond timeout value out of range");
        }

        if (nanos >= 500000 || (nanos != 0 && millis == 0)) {
            millis++;
        }

        join(millis);
    }

```

看一个例子
```java
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + " is started");

        Thread exampleThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " is started");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " is completed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        exampleThread.start();
        exampleThread.join();
        System.out.println(Thread.currentThread().getName() + " is completed");
    }
}
```
输出结果
```java
main is started
Thread-0 is started
Thread-0 is completed
main is completed

```
可以看到main线程会等待Thread-0线程执行完毕以后，再继续执行。
6. interrupt方法：interrupt，即中断的意思。interrupt方法可以中断处于阻塞状态的线程。单独调用interrupt方法可以使得处于阻塞状态的线程抛出一个异常，
也就说。
 ```java
public void interrupt() {
        if (this != Thread.currentThread())
            checkAccess();

        synchronized (blockerLock) {
            Interruptible b = blocker;
            if (b != null) {
                interrupt0();           // Just to set the interrupt flag
                b.interrupt(this);
                return;
            }
        }
        interrupt0();
    }
    
private native void interrupt0();

```
中断处于阻塞状态的线程
```java
public class Test {
     
    public static void main(String[] args) throws IOException  {
        Test test = new Test();
        MyThread thread = test.new MyThread();
        thread.start();
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
             
        }
        thread.interrupt();
    } 
     
    class MyThread extends Thread{
        @Override
        public void run() {
            try {
                System.out.println("进入睡眠状态");
                Thread.currentThread().sleep(10000);
                System.out.println("睡眠完毕");
            } catch (InterruptedException e) {
                System.out.println("得到中断异常");
            }
            System.out.println("run方法执行完毕");
        }
    }
}
```
不能中断运行中的线程
```java
public class Test {

    public static void main(String[] args) throws IOException  {
        Test test = new Test();
        MyThread thread = test.new MyThread();
        thread.start();
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {

        }
        thread.interrupt();
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            int i = 0;
            while(i<Integer.MAX_VALUE){
                System.out.println(i+" while循环");
                i++;
            }
        }
    }
}
```
运行该程序会发现，while循环会一直运行直到变量i的值超出Integer.MAX_VALUE。所以说直接调用interrupt方法不能中断正在运行中的线程。
但是如果配合isInterrupted()方法就能够中断正在运行的线程，因为调用interrupt方法相当于将中断标志位置为true，那么可以通过调用isInterrupted()判断中断标志是否被置位来中断线程的执行。比如下面这段代码
```java
public boolean isInterrupted() {
        return isInterrupted(false);
    }
    
private native boolean isInterrupted(boolean ClearInterrupted);

```

```java
public class Test {

    public static void main(String[] args) throws IOException  {
        Test test = new Test();
        MyThread thread = test.new MyThread();
        thread.start();
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {

        }
        thread.interrupt();
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            int i = 0;
            while(!isInterrupted() && i<Integer.MAX_VALUE){
                System.out.println(i+" while循环");
                i++;
            }
        }
    }
}

```
运行会发现，打印若干个值之后，while循环就停止打印了。

但是一般情况下不建议通过这种方式来中断线程，一般会在MyThread类中增加一个属性 isStop来标志是否结束while循环，然后再在while循环中判断isStop的值。

```java
public class Test {

    public static void main(String[] args) throws IOException {
        Test test = new Test();
        MyThread thread = test.new MyThread();
        thread.start();
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
        }
        thread.setStop(true);
    }

    class MyThread extends Thread {
        private volatile boolean isStop = false;

        @Override
        public void run() {
            int i = 0;
            while (!isStop) {
                i++;
                System.out.println(i + " while循环");
            }
        }

        public void setStop(boolean stop) {
            this.isStop = stop;
        }
    }
}
```
那么就可以在外面通过调用setStop方法来终止while循环。
7. stop方法：stop方法已经是一个废弃的方法，它是一个不安全的方法。因为调用stop方法会直接终止run方法的调用，并且会抛出一个ThreadDeath错误，如果线
程持有某个对象锁的话，会完全释放锁，导致对象状态不一致。所以stop方法基本是不会被用到的。
8. destroy方法：已废弃，基本不会使用到

### 以下是关系到线程属性的几个方法：
1. getId方法：获取线程ID
2. getName和setName用来得到或者设置线程名称。
3. getPriority和setPriority用来获取和设置线程优先级。
4. setDaemon和isDaemon用来设置线程是否成为守护线程和判断线程是否是守护线程。
5. currentThread：用来获取当前线程。

守护线程和用户线程的区别在于：守护线程依赖于创建它的线程，而用户线程则不依赖。举个简单的例子：如果在main线程中创建了一个守护线程，当main方法运行
完毕之后，守护线程也会随着消亡。而用户线程则不会，用户线程会一直运行直到其运行完毕。在JVM中，像垃圾收集器线程就是守护线程。

在上面已经说到了Thread类中的大部分方法，那么Thread类中的方法调用到底会引起线程状态发生怎样的变化呢？下面一幅图就是在上面的图上进行改进而来的

![Thread生命周期图](thread_lifecycle_1.jpg)

### synchronized关键字的使用
在Java中，每一个对象都拥有一个锁标记（monitor），也称为监视器，另外，每个类也会有一个锁，它可以用来控制对static数据成员的并发访问。
(写单例模式的时候的双重检查用的锁就是类的锁)。对于synchronized方法或者synchronized代码块，当出现异常时，JVM会自动释放当前线程占用的锁，因此不会
由于异常导致出现死锁现象。
* 同步方法
1. 当一个线程正在访问一个对象的synchronized方法，那么其他线程不能访问该对象的其他synchronized方法。这个原因很简单，因为一个对象只有一把锁，当一个
线程获取了该对象的锁之后，其他线程无法获取该对象的锁，所以无法访问该对象的其他synchronized方法。
2. 当一个线程正在访问一个对象的synchronized方法，那么其他线程能访问该对象的非synchronized方法。这个原因很简单，访问非synchronized方法不需要获得
该对象的锁，假如一个方法没用synchronized关键字修饰，说明它不会使用到临界资源，那么其他线程是可以访问这个方法的，
3. 果一个线程A需要访问对象object1的synchronized方法fun1，另外一个线程B需要访问对象object2的synchronized方法fun1，即使object1和object2是同一类型）
，也不会产生线程安全问题，因为他们访问的是不同的对象，所以不存在互斥问题。
* 同步代码块
```java
 synchronized (synObject) {
    ...
 }
```
当在某个线程中执行这段代码块，该线程会获取对象synObject的锁，从而使得其他线程无法同时访问该代码块。并且如果一个线程执行一个对象的非
static synchronized方法，另外一个线程需要执行这个对象所属类的static synchronized方法，此时不会发生互斥现象，因为访问static synchronized方法占用
的是类锁，而访问非static synchronized方法占用的是对象锁，所以不存在互斥现象。如下所示
```java
public class Test {

    public static void main(String[] args) {
        final InsertData insertData = new InsertData();
        new Thread() {
            @Override
            public void run() {
                insertData.insert();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                insertData.insert1();
            }
        }.start();
    }
}

class InsertData {
    public synchronized void insert() {
        System.out.println("执行insert");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行insert完毕");
    }

    public synchronized static void insert1() {
        System.out.println("执行insert1");
        System.out.println("执行insert1完毕");
    }
}

```
输出结果
```java
执行insert
执行insert1
执行insert1完毕
执行insert完毕

```

如果能用同步代码块就优先使用，因为一个方法可能只有部分代码需要同步,这样可以提高效率。






