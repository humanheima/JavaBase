本篇文章，我们学习研究一下Java中的阻塞队列阻塞队列。一是为了了解阻塞队列的实现原理和常用方法。二是为了后续学习研究线程池打下基础。

阻塞队列都实现了BlockingQueue接口，BlockingQueue的继承结构如下所示。

![BlockingQueue.png](https://upload-images.jianshu.io/upload_images/3611193-923ca243918da4b6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

BlockingQueue的方法有四类，它们的差异体现在处理某个不能立即满足，但是在将来某个时间点可能会满足的操作的时候。

比如向队列中插入一个元素的时候。这个时候队列可能已经满了，或者当前线程无法获得锁对象，那么这个时候四类方法会有不同的处理方式。

* 第一类抛出异常。
* 第二类返回一个特殊的值（根据操作类型返回null或者false）。
* 第三类无限阻塞当前线程直到操作成功。
* 第四类只会阻塞当前线程特定一段时间，如果在特定时间内还没有操作成功则放弃操作。

如下表所示：

|表头|Throws exception|Special value|Blocks|Times out|
|---|----|-----|-----|---|
|插入|{@link #add add(e)}|{@link #offer offer(e)}|{@link #put put(e)}|{@link #offer(Object, long, TimeUnit) offer(e, time, unit)}|
|删除|{@link #remove remove()}|{@link #poll poll()}|{@link #take take()}|{@link #poll(long, TimeUnit) poll(time, unit)}|
|检查|{@link #element element()}|{@link #peek peek()}|||

BlockingQueue的一些性质：
* BlockingQueue不能插入null元素。
* BlockingQueue可以有最大容量限制。
* BlockingQueue的实现是线程安全的。

接下来我们就学习一下BlockingQueue的一个子类`LinkedBlockingQueue`。

![LinkedBlockingQueue.png](https://upload-images.jianshu.io/upload_images/3611193-fcf22d157842d50b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


LinkedBlockingQueue是基于链表节点的阻塞队列。可以指定最大容量，默认情况下最大容量是`Integer.MAX_VALUE`。元素先进先出，在队列尾部插入元素，在队列头部获取元素。

链表节点类，是一个双向链表。

```java
static final class Node<E> {
       
    E item;

    Node<E> prev;

    Node<E> next;

    Node(E x) {
        item = x;
    }
}
```

### LinkedBlockingQueue的部分成员变量
```
//队列容量限制
private final int capacity;

//队列中的元素个数
private final AtomicInteger count = new AtomicInteger();

//队列头元素
transient Node<E> head;

//队列尾元素
private transient Node<E> last;

//获取元素的时候要持有的锁
private final ReentrantLock takeLock = new ReentrantLock();

//队列非空的条件
private final Condition notEmpty = takeLock.newCondition();

//插入元素要持有的锁
private final ReentrantLock putLock = new ReentrantLock();

//队列非满的条件，
private final Condition notFull = putLock.newCondition();
```

### 构造函数

```
public LinkedBlockingQueue() {
    this(Integer.MAX_VALUE);
}

//使用一个集合初始化LinkedBlockingQueue
public LinkedBlockingQueue(Collection<? extends E> c) {
    this(Integer.MAX_VALUE);
    final ReentrantLock putLock = this.putLock;
    putLock.lock(); // Never contended, but necessary for visibility
    try {
        int n = 0;
        for (E e : c) {
            if (e == null)
                throw new NullPointerException();
            if (n == capacity)
                throw new IllegalStateException("Queue full");
            enqueue(new Node<E>(e));
            ++n;
        }
        count.set(n);
    } finally {
        putLock.unlock();
    }
}

public LinkedBlockingQueue(int capacity) {
    if (capacity <= 0) throw new IllegalArgumentException();
    this.capacity = capacity;
    //注释1处
    last = head = new Node<E>(null);
}
```

前两个构造函数都会调用第三个构造函数。注释1处，我们自己初始化了虚拟的头节点和尾节点。

接下来我们看下队列常用的方法

### 插入数据的方法

offer(E e)

```
/**
 * 将指定元素插入到队列尾部。如果队列已满则不插入，返回false。插入成功返回true。
 *
 * 当使用一个有容量限制的队列的时候，相对于{@link BlockingQueue#add add}方法来说，该方法更可取。因为{@link BlockingQueue#add add}方法
 * 在添加元素失败的时候仅仅抛出一个异常。
 *
 * @throws NullPointerException 指定元素为 null 抛异常
 */
public boolean offer(E e) { 
    //元素不能为null
    if (e == null) throw new NullPointerException();
    final AtomicInteger count = this.count;
    //元素个数超过最呆容量，直接返回fasle
    if (count.get() == capacity)
        return false;
    int c = -1;
    Node<E> node = new Node<E>(e);
    final ReentrantLock putLock = this.putLock;
    //注释1处，获取锁
    putLock.lock();
    try {
        //注释2处
        if (count.get() < capacity) {
            //注释3处
            enqueue(node);
            //注释4处
            c = count.getAndIncrement();
            if (c + 1 < capacity)
                //通知生产者，队列不满，可以插入元素
                notFull.signal();
        }
    } finally {
        //注释5处
        putLock.unlock();
    }
    if (c == 0)
        //c == 0说明队列中至少有一个元素，通知消费者，队列不为空
        signalNotEmpty();
    //c>=0说明插入成功
    return c >= 0;
}
```

注释1处，获取锁，如果不能立即获取到锁，则阻塞当前线程。
注释2处，判断，只有当前队列中元素数量小于最大限制才执行插入操作。
注释3处，将元素插入到队列末尾。
注释4处，原子性的将count加1。
```
c = count.getAndIncrement();
```
**一定要注意，返回值是加之前的值并不是加之后的值。**

注释5处，释放锁。

到这里我们应该看出来了，阻塞队列是如何实现`阻塞`功能的呢？，两个字：**加锁**。


enqueue(Node<E> node)方法
```
private void enqueue(Node<E> node) {
    //将last的next赋值为node，然后将last赋值为last.next
    last = last.next = node;
}
```

offer(E e, long timeout, TimeUnit unit)，在指定时间内将元素插入到队列末尾。

```
/**
 * 将指定元素插入到队列末尾，如果队列已满的话，则等待，如果在指定时间队列还是满的，则不执行插入操作，返回false。
 *
 * @return {@code true} 插入成功返回ture，如果在指定时间队列还是满的，说明无法执行插入操作，返回false。
 * @throws InterruptedException {@inheritDoc}
 * @throws NullPointerException {@inheritDoc}
 */
public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
    if (e == null) throw new NullPointerException();
    //初始的等待时常
    long nanos = unit.toNanos(timeout);
    int c = -1;
    final ReentrantLock putLock = this.putLock;
    final AtomicInteger count = this.count;
    //注释1处
    putLock.lockInterruptibly();
    try {
        //如果队列中元素数量已经到达最大限制，while循环等待
        while (count.get() == capacity) {
            //注释2处
            if (nanos <= 0)
                return false;
            //注释3处
            nanos = notFull.awaitNanos(nanos);
        }
        //插入元素
        enqueue(new Node<E>(e));
        c = count.getAndIncrement();
        if (c + 1 < capacity)
            //通知生产者
            notFull.signal();
    } finally {
        //释放锁
        putLock.unlock();
    }
    if (c == 0)
        //队列中有一个元素，通知消费者
        signalNotEmpty();
    return true;
}
```
注释1处，获取锁，该方法是可以被中断的。
注释2处，如果条件满足，说明在获取锁以后，没有在指定的时间内插入元素，则返回false。
注释3处，这里要解释一下。

Condition的`long awaitNanos(long nanosTimeout) throws InterruptedException;`

方法返回值是指定的参数nanosTimeout减去等待此方法返回的时间。如果返回值大于0，那么我们应该继续等待，如果返回值小于等于0，说明我们的等待时间已经耗尽。也就是注释2处，等待时间耗尽，直接返回false。

public void put(E e)，将元素插入到队列末尾，如果队列已满的话，会等待直到空间可用，然后插入数据。可以被中断。

```
public void put(E e) throws InterruptedException {
     if (e == null) throw new NullPointerException();
     int c = -1;
     Node<E> node = new Node<E>(e);
     final ReentrantLock putLock = this.putLock;
     final AtomicInteger count = this.count;
     putLock.lockInterruptibly();
     try {
         //注释1处
         while (count.get() == capacity) {
             notFull.await();
         }
         enqueue(node);
         c = count.getAndIncrement();
         if (c + 1 < capacity)
             notFull.signal();
     } finally {
         putLock.unlock();
     }
     if (c == 0)
         signalNotEmpty();
}
```

注释1处，是该方法和`offer(E e)`方法的区别所在。如果队列已满，该方法会一直等待队列不满，然后执行插入操作，除非中途被中断。
而`offer(E e)`方法是不会等待的，直接返回false。

### 获取数据的方法

E poll()，获取队列中的第一个元素，返回值可能为null。

```
public E poll() {
    final AtomicInteger count = this.count;
    //队列中没有元素
    if (count.get() == 0)
        return null;
    E x = null;
    int c = -1;
    final ReentrantLock takeLock = this.takeLock;
    //获取锁
    takeLock.lock();
    try {
        if (count.get() > 0) {
            //注释1处
            x = dequeue();
            //注释2处
            c = count.getAndDecrement();
            //取出元素后，队列不为空，通知消费者
            if (c > 1)
                notEmpty.signal();
        }
    } finally {
        takeLock.unlock();
    }
    //注释3处
    if (c == capacity)
        signalNotFull();
    return x;
}
```

注释1处，移除队列头元素，然后将count减1。

注释2处，将元素数量减1，并返回减之前的值。
```
c = count.getAndDecrement();
```

注释3处，如果元素数量减1之前是capacity，这时候队列中元素的数量已经是`capacity-1`了，通知生产者可以插入数据。

```
//将元素从队列中移除并返回。
private E dequeue() {
    Node<E> h = head;
    Node<E> first = h.next;
    h.next = h; // 帮助GC
    head = first;
    E x = first.item;
    first.item = null;
    return x;
}
```

public E poll(long timeout, TimeUnit unit)，在指定时间内获取获取队列中的第一个元素。

```
public E poll(long timeout, TimeUnit unit) throws InterruptedException {
    E x = null;
    int c = -1;
    long nanos = unit.toNanos(timeout);
    final AtomicInteger count = this.count;
    final ReentrantLock takeLock = this.takeLock;
    //获取锁，可中断
    takeLock.lockInterruptibly();
    try {
        //whle循环，队列为空，则等待
        while (count.get() == 0) {
            //等待超时，直接返回null
            if (nanos <= 0)
                return null;
            nanos = notEmpty.awaitNanos(nanos);
        }
        //移除队列头元素，然后将count减1
        x = dequeue();
        c = count.getAndDecrement();
        //队列不为空，通知消费者
        if (c > 1)
            notEmpty.signal();
    } finally {
        takeLock.unlock();
    }
    //队列不满，通知生产者
    if (c == capacity)
        signalNotFull();
    return x;
}
```

E take()，获取队列中第一个元素，如果不存在元素则一直等待。可以被中断。

```
public E take() throws InterruptedException {
    E x;
    int c = -1;
      final AtomicInteger count = this.count;
      final ReentrantLock takeLock = this.takeLock;
      //获取锁，可被中断
      takeLock.lockInterruptibly();
      try {
          //注释1处
          while (count.get() == 0) {
              notEmpty.await();
          }
          //获取并移除元素
          x = dequeue();
          c = count.getAndDecrement();
          if (c > 1)
              notEmpty.signal();
      } finally {
          takeLock.unlock();
      }
      if (c == capacity)
          signalNotFull();
      return x;
}
```

注释1处，如果队列为空则等待。注意和`E poll()`方法的区别。如果队列为空`E poll()`方法不会等待。


```
public E peek() {
    if (count.get() == 0)
        return null;
    final ReentrantLock takeLock = this.takeLock;
    takeLock.lock();
    try {
        Node<E> first = head.next;
        if (first == null)
            return null;
        else
            return first.item;
    } finally {
        takeLock.unlock();
    }
}
```
该方法用来获取队列中的头元素，但是不会将元素从队列中移除。

### 移除数据的方法

```
public boolean remove(Object o) {
  if (o == null) return false;
     //注释1处
     fullyLock();
     try {
         for (Node<E> trail = head, p = trail.next; p != null; trail = p, p = p.next) {
             if (o.equals(p.item)) {
                 //注释2处，移除元素，p就是要删除的元素，trail是要删除元素的前一个元素
                 unlink(p, trail);
                 return true;
             }
         }
         return false;
     } finally {
         fullyUnlock();
     }
}
```
注释1处，获取插入数据的锁和获取数据的锁。移除数据期间不允许插入和获取数据。
```java
void fullyLock() {
    putLock.lock();
    takeLock.lock();
}
```

```java
void unlink(Node<E> p, Node<E> trail) {
    //将要删除元素的数据置为null
    p.item = null;
    //将要删除元素的前一个元素的next指向p的next
    trail.next = p.next;
    //如果删除的是最后一个元素，将last指向前一个元素
    if (last == p)
        last = trail;
    //通知消费者，队列不满
    if (count.getAndDecrement() == capacity)
        notFull.signal();
}
```














