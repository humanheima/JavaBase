## ThreadLocal 源码学习
1. 实际的通过ThreadLocal创建的副本是存储在每个线程自己的threadLocals中的；
2. 一个线程中可有多个threadLocal变量 存储在threadLocals中;
3. 在进行get之前，必须先set，否则会报空指针异常；如果想在get之前不需要调用set就能正常访问的话，必须重写initialValue()方法。

线程本地变量，每个线程持有线程本地变量的副本。

举个例子先
```java
public class ThreadLocalTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        //1.在主线程中设置ThreadLocal保存的变量值
        threadLocal.set("初始名称");
        System.out.println(Thread.currentThread().getName() + " ," + threadLocal.get());
        new TestThread("线程甲", name).start();
        new TestThread("线程乙", name).start();
    }
}

class TestThread extends Thread {

    private ThreadLocal<String> threadLocal;

    public TestThread(String name, ThreadLocal<String> threadLocal) {
        super(name);
        this.threadLocal = threadLocal;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i == 6) {
                //当i==6的时候替换成当前线程名
                threadLocal.set(getName());
            }
            //获取
            System.out.println(Thread.currentThread().getName() + " ," + threadLocal.get() + "，i= " + i);
        }
    }
}

```
```
main ,初始名称
线程甲 ,null，i= 0
线程甲 ,null，i= 1
线程甲 ,null，i= 2
线程甲 ,null，i= 3
线程甲 ,null，i= 4
线程甲 ,null，i= 5
线程甲 ,线程甲，i= 6
线程甲 ,线程甲，i= 7
线程甲 ,线程甲，i= 8
线程甲 ,线程甲，i= 9
线程乙 ,null，i= 0
线程乙 ,null，i= 1
线程乙 ,null，i= 2
线程乙 ,null，i= 3
线程乙 ,null，i= 4
线程乙 ,null，i= 5
线程乙 ,线程乙，i= 6
线程乙 ,线程乙，i= 7
线程乙 ,线程乙，i= 8
线程乙 ,线程乙，i= 9
```

在这个例子中，我们创建了一个ThreadLocal变量，内部保存的是一个String类型的变量。
```
    private static ThreadLocal<String> name = new ThreadLocal<>();

```
在注释1处，我们在主线程中设置ThreadLocal保存的变量值，所以输出的是,threadLocal.get()方法获取的就是`初始名称`
```java
main ,初始名称
```
然后我们新建了两个线程，在线程的run方法中首先从0到5`threadLocal.get()`方法获取的值都是null。
然后从6开始把threadLocal设置为当前线程的名字，然后再调用`threadLocal.get()`方法输出的就是线程对应的名字了。

源码分析

构造方法
```java
public ThreadLocal() {
    
}
```

首先看下ThreadLocal的`set(T value)`方法
```java
public void set(T value) {
        Thread t = Thread.currentThread();
        //注释1处
        ThreadLocalMap map = getMap(t);
        //注释2处
        if (map != null)
            map.set(this, value);
        //注释3处
        else
            createMap(t, value);
}
```
在注释1处，首先获取当前线程的ThreadLocalMap对象。

```java
ThreadLocalMap getMap(Thread t) {
        return t.threadLocals;
}
```
Thread类的threadLocals变量是一个ThreadLocal.ThreadLocalMap对象，用来保存与当前线程相关的ThreadLocal变量。
```java
public
class Thread implements Runnable {
    //...
    //用来保存与当前线程相关的ThreadLocal变量
    ThreadLocal.ThreadLocalMap threadLocals = null;

}
```
ThreadLocalMap是ThreadLocal的一个静态内部类，只适合用来保存线程本地变量。ThreadLocalMap的实体使用弱引用来保存key。
```java
static class ThreadLocalMap {
    
     //存储entry的表，根据需要调整大小。表的长度必须是2的幂
     private Entry[] table;
    //...
    //key的类型是弱引用
    static class Entry extends WeakReference<ThreadLocal<?>> {
        /** The value associated with this ThreadLocal. */
        Object value;
    
        Entry(ThreadLocal<?> k, Object v) {
            super(k);
            value = v;
        }
    }
}
```
在`set(T value)`方法的注释2处，如果获取到的map对象不为null，则调用ThreadLocalMap的`set(ThreadLocal<?> key, Object value)`方法。

在`set(T value)`方法的注释3处，如果获取到的map对象为null，则调用createMap方法。

创建一个ThreadLocalMap对象赋值给当前线程的threadLocals变量。
```
void createMap(Thread t, T firstValue) {
        t.threadLocals = new ThreadLocalMap(this, firstValue);
}
```

ThreadLocal的`get()`方法,返回当前线程的线程本地变量的副本。如果当前线程的线程本地变量没有值，会调用initialValue方法来初始化线程本地变量的值。
```java
public T get() {
        //获取当前线程
        Thread t = Thread.currentThread();
        //获取当前线程的ThreadLocalMap对象map
        ThreadLocalMap map = getMap(t);
        if (map != null) {//如果map存在，返回对应的值
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        //map不存在。调用setInitialValue方法
        return setInitialValue();
    }
```
```java
private T setInitialValue() {
        //注释1处,默认value为null
        T value = initialValue();
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
        return value;
}
```
setInitialValue方法的注释1处调用initialValue方法。
```java
protected T initialValue() {
        return null;
}
```
initialValue方法默认返回null。我们可以重写这个方法来提供一个默认值，就像这样。
```java
private static ThreadLocal<String> name = new ThreadLocal<String>(){

    @Override
    protected String initialValue() {
       return "hello world";
    }
};
```


接下来看一下ThreadLocalMap的`set(ThreadLocal<?> key, Object value)`方法，该方法可以用来创建新的Entry，或者替换已存在的Entry。
```java
private void set(ThreadLocal<?> key, Object value) {

            Entry[] tab = table;
            int len = tab.length;
            //找到
            int i = key.threadLocalHashCode & (len-1);
            //为什么要遍历呢？因为如果存在无效的Entry的话，我们直接用新的Entry替换旧的Entry，节省内存
            for (Entry e = tab[i]; e != null; e = tab[i = nextIndex(i, len)]) {
                ThreadLocal<?> k = e.get();
                //如果存在对应的Entry，则替换值
                if (k == key) {
                    e.value = value;
                    return;
                }
                //注释1处，如果存在无效的Entry，则替换Entry
                if (k == null) {
                    replaceStaleEntry(key, value, i);
                    return;
                }
            }
            //新建Entry加入到表中
            tab[i] = new Entry(key, value);
            int sz = ++size;
            //注释2处
            if (!cleanSomeSlots(i, sz) && sz >= threshold)
                rehash();
        }

```







 


