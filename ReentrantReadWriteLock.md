上一篇文章[Java AbstractQueuedSynchronizer（AQS）浅析之一](https://www.jianshu.com/p/4bb911920394)
我们分析了AQS在ReentrantLock中的运用。**注意：ReentrantLock是以独享模式获取锁和释放锁的**，今天这篇文章，我们看一看AQS在ReentrantReadWriteLock中的应用。

** ReentrantReadWriteLock 类结构**
```java
public class ReentrantReadWriteLock
        implements ReadWriteLock, java.io.Serializable {
    
    /** 读锁 */
    private final ReentrantReadWriteLock.ReadLock readerLock;
    /** 写锁 */
    private final ReentrantReadWriteLock.WriteLock writerLock;
    /** 实现所有的同步机制 */
    final Sync sync;
    
    public ReentrantReadWriteLock() {
        //默认是非公平锁
        this(false);
    }
    
    public ReentrantReadWriteLock(boolean fair) {
        sync = fair ? new FairSync() : new NonfairSync();
        readerLock = new ReadLock(this);
        writerLock = new WriteLock(this);
    }
    
    public ReentrantReadWriteLock.WriteLock writeLock() { return writerLock; }
    public ReentrantReadWriteLock.ReadLock  readLock()  { return readerLock; }
    
    /**
     * ReentrantReadWriteLock的同步实现，子类有公平和非公平版本。
     */
    abstract static class Sync extends AbstractQueuedSynchronizer{
        
        static final class HoldCounter {
            
        }
        
        static final class ThreadLocalHoldCounter
                    extends ThreadLocal<HoldCounter> {
            
        }
            
    }
    
    /**
     * 非公平版本
     */
    static final class NonfairSync extends Sync{
        
    }
    
    /**
     * 公平版本
     */
    static final class FairSync extends Sync {
        
    }
    
    
    /**
     * 读锁
     */
    public static class ReadLock implements Lock, java.io.Serializable {
        
    }
    
    /**
     * 写锁
     */
    public static class WriteLock implements Lock, java.io.Serializable {
        
    }
    
}
```
![ReentrantReadWriteLock.png](https://upload-images.jianshu.io/upload_images/3611193-28814995d50ea5ae.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

上图中：绿色虚线表示实现接口，蓝色实线表示继承类，红色带加号的实线表示内部类。

ReentrantReadWriteLock.ReadLock获取的是读锁（共享锁）
ReentrantReadWriteLock.WriteLock获取的是写锁（独享锁）

本篇以非公平的读写锁进行分析

## ReentrantReadWriteLock.ReadLock

### ReadLock的lock()方法
```
public void lock() {
     //调用AQS的acquireShared(int arg)方法
     sync.acquireShared(1);
}
```
**AQS的acquireShared(int arg)方法**
```
public final void acquireShared(int arg) {
   //先尝试获取锁
    if (tryAcquireShared(arg) < 0)
        doAcquireShared(arg);
}
```
AQS没有实现`tryAcquireShared(int arg)`方法，我们看其子类`ReentrantReadWriteLock.Sync`的实现。

```
protected final int tryAcquireShared(int unused) {
    /*
     * 流程:
     * 1. 如果写锁被别的线程持有，失败。
     * 2. 否则，当前线程有资格获取读锁，所以询问是否由于排队策略需要阻塞。 如果不需要阻塞的话，
     * 尝试通过CAS修改state来获取锁并更新锁计数。请注意该步骤不进行重入获取锁的检查，
     * 这个检查推迟到获取锁的完整版本方法（fullTryAcquireShared(Thread current)）中来避免
     * 在这个更典型的非重入情况下必须检查锁计数。
     * 3. 如果步骤2由于当前线程没有资格获取锁或者CAS修改state失败或者读锁的数量已经饱和而失败
     * 就使用具有完整重试循环的版本的方法来获取读锁，即调用`fullTryAcquireShared(Thread current)`方法。
     */
    Thread current = Thread.currentThread();
   //获取同步状态值
    int c = getState();
    //注释1处，如果存在写锁，并且锁的持有者不是当前线程，返回-1
    if (exclusiveCount(c) != 0 &&
            getExclusiveOwnerThread() != current)
        return -1;
    //获取共享锁的数量
    int r = sharedCount(c);
    //注释2处，走到注释2处说明没有写锁，或者写锁的持有者是当前线程。此时我们是可以获取读锁的。
    if (!readerShouldBlock() &&
            r < MAX_COUNT &&
            compareAndSetState(c, c + SHARED_UNIT)) {
        if (r == 0) {//注释3处
            //r==0，说明当前线程是第一个获取读锁的线程
            firstReader = current;
            //当前线程，持有锁的数量从0到1
            firstReaderHoldCount = 1;
        } else if (firstReader == current) {
            //如果第一个获取读锁的线程就是当前线程，增加当前线程持有的读锁的数量
            firstReaderHoldCount++;
        } else {
            //注释4处
            HoldCounter rh = cachedHoldCounter;
            //如果rh为null或者rh的线程id不是当前线程的线程id
            if (rh == null || rh.tid != getThreadId(current))
                //获取当前线程的HoldCounter对象，赋值给cachedHoldCounter
                cachedHoldCounter = rh = readHolds.get();
            //如果此条件满足，说明上一个成功获取读锁的线程就是当前线程，但是现在把读锁释放了，所以读锁计数为0
            else if (rh.count == 0)
                //设置当前线程的HoldCounter对象
                readHolds.set(rh);
           //当前线程的HoldCounter对象的读锁数量加1
            rh.count++;
        }
        //成功获得读锁返回1
        return 1;
    }
    return fullTryAcquireShared(current);
}

```
注释1处，如果存在写锁，并且锁的持有者不是当前线程，返回-1，表示获取读锁失败。

注释2处，走到注释2处说明没有写锁，或者写锁的持有者是当前线程。此时我们是可以获取读锁的。

注释2处，如果3个条件都满足
1. !readerShouldBlock()：当前线程不应该阻塞。
2. 读锁的数量小于MAX_COUNT（65535）。
3. 以CAS的方式更新state成功。

条件1，当前线程不应该阻塞。那么什么时候当前获取读锁的线程应该阻塞呢？看源码

**ReentrantReadWriteLock.NonfairSync的readerShouldBlock()方法**
```
final boolean readerShouldBlock() {
    return apparentlyFirstQueuedIsExclusive();
}
```
**AQS的apparentlyFirstQueuedIsExclusive()方法**
```
final boolean apparentlyFirstQueuedIsExclusive() {
    Node h, s;
    return (h = head) != null &&
        (s = h.next)  != null &&
        !s.isShared()         &&
        s.thread != null;
}
```
从上面的代码可以看出，只有当等待队列不为空，并且head的后继节点有效且在等待以独享模式获取锁的时候，当前线程才会阻塞。

条件2，共享锁的数量小于MAX_COUNT（65535）。

这里我们要提一下AQS的state字段（int类型，32位），该字段用来描述锁被获取的次数。
```
/**
 * The synchronization state.
 */
private volatile int state;
```

在独享锁中这个值通常是0或者1（如果是重入锁的话state值就是获取锁的次数），在共享锁中state就是共享锁的数量。但是在ReentrantReadWriteLock中有读、写两把锁，所以需要在一个整型变量state上分别表示读锁（共享锁）和写锁（独享锁）的数量（或者也可以叫状态）。于是将state变量“按位切割”切分成了两个部分，高16位表示读锁状态（读锁数量），低16位表示写锁状态（写锁数量）。如下图所示：
![read_write_bit.png](https://upload-images.jianshu.io/upload_images/3611193-ea6605b811d4a02a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

我们再看一下`ReentrantReadWriteLock.Sync`类中获取独享锁和共享锁的方法就明白了
```java
abstract static class Sync extends AbstractQueuedSynchronizer {
    private static final long serialVersionUID = 6317671515068378041L;

    /*
     * 读锁和写锁的提取常量和函数
     * 锁的状态逻辑上分为两个无符号的short类型（16位）
     * 低16位代表独享锁的数量，高16位代表共享锁的数量。
     * 
     */
    static final int SHARED_SHIFT   = 16;//16位
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);//65536
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;//65535
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;//65535

    /** 无符号右移16位，获取共享锁的数量 */
    static int sharedCount(int c)    { return c >>> SHARED_SHIFT; }
    /** 获取独享锁的数量 */
    static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; }
}
```
条件3，以CAS的方式更新state成功。
```
compareAndSetState(c, c + SHARED_UNIT)
```
注意我们每获取一次读锁要加65536（2的16次方）。

如果注释2中的3个条件都为true，说明我们成功获取了读锁。那么进入if代码块中。

注释3处，这里出现了两个变量
```
//第一个获取读锁的线程，将读锁数量从0改变到1，并且还没有释放读锁。
private transient Thread firstReader = null;
//第一个获取读锁的线程持有的读锁的数量
private transient int firstReaderHoldCount;
```

注释4处，有多了几个陌生的变量和类

**ReentrantReadWriteLock.Sync.HoldCounter**
```
/**
 * 每个线程的读锁计数器，保存在线程本地变量中
 */
static final class HoldCounter {
  //线程持有读锁的数量
   int count = 0;
   // 线程id
   final long tid = getThreadId(Thread.currentThread());
}
```
这个类为每个线程保存持有读锁的数量。该类的实例保存在ThreadLocal中。

ReentrantReadWriteLock.Sync的成员变量
```
//保存上一个成功获取读锁的线程的HoldCounter对象
private transient HoldCounter cachedHoldCounter;
```

**ReentrantReadWriteLock.Sync.ThreadLocalHoldCounter**
```
static final class ThreadLocalHoldCounter extends ThreadLocal<HoldCounter> {
    public HoldCounter initialValue() {
        return new HoldCounter();
    }
}
```
这个类就是用来保存HoldCounter的。注意该类的`initialValue`方法返回一个新的`HoldCounter`对象。

在注释2处，如果3个条件都满足说明成功获取了读锁，返回1  。如果获取失败就调用`fullTryAcquireShared(Thread current)`方法来获取读锁。

**ReentrantReadWriteLock.Sync的fullTryAcquireShared(Thread current)方法**
```
/**
 * 获取读锁的完整版本，会解决tryAcquireShared方法中未处理的CAS失败和可重入获取读锁的问题。
 */
final int fullTryAcquireShared(Thread current) {
  
    HoldCounter rh = null;
    for (;;) {//无限循环
        //获取state
        int c = getState();
        //注释1处，如果写锁的数量不为0
        if (exclusiveCount(c) != 0) {
            //如果不是当前线程持有互斥锁返回-1
            if (getExclusiveOwnerThread() != current)
                return -1;
            // else 表示我们持有写锁；阻塞在这里会导致死锁。 
        } else if (readerShouldBlock()) {//注释2处，如果当前线程应该阻塞
            //注释3处， 当前线程持有读锁
            if (firstReader == current) {
                //断言 firstReaderHoldCount > 0;
            } else {
                //注释4处
                if (rh == null) {
                    rh = cachedHoldCounter;
                    if (rh == null || rh.tid != getThreadId(current)) {
                        //获取当前线程的HoldCounter
                        rh = readHolds.get();
                        if (rh.count == 0) 
                            //从线程本地变量readHolds中移除当前线程
                            readHolds.remove();
                    }
                }
                //当前线程的锁计数为0，没有持有读锁，返回-1。
                if (rh.count == 0)
                    return -1;
            }
        }
        //如果读锁的数量等于MAX_COUNT，抛出异常
        if (sharedCount(c) == MAX_COUNT)
            throw new Error("Maximum lock count exceeded");
        //注释5处，如果以CAS的方式获取更新state成功，说明当前线程成功获取读锁，最后返回1。
        if (compareAndSetState(c, c + SHARED_UNIT)) {  
            if (sharedCount(c) == 0) {
                //注释6处，当前线程是第一个获取读锁的线程，为firstReader和firstReaderHoldCount赋值
                firstReader = current;
                firstReaderHoldCount = 1;
            } else if (firstReader == current) {
                //注释7处，当前线程再次获取锁，更新firstReaderHoldCount值
                firstReaderHoldCount++;
            } else { //注释8处
                if (rh == null)
                    rh = cachedHoldCounter;
                if (rh == null || rh.tid != getThreadId(current))
                    //获取当前线程的HoldCounter
                    rh = readHolds.get();
                else if (rh.count == 0)
                    //当前线程第一次获取读锁
                    readHolds.set(rh);
                //重入获取锁，增加当前线程持有的锁数量
                rh.count++;
                cachedHoldCounter = rh; // cache for release
            }
            //成功获取锁，返回1
            return 1;
        }
    }
}
```
注释1处，如果写锁的数量不为0，如果不是当前线程持有写锁，则获取失败，返回-1。

注释2处，没有写锁并且当前线程需要阻塞

注释3处，`firstReader == current`说明当前线程持有读锁并且还没有释放锁。

如果注释3条件不满足，进入注释4处，获取当前线程的锁计数器，如果当前线程的锁计数为0,说明当前线程没有持有读锁，就从线程本地变量中移除当前线程的锁计数器。

注释5处，如果以CAS的方式获取更新state成功，说明当前线程成功获取读锁。最后返回1。

注释6处，如果当前线程是第一个获取读锁的线程，为firstReader和firstReaderHoldCount赋值。

注释7处，如果当前线程是第一个获取读锁的线程再次获取锁，更新firstReaderHoldCount值。

注释8处，创建当前线程的锁计数器保存在线程本地变量中。

如果获取锁失败，就会调用**AQS的doAcquireShared(int arg)**方法

```
public final void acquireShared(int arg) {
    //tryAcquireShared(arg)方法返回值小于0说明获取锁失败
    if (tryAcquireShared(arg) < 0)
        doAcquireShared(arg);
}
```
**AQS的doAcquireShared(int arg)**方法
```
/**
 * 以共享不可中断的模式获取锁
 */
private void doAcquireShared(int arg) {
    //以共享模式将节点添加到等待队列中
    final Node node = addWaiter(Node.SHARED);
    boolean failed = true;
    try {
        boolean interrupted = false;
        for (;;) {//无限循环
            final Node p = node.predecessor();
                if (p == head) {
                   //再次尝试获取锁
                   int r = tryAcquireShared(arg);
                   if (r >= 0) {//获取锁成功
                       //注释1处，这个方法有点意思
                       setHeadAndPropagate(node, r);
                       p.next = null; // help GC
                       if (interrupted)
                           selfInterrupt();
                       failed = false;
                       return;
                   }
               }
               //阻塞线程，等待被唤醒
               if (shouldParkAfterFailedAcquire(p, node) &&
                   parkAndCheckInterrupt())
                   interrupted = true;
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
```
这个过程可以参考上一篇文章[Java AbstractQueuedSynchronizer（AQS）浅析之一](https://www.jianshu.com/p/4bb911920394)中的`acquireQueued(final Node node, int arg)`方法分析过程，这里再简单说一下。

这是一个无限循环，
1. 以共享模式添加当前线程到等待队列。
2.  添加成功以后再次尝试获取锁，如果获取成功，就返回。
3. 获取失败，阻塞当前线程，等待被唤醒。
4. 线程被唤醒后，继续循环步骤1，2，3。

我们看下注释1处。当前节点在获取读锁以后，如果还有剩余资源（tryAcquireShared(arg)方法返回值大于0），或者当前节点的后继节点以共享模式等待获取锁（就是获取读锁），就唤醒当前节点的后继节点。

**AQS的setHeadAndPropagate(Node node, int propagate)**

```
/**
 * 设置等待队列的头并且检查当前节点的后继节点是否以共享模式等待获取锁，如果当前节点的后继节点
 * 是以共享模式等待获取锁，则当propagate > 0或设置了PROPAGATE状态的时候就唤醒后继节点。
 *
 */
 private void setHeadAndPropagate(Node node, int propagate) {
    Node h = head;
    //将自己设置为head
    setHead(node);
    //尝试唤醒下一个队列中的节点
    if (propagate > 0 || h == null || h.waitStatus < 0 ||
        (h = head) == null || h.waitStatus < 0) {
        Node s = node.next;
        //后继节点为null或者以共享模式等待获取锁
        if (s == null || s.isShared())
            //这个方法后面再说
            doReleaseShared();
    }
}

```
现在ReadLock获取锁的过程分析完了，接下来看看ReadLock释放锁的过程。

### ReadLock的unlock()方法
```
public void unlock() {
   sync.releaseShared(1);
}
```

**AQS的releaseShared(int arg)方法**

```
public final boolean releaseShared(int arg) {
   //注释1处，
   if (tryReleaseShared(arg)) {
       doReleaseShared();
       return true;
   }
   return false;
}
```

注释1处，尝试释放读锁，AQS没有实现tryReleaseShared(int arg)方法，我们直接看ReentrantReadWriteLock.Sync的实现。

**ReentrantReadWriteLock.Sync的的tryReleaseShared(int arg)方法**
```
protected final boolean tryReleaseShared(int unused) {
    Thread current = Thread.currentThread();
    if (firstReader == current) {
        //如果第一个读线程是当前线程，那么firstReaderHoldCount肯定大于0
        
        //注释1处，
        if (firstReaderHoldCount == 1)
            firstReader = null;
        else
            firstReaderHoldCount--;
    } else {//注释2处，
        HoldCounter rh = cachedHoldCounter;
        if (rh == null || rh.tid != getThreadId(current))
            //获取当前线程的锁计数
            rh = readHolds.get();
        int count = rh.count;
        //锁计数等于1，说明当前线程只获取了一次读锁， 本次释放以后，就不再持有锁
        if (count <= 1) {
            readHolds.remove();
            if (count <= 0)//count <= 0表明当前线程没有持有读锁，所以不能释放。
                throw unmatchedUnlockException();
        }
        //当前线程count减去1
        --rh.count;
    }
    //注释3处，
    for (;;) {//CAS可能失败，所以需要循环
        int c = getState();
        int nextc = c - SHARED_UNIT;
        if (compareAndSetState(c, nextc))
            // 释放读锁对读线程没有作用，但是会影响写线程，
            // 但是如果读锁和写锁都被释放的情况下，写线程可以获取写锁。
            return nextc == 0;
    }
}

```
注释1处，如果firstReaderHoldCount==1，说明当前线程只获取了一次读锁，所以当释放锁以后，当前线程就不再持有读锁了，应该将firstReader置为null。否则就将firstReaderHoldCount减去1。

注释2处，获取当前线程的锁计数，如果锁计数等于1，说明当前线程只获取了一次读锁， 本次释放以后，就不再持有锁。 如果锁计数<= 0表明当前线程没有持有读锁，所以不能释放。最后将当前线程的锁计数减去1。

注释3处，使用CAS的方式修改读锁状态。因为CAS可能会失败，所以在for循环里进行修改。修改成功后返回读锁的数量，如果为0，说明读锁完全释放了，这个时候是可以获取写锁的。

**AQS的doReleaseShared方法**
```
private void doReleaseShared() {
    for (;;) {
        Node h = head;
        if (h != null && h != tail) {
            int ws = h.waitStatus;
            if (ws == Node.SIGNAL) {
                if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                    continue;// loop to recheck cases
                  //唤醒后继节点
                  unparkSuccessor(h);
            }
            else if (ws == 0 &&
                       !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                continue;                // loop on failed CAS
        }
        if (h == head)                   // loop if head changed
            break;
    }
}

```
该方法就是为了唤醒后继节点。


## ReentrantReadWriteLock.WriteLock 

### WriteLock的lock()方法
```
public void lock() {
    sync.acquire(1);
}
```
**AQS的acquire(int arg)方法**
```
public final void acquire(int arg) {
    if (!tryAcquire(arg) &&
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        selfInterrupt();
}
```
在[Java AbstractQueuedSynchronizer（AQS）浅析之一](https://www.jianshu.com/p/4bb911920394)这篇文章中已经分析过了这个方法，现在我们只看`tryAcquire(arg)`方法的具体实现。

**ReentrantReadWriteLock.Sync的tryAcquire(arg)方法**

```
protected final boolean tryAcquire(int acquires) {
    /*
     * 流程:
     * 1. 如果存在读锁或写锁，并且读锁和写锁的持有者不是当前线程，则失败。
     * 2. 如果写锁数量超过最大数量，失败。
     * 3. 否则，当前线程有资格获取锁。
     */
    Thread current = Thread.currentThread();
    //获取同步状态值
    int c = getState();
    //获取写锁数量
    int w = exclusiveCount(c);
    //存在锁
    if (c != 0) {
        // 注释1处，
        if (w == 0 || current != getExclusiveOwnerThread())
            return false;
        //如果要获取写锁的数量加上现有写锁的数量大于65535，抛出异常。
        if (w + exclusiveCount(acquires) > MAX_COUNT)
            throw new Error("Maximum lock count exceeded");
        // 注释2处，
        setState(c + acquires);
        return true;
    }
   //注释3处，非公平策略的写锁，writerShouldBlock总是返回false
    if (writerShouldBlock() ||
            !compareAndSetState(c, c + acquires))
            return false;
    //注释4处
    setExclusiveOwnerThread(current);
    return true;
}

```


注释1处，存在锁，如果当前不存在写锁或者当前线程没持有写锁，获取失败。

注释2处，如果走到注释2处，说明当前线程已经持有写锁，是再次获取写锁，所以我们可以直接修改同步状态值，不需要CAS，也是美滋滋。

注释3处，如果不存锁，如果当前线程应该阻塞或者CAS更新同步状态值失败，就返回false。

注释4处，将当前线程标记为写锁的持有者，返回true。

对与AQS的acquire(int arg)方法中的其他步骤这里就不再赘述了，可以参考[Java AbstractQueuedSynchronizer（AQS）浅析之一](https://www.jianshu.com/p/4bb911920394)。

### WriteLock的unLock()方法
```
public void unlock() {
    sync.release(1);
}
```
 **AQS的release(int arg)方法**
```
public final boolean release(int arg) {
    if (tryRelease(arg)) {
        Node h = head;
        if (h != null && h.waitStatus != 0)
            //唤醒后继节点
            unparkSuccessor(h);
        return true;
    }
    return false;
}
```

只看`tryRelease(int releases)`方法

**ReentrantReadWriteLock.Sync的tryRelease(int releases) 方法**

```
protected final boolean tryRelease(int releases) {
   //注释1处，如果不是写锁的持有者，抛出异常
    if (!isHeldExclusively())
        throw new IllegalMonitorStateException();
    int nextc = getState() - releases;
    //注释2处，
    boolean free = exclusiveCount(nextc) == 0;
    if (free)
        setExclusiveOwnerThread(null);
    //注释3处
    setState(nextc);
    return free;
}
```
注释1处，如果不是写锁的持有者，抛出异常。

注释2处，如果剩余写锁数量为0，说明已经完全释放完毕，将写锁的持有者线程置为null。
注释3处，重新为同步状态state赋值，返回。

### 锁降级
如果当前线程持有写锁，然后获取了读锁，然后释放了写锁，就从写锁降级到了读锁。但是从一个读锁升级到写锁是不可能的。一个锁降级的例子

```
private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private int data = 0;

    public void processData() {
        // 获取写锁
        rwLock.writeLock().lock();
        try {
            // 修改数据
            data = 42;
            System.out.println(Thread.currentThread().getName() + " 修改数据为: " + data);

            // 在持有写锁时获取读锁（开始锁降级）
            rwLock.readLock().lock();
            try {
                // 这里仍然持有写锁和读锁
                System.out.println(Thread.currentThread().getName() + " 在写锁下读取数据: " + data);
            } finally {
                // 释放写锁，完成锁降级（此时只持有读锁）
                rwLock.writeLock().unlock();
            }

            // 现在只持有读锁，可以继续读取
            System.out.println(Thread.currentThread().getName() + " 降级后读取数据: " + data);
        } finally {
            // 释放读锁
            rwLock.readLock().unlock();
        }
    }
```


参考链接
* [不可不说的Java“锁”事](https://tech.meituan.com/2018/11/15/java-lock.html)
* [Java--读写锁的实现原理](https://mrdear.cn/2018/06/23/java/java--readwritelock/)







