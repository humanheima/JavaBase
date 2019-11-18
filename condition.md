今天学习一下Condition的使用和实现原理

![Condition.png](https://upload-images.jianshu.io/upload_images/3611193-caeee377b5402868.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

使用Condition实现生产者和消费者模式
```java
/**
 * Created by dumingwei on 2017/7/4.
 */
public class ThreadCommunicateTestUseLock {

    private int queueSize = 100;
    private Queue<Integer> queue = new ArrayDeque<>(queueSize);
    //创建一个锁对象
    private Lock lock = new ReentrantLock();
    //标记队列没有满
    private Condition notFull = lock.newCondition();
    //标记队列不为空
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        ThreadCommunicateTestUseLock test = new ThreadCommunicateTestUseLock();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();

        producer.start();
        consumer.start();
    }

    class Consumer extends Thread {

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("队列空，等待数据");
                            //队列已空
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    sleep(100);
                    queue.poll();                //每次移走队首元素
                    //通知生产者生产数据
                    notFull.signal();
                    System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Producer extends Thread {

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == queueSize) {
                        try {
                            System.out.println("队列满，等待有空余空间");
                            //队列已满
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    sleep(100);
                    queue.offer(1);       //每次插入一个元素
                    //通知消费者消费数据
                    notEmpty.signal();
                    System.out.println("向队列取中插入一个元素，队列剩余空间：" + (queueSize - queue.size()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}

```
看了看注释，竟然是17年创建的类，也是时光荏苒了。

**Condition的await()方法**作用
1. 让当前线程等待直到被唤醒或者被中断。
2. 和`Condition`对象关联的锁被自动释放当前线被挂起直到下面四种场景发生：
    2.1 其他线程调用了该`Condition`对象的`signal`方法并且当前线程被选为被唤醒的线程。
    2.2 其他线程调用了该`Condition`对象的signalAll`方法。
    2.3 其他线程中断了当前线程并且线程挂起是支持线程中断的。
    2.4 假唤醒发生。

在所有的场景中，在该方法返回之前，当前线程必须重新获取了和该`Condition`对象关联的锁。当该方法返回的时候确保了当前线程一定持有了和该`Condition`对象关联的锁。

Condition是一个接口，我们看它的一个子类的实现。

```
public abstract class AbstractQueuedSynchronizer
    extends AbstractOwnableSynchronizer
    implements java.io.Serializable {

    public class ConditionObject implements Condition, java.io.Serializable {
        /** 条件队列中的第一个节点 */
        private transient Node firstWaiter;
        /** 条件队列中最后一个节点 */
        private transient Node lastWaiter;
    }

}

```

**ConditionObject的await()方法**
```
public final void await() throws InterruptedException {
    if (Thread.interrupted())//如果被中断，直接抛出异常
        throw new InterruptedException();
    //注释1处，把当前线程加入到条件等待队列
    Node node = addConditionWaiter();
    //注释2处，释放锁，才知道为什么说调用await会释放锁了
    int savedState = fullyRelease(node);
    int interruptMode = 0;
    //注释3处，这是一个while循环
    while (!isOnSyncQueue(node)) {
        //阻塞当前线程
        LockSupport.park(this);
        //如果被中断了，就跳出循环
        if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
            break;
    }
   //注释4处，在同步队列中的节点尝试获取锁并且在等待过程中没有被中断
    if (acquireQueued(node, savedState) && interruptMode != THROW_IE)
        interruptMode = REINTERRUPT;
    if (node.nextWaiter != null) 
        //清理已经取消的节点
        unlinkCancelledWaiters();
    if (interruptMode != 0)
        //注释5处，根据在等待过程中是否被中断来抛出中断异常，或者重新中断当前线程，或者什么也不做
        reportInterruptAfterWait(interruptMode);
}

```

注释1处，将当前线程加入到条件等待队列。

ConditionObject的addConditionWaiter()方法

```
private Node addConditionWaiter() {
    Node t = lastWaiter;
    // 如果尾节点取消等待了，遍历清除所有已经取消等待的节点。
    if (t != null && t.waitStatus != Node.CONDITION) {
         unlinkCancelledWaiters();
         //清除完以后，lastWaiter就是最后一个正在等待的节点或者为null。
         t = lastWaiter;
    }
    Node node = new Node(Thread.currentThread(), Node.CONDITION);
   
    if (t == null)
        firstWaiter = node;
    else
        t.nextWaiter = node;
    //将尾节点指向新插入的节点
    lastWaiter = node;
    return node;
}
```
ConditionObject的await()方法的注释2处，释放当前线程持有的所有锁

**AQS的fullyRelease(Node node)方法**
```
final int fullyRelease(Node node) {
    boolean failed = true;
    try {
        int savedState = getState();
        if (release(savedState)) {
            failed = false;
            return savedState;
        } else {
            //如果不能完全释放所有的锁，抛出异常
            throw new IllegalMonitorStateException();
        }
    } finally {
        if (failed)
            node.waitStatus = Node.CANCELLED;
    }
}

```
注意：这个方法如果不能完全释放所有的锁，抛出异常。释放了所有的锁之后，AQS的同步状态值  `state==0`。

ConditionObject的await()方法的注释3处，这是一个while循环。
```
while (!isOnSyncQueue(node)) {
    //挂起当前线程
    LockSupport.park(this);
    //线程恢复以后，判断一下在挂起过程中是否被中断过
    if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
        break;
}
```

首先调用`isOnSyncQueue(Node node)`判断当前线程是否在同步队列中等待获取锁，如果没有的话就挂起当前线程。
```
final boolean isOnSyncQueue(Node node) {
    if (node.waitStatus == Node.CONDITION || node.prev == null)
        return false;
    if (node.next != null) // If has successor, it must be on queue
        return true;
       
    return findNodeFromTail(node);
}

```
线程恢复以后（其他线程调用等待条件的notify()方法并且当前线程被选择为唤醒的线程，或者其他线程调用等待条件的notifyAll()方法），判断一下在挂起过程中是否被中断过。

```java
private int checkInterruptWhileWaiting(Node node) {
    return Thread.interrupted() ?
            (transferAfterCancelledWait(node) ? THROW_IE : REINTERRUPT) :
            0;
}
```

ConditionObject的await()方法的注释4处，在同步队列中的节点尝试获取锁并且在等待过程中没有被中断，将中断模式设置为REINTERRUPT。

ConditionObject的await()方法的注释5处，根据中断模式interruptMode来决定是否抛出中断异常，重新中断当前线程。

```java
private void reportInterruptAfterWait(int interruptMode)
    throws InterruptedException {
    if (interruptMode == THROW_IE)
        //抛出异常
        throw new InterruptedException();
    else if (interruptMode == REINTERRUPT)
        //再次中断自己
        selfInterrupt();
}

```

Condition的await()方法基本分析完毕，现在看一看Condition的signal()方法

```java
/**
 * 将在条件队列中等待时间最长的线程移动到同步队列中来获取锁。
 */
public final void signal() {
    //如果当前线程没有持有写锁，抛出异常
    if (!isHeldExclusively())
        throw new IllegalMonitorStateException();
    Node first = firstWaiter;
    if (first != null)
        //唤醒第一个等待者
        doSignal(first);
}

```
```java
private void doSignal(Node first) {
    do { 
        //将firstWaiter赋值为firstWaiter的下一个等待节点
        if ( (firstWaiter = first.nextWaiter) == null)
            lastWaiter = null;
        first.nextWaiter = null;
    } while (!transferForSignal(first) && (first = firstWaiter) != null);
}

```
这个方法的逻辑就是从等待队列的头部开始向后遍历，直到将某一个节点从条件队列中移动到同步队列中去获得锁。

```java
/**
 * 把一个队列从条件队列中移动到同步队列中。
 * 如果成功返回true。
 * @param node the node
 * @return 如果成功返回true。 (否则说明该节点在signal之前取消等待了。)
 */
final boolean transferForSignal(Node node) {
    /*
     * 注释1处，如果不能改变waitStatus，说明该节点取消等待了。返回false。
     */
    if (!compareAndSetWaitStatus(node, Node.CONDITION, 0))
         return false;

    //注释2处，加入队列
    Node p = enq(node);
    int ws = p.waitStatus;
    //注释3处，直接唤醒
    if (ws > 0 || !compareAndSetWaitStatus(p, ws, Node.SIGNAL))
        LockSupport.unpark(node.thread);
    //返回true
    return true;
}

```
注释1处，如果不能改变waitStatus，说明该节点取消等待了。返回false。
注释2处，将当前节点加入队列同步队列。
注释3处，如果当前线程已经取消等待了，或者无法将当前节点的等待状态设置为SIGNAL，就直接唤醒节点的线程。


Condition的signalAll()方法

```
public final void signalAll() {
    if (!isHeldExclusively())
        throw new IllegalMonitorStateException();
    Node first = firstWaiter;
    if (first != null)
        
        doSignalAll(first);
}
```

该方法与signal的区别就在最后一行代码

```java
private void doSignalAll(Node first) {
    //将第一个条件等待线程和最后一个条件等待线程都置为null
    lastWaiter = firstWaiter = null;
    do {
        //唤醒所有的线程
        Node next = first.nextWaiter;
        first.nextWaiter = null;
        transferForSignal(first);
        first = next;
    } while (first != null);
}

```

```java
/**
 * 把一个队列从条件队列中移动到同步队列中。
 * 如果成功返回true。
 * @param node the node
 * @return 如果成功返回true。 (否则说明该节点在signal之前取消等待了。)
 */
final boolean transferForSignal(Node node) {
    /*
     * 注释1处，如果不能改变waitStatus，说明该节点取消等待了。返回false。
     */
    if (!compareAndSetWaitStatus(node, Node.CONDITION, 0))
         return false;

    //注释2处，加入队列
    Node p = enq(node);
    int ws = p.waitStatus;
    //注释3处，直接唤醒
    if (ws > 0 || !compareAndSetWaitStatus(p, ws, Node.SIGNAL))
        LockSupport.unpark(node.thread);
    //返回true
    return true;
}
```












