先扯点别的：最近被公司外派到了嘉定区，新租了一个房子，马马虎虎，每天走路上班大约30分钟。早上8点半上班，下午5点半下班，和以前的作息规律有点不一样，逐渐适应，调整，然后还得把跑步这项运动坚持下来。

ThreadPoolExecutor的继承结构
![这里写图片描述](https://imgconvert.csdnimg.cn/aHR0cDovL2ltZy5ibG9nLmNzZG4ubmV0LzIwMTgwMzEyMjExNzA1NTc1?x-oss-process=image/format,png)

ThreadPoolExecutor有两类方法方法可以用来执行任务。

### 1. execute方法
这个方法是在`Executor`接口中定义的，不会返回执行结果，在`ThreadPoolExecutor` 类中有具体的实现。

```java
void execute(Runnable command);
```
execute方法接受一个Runnable对象作为参数，方法执行没有返回结果。

### 2. submit方法
这个方法是在`ExecutorService` 接口中定义的，在`AbstractExecutorService` 类中有具体的实现。

```java
//提交一个Callable
<T> Future<T> submit(Callable<T> task);

//提交一个Runnable
Future<?> submit(Runnable task);

//提交一个Runnable并默认值result，在任务执行完毕以后，调用{Future.get()}方法会返回这个默认值
<T> Future<T> submit(Runnable task, T result);
```

### 源码探索

**新建ThreadPoolExecutor实例**

一般可以使用`Executors`类中的静态方法来创建`ThreadPoolExecutor`实例。

```java
//创建一个可固定数量线程的线程池和一个无限队列
public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
}

//创建只有一个线程的线程池和一个无限队列   
public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
}

//创建可以有无限多个线程的线程池和一个同步队列。
//如果想向队列中加入元素，队列必须是空的，如果不为空，会阻塞。
//如果想从队列中获取数据，如果队列为空，则阻塞。   
public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
}
```
上面的三个方法各有一个重载方法，可以用于指定`ThreadFactory` 。上面的方法内部还是调用了`ThreadPoolExecutor` 的构造方法。

```java
public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
            maximumPoolSize <= 0 ||
            maximumPoolSize < corePoolSize ||
            keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.acc = System.getSecurityManager() == null ?
                null :
                AccessController.getContext();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
}
```
构造函数中参数的含义
1. corePoolSize：核心线程池大小，即使核心线程是空闲状态也不会被销毁。除非设置allowCoreThreadTimeOut为true。创建一个线程池以后并不会创建任何新的线程，而是等待有任务到来才去创建线程执行任务。可以调用`prestartAllCoreThreads()`方法或者`prestartCoreThread()`方法预先创建corePoolSize个或者1个线程，等待任务的到来。
2. maximumPoolSize：线程池最大线程数，这个参数也是一个非常重要的参数，它表示在线程池中最多能创建多少个线程。
3. keepAliveTime：表示线程空闲多长时间会终止。默认情况下，只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用，直到线程池中的线程数不大于corePoolSize。即当线程池中的线程数大于corePoolSize时，如果一个线程空闲的时间达到keepAliveTime，则会终止，直到线程池中的线程数不超过corePoolSize。但是如果调用了allowCoreThreadTimeOut(boolean)方法，并传入true，在线程池中的线程数不大于corePoolSize时，keepAliveTime参数也会起作用，直到线程池中的线程数为0。
4. unit：keepAliveTime的单位。
5. workQueue：阻塞队列，用来存储等待执行的任务，这个参数的选择也很重要，会对线程池的运行过程产生重大影响，一般来说，这里的阻塞队列有以下几种选择`ArrayBlockingQueue` `LinkedBlockingQueue` `SynchronousQueue` 。
6. threadFactory：线程工厂，主要用来创建线程。
7. handler：表示当拒绝处理任务时的策略，有以下四种取值

 - ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。 
 - ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。 
 - ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）。
 - ThreadPoolExecutor.CallerRunsPolicy：由调用`ThreadPoolExecutor.execute()`方法所在的线程执行该任务 。
 
 ThreadPoolExecutor类中一些变量
```java
//注释1处，ctl是一个组合值，标志当前线程池的状态和线程数量
private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

private static final int COUNT_BITS = Integer.SIZE - 3;//等于29
//用二进制表示CAPACITY     0001 1111 1111 1111 1111 1111 1111 1111 
private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

//线程池的状态
//用二进制表示RUNNING      1110 0000 0000 0000 0000 0000 0000 0000
private static final int RUNNING    = -1 << COUNT_BITS;
//用二进制表示SHUTDOWN     0000  0000 0000 0000 0000 0000 0000 0000
private static final int SHUTDOWN   =  0 << COUNT_BITS;
//用二进制表示STOP         0010  0000 0000 0000 0000 0000 0000 0000
private static final int STOP       =  1 << COUNT_BITS;
//用二进制表示TIDYING      0100  0000 0000 0000 0000 0000 0000 0000
private static final int TIDYING    =  2 << COUNT_BITS;
//用二进制表示TERMINATED   0110  0000 0000 0000 0000 0000 0000 0000
private static final int TERMINATED =  3 << COUNT_BITS;

//任务缓存队列，用来存放等待执行的任务
private final BlockingQueue<Runnable> workQueue;

//操作许多变量都需要这个锁
private final ReentrantLock mainLock = new ReentrantLock();

//保存创建的Worker，需要获取mainLock才可以操作这个变量
private final HashSet<Worker> workers = new HashSet<Worker>();

//是否允许为核心线程设置存活时间
private volatile boolean allowCoreThreadTimeOut;

//用来记录线程池中曾经出现过的最大线程数
private int largestPoolSize;

//用来记录已经执行完毕的任务个数
private long completedTaskCount;
```
几个用来获取线程池状态和线程池中线程数量的方法

```java
private static int runStateOf(int c)     { return c & ~CAPACITY; }
private static int workerCountOf(int c)  { return c & CAPACITY; }
private static int ctlOf(int rs, int wc) { return rs | wc; }
```


```java
private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
```

注释1处，ctl是一个组合值，标志当前线程池的状态和线程数量，这个int类型的值的高3位表示线程状态，低29位用来表示线程数量（Android的同学可以类比MeasureSpec）。初始化的时候线程池的状态是 `RUNNING`。

   
### 接下来，我们分析一下ThreadPoolExecutor的execute方法
```java
public void execute(Runnable command) {
    //任务不能为空
    if (command == null)
        throw new NullPointerException();
    int c = ctl.get();
    //注释1处，获取线程状态
    if (workerCountOf(c) < corePoolSize) {
        if (addWorker(command, true))
            return;
        //添加线程失败
        c = ctl.get();
    }
    //注释2处
    //如果线程池状态是RUNNING 并且任务成功添加到workQueue中
    if (isRunning(c) && workQueue.offer(command)) {
        //再次检查
        int recheck = ctl.get();
        //2.1如果线程池状态线程池是`SHUTDOWN`了，并且能成功的把任务从队列中移除
        if (! isRunning(recheck) && remove(command))
	        //拒绝任务
            reject(command);
        //2.2任务加入到队列了，但是当前没有工作线程就添加一个线程
        else if (workerCountOf(recheck) == 0)
            addWorker(null, false);
    }
    //注释3处 
    else if (!addWorker(command, false))
        reject(command);
}
```

上面代码执行的步骤使用文字来叙述一下。

注释1处，如果当前工作线程数量，小于核心线程的数量，就尝试添加一个新线程，此时添加的是一个**核心线程**，并把传入的`command`作为新添加的线程的第一个任务，添加成功就返回。调用`addWorker`方法会以原子方式检查`runState`和`workerCount`并且通过返回`false`来避免在假唤醒的时候添加线程。

注释2处，如果当前线程池正在运行并且任务可以成功加入到队列中，我们仍然需要再次检查线程池（检查线程池的运行状态，当前线程池中的工作线程数量）。为什么要再次检查呢？因为我们向阻塞队列中添加元素并不一定能立即添加成功。
比如队列满了以后，我们要等到队列有可用空间才能将元素加入到队列中。所以我们在添加元素成功以后，这段时间内线程池的状态可能发生了变化，所以要再次检查。

当任务被添加到了阻塞队列前，线程池处于RUNNING 状态，但如果在添加到队列成功后，线程池进入了SHUTDOWN 状态或者其他状态，这时候是不应该再接收新的任务的，所以需要把这个任务从队列中移除，并且拒绝该任务。同样，在没有添加到队列前，可能有一个有效线程，但添加完任务后，这个线程可能因为闲置超时或者异常被干掉了，这时候需要创建一个新的线程来执行任务。
 * 2.1 如果线程池状态不是是`RUNNING`了，并且能成功的把任务从队列中移除,就拒绝任务。
 * 2.2 如果任务加入到队列了，但是此时线程池中没有工作线程了，就添加一个线程来执行任务。

注释3处，如果我们不能把任务加入队列，那么我们尝试添加一个新线程，如果添加失败就拒绝任务。

**注意：在这里我们也可以看到执行一个任务的步骤可以分三个大步骤**

1. 如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个核心线程去执行这个任务；
2. 如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会将其添加到任务缓存队列当中。
3. 如果任务缓存队列已满，则会创建新的线程去执行任务（线程数量不能大于maximumPoolSize）；

###### 看一下方法中调用的addWorker方法

```java
private boolean addWorker(Runnable firstTask, boolean core) {
    //标签，break或者continue到这里
    retry:
    //外层循环
    for (;;) {
        int c = ctl.get();
        //当前线程的状态
        int rs = runStateOf(c);

        //注释1处，这个if条件可以拆分为3中情况，只要满足一种就直接返回false
        //1. 线程池状态是STOP,或TIDYING，或TERMINATED，
        //2.（线程池状态是SHUTDOWN或STOP，或TIDYING，或TERMINATED）&& firstTask != null
        //3.（线程池状态是SHUTDOWN或STOP，或TIDYING，或TERMINATED）&& workQueue.isEmpty()
        if (rs >= SHUTDOWN &&
            ! (rs == SHUTDOWN &&
               firstTask == null &&
               ! workQueue.isEmpty()))
            return false;
	    //内层循环
        for (;;) {
            //获取工作线程数量
            int wc = workerCountOf(c);
            //注释2处，当线程池中的线程数量达到上限以后，返回false
            if (wc >= CAPACITY ||
                wc >= (core ? corePoolSize : maximumPoolSize))
                return false;
            //注释3处，如果线程数量能够增加，则break到retry标签，（跳过内外层两层循环），继续往下执行。
            if (compareAndIncrementWorkerCount(c))
                break retry;
            c = ctl.get();  // 重新读取 ctl 的值
            //注释4处，如果线程池的状态发生了改变，就continue到retry标签，重新执行外层循环。
            if (runStateOf(c) != rs)
                continue retry;//则continue到retry标签，继续执行循环
            }
        }
    //注释5处，开始添加Worker的逻辑
    //标记新添加的工作线程是否启动	
    boolean workerStarted = false;
    //标记新的线程是否添加成功
    boolean workerAdded = false;
    //工作线程
    Worker w = null;
    try {
        //注释6处
        w = new Worker(firstTask);
        final Thread t = w.thread;
        if (t != null) {
            //说明线程工厂成功的创建了一个线程
            final ReentrantLock mainLock = this.mainLock;
            mainLock.lock();
            try {
                //注释7处，获取锁以后再次检查线程池状态
                int rs = runStateOf(ctl.get());
	        	//如果线程池状态是RUNNING 或 线程池状态是SHUTDOWN 并且fistTask==null
                if (rs < SHUTDOWN ||
                    (rs == SHUTDOWN && firstTask == null)) {
                    if (t.isAlive()) // 表示线程已经被启动过了，则抛出异常
                        throw new IllegalThreadStateException();
                    //把新线程添加到线程工作集    
                    workers.add(w);
                    int s = workers.size();
                    //改变最大线程数
                    if (s > largestPoolSize)
                        largestPoolSize = s;
                    //成功添加工作线程
                    workerAdded = true;
                }
            } finally {
                mainLock.unlock();
            }
            //注释8处，如果成功添加任务，启动任务
            if (workerAdded) {
                t.start();
                workerStarted = true;
            }
        }
    } finally {
	    //注释9处，最后判断启动任务如果失败，就把任务从工作集中移除，减小最大线程数largestPoolSize，并试着终止线程池
        if (! workerStarted)
            addWorkerFailed(w);
    }
    //返回任务是否成功启动
    return workerStarted;
}
```
首先，方法有一个`boolean core`参数,标记是否是要添加核心线程池，为true表示要添加核心线程池。

然后我们看一下注释1处的条件判断，如果不满足，无法添加工作线程返回false。

```java
        if (rs >= SHUTDOWN &&
            ! (rs == SHUTDOWN &&
               firstTask == null &&
               ! workQueue.isEmpty()))
            return false;
```

如果if条件满足，那么必须同时满足下列第1点和第2点。
1.  rs >= SHUTDOWN，即线程池的状态是SHUTDOWN，或者STOP，或者TIDYING，或者TERMINATED。
2. `! (rs == SHUTDOWN && firstTask == null && ! workQueue.isEmpty())` 为true，也就是` (rs == SHUTDOWN && firstTask == null && ! workQueue.isEmpty())` 为false。也就是满足下面三小点中的任何一点就可以
2.1 rs == SHUTDOWN为false，也就是线程池的状态是STOP，或者TIDYING，或者TERMINATED。
2.2 或者 firstTask == null为 false，也就是firstTask != null。
2.3 或者 ! workQueue.isEmpty()为false ，也就是workQueue.isEmpty()为true，也就是队列中已经没有任务了。

这个条件判断等价下面三种情况

1. 线程池状态是STOP,或TIDYING，或TERMINATED，
2.（线程池状态是SHUTDOWN或STOP，或TIDYING，或TERMINATED）&& firstTask != null
3.（线程池状态是SHUTDOWN或STOP，或TIDYING，或TERMINATED）&& workQueue.isEmpty()

注释2处，当线程池中的线程数量达到上限以后，返回false。如果是添加核心线程，线程数量不能超过corePoolSize。如果是普通线程，数量不能超过maximumPoolSize。也不能超过最大线程数量CAPACITY`(2^29)-1`。

注释3处，如果线程数量能够增加，则break到retry标签，（跳过内外层两层循环），继续往下执行添加线程的操作。

注释4处，如果线程池的状态发生了改变，就continue到retry标签，重新执行外层循环判断是否可以添加线程。

注释5处，开始添加Worker的逻辑。

注释6处，创建工作线程。

注释7处，获取锁以后再次检查线程池状态，如果状态正常就把新线程添加到线程工作集，成功添加工作线程。

注释8处，如果成功添加任务，启动线程。

注释9处，最后判断启动任务如果失败，就把任务从工作集中移除，减小最大线程数largestPoolSize，并试着终止线程池。


###### 我们看一下Worker这个类的精简版

```java
 private final class Worker extends AbstractQueuedSynchronizer implements Runnable {

    //执行当前任务的线程
    final Thread thread;
    //要执行的任务，可能为null
    Runnable firstTask;
    //每个线程的任务计数器，用来标记当前线程执行了多少个任务
    /** Per-thread task counter */
    volatile long completedTasks;

    //构造函数
    Worker(Runnable firstTask) {
        //...
        this.firstTask = firstTask;
        this.thread = getThreadFactory().newThread(this);
    }
    //注释1处，Worker在run方法中调用了ThreadPoolExecutor的runWorker方法并传入自己，这就是为什么线程能够复用的根本原因。我们下面分析。
    public void run() {
        runWorker(this);
    }
        
    //...
    //省略其他方法
}
```

注释1处，Worker在run方法中调用了ThreadPoolExecutor的runWorker方法并传入自己，这就是为什么线程能够复用的根本原因。我们下面分析。

ThreadPoolExecutor的runWorker方法

```java
 final void runWorker(Worker w) {
     Thread wt = Thread.currentThread();
     Runnable task = w.firstTask;
     w.firstTask = null;
     w.unlock(); //允许中断
     boolean completedAbruptly = true;
     try {
 	    //注释1处，while循环，从队列中取出任务可能会导致阻塞。
         while (task != null || (task = getTask()) != null) {
             w.lock();
             //注释2处，在适当的时机中断线程
             if ((runStateAtLeast(ctl.get(), STOP) ||
                  (Thread.interrupted() &&
                   runStateAtLeast(ctl.get(), STOP))) &&
                 !wt.isInterrupted()) 
               //中断线程     
               wt.interrupt();
             try {
                 //可以在任务执行之前做一些操作
                 beforeExecute(wt, task);
                 Throwable thrown = null;
                 try {
 	                //注释3处，执行任务
                     task.run();
                 } finally {
                     //在任务执行完毕以后做一些操作
                     afterExecute(task, thrown);
                 }
             } finally {
                 task = null;
                 w.completedTasks++;
                 w.unlock();
             }
         }
         completedAbruptly = false;
     } finally {
 	    //注释4处，移除当前Worker，可能会终止线程池，或者创建新的Worker替代当前Worker等等。
         processWorkerExit(w, completedAbruptly);
     }
 }
```
注释1处，while 循环，如果Worker的firstTask不为null，或者`getTask()`从队列中获取任务不为null。
注意：从队列中获取任务是阻塞的。如果Worker能够取到任务，就执行取出的任务。如果从队列中取任务的时候阻塞了，那么当前工作线程就会阻塞，这就是为什么线程可以复用不退出的原因。
注释2处，在适当的时候中断Worker的工作线程wt。
注释3处，执行任务。
注释4处，如果没有可执行的任务，或者异常终止则Worker停止工作，就结束当前工作线程。

**另外还有两个方法注意一下**

我们可以继承ThreadPoolExecutor重写这两个方法用来在在执行每个任务之前和任务执行完毕之后做一些操作。

```java
protected void beforeExecute(Thread t, Runnable r) { }
```

```java
protected void afterExecute(Runnable r, Throwable t) { }
```

###### 我们看一下getTask()这个方法
```java
/**
 * 执行阻塞或定时等待任务，取决于当前配置设置，或者当前worker由于以下原因必须停止的时候返回null：
 * 1. worker的数量大于maximumPoolSize 
 * 2. 线程池停止了（STOP）
 * 3. 线程池 shutdown了并且任务队列empty
 * 4. worker在等待从队列中获取任务的时候超时，这里的超时时间就是我们给线程池设置的keepAliveTime
 *
 * @return 返回一个任务；或者当 worker必须退出的时候返回null，在这种情况下，workerCount 会减少。
 */
private Runnable getTask() {
    boolean timedOut = false; // 标记上次调用poll()方法获取数据是否超时

    for (;;) {//死循环
        int c = ctl.get();
        //线程池状态
        int rs = runStateOf(c);

        // 1. 线程池状态是STOP,或TIDYING，或TERMINATED返回null
        // 2. 线程池状态是SHUTDOWN，或STOP,或TIDYING，或TERMINATED && 任务队列为空，返回null
        if (rs >= SHUTDOWN && (rs >= STOP || workQueue.isEmpty())) {
            decrementWorkerCount();
            return null;
        }
        //Worker的数量
        int wc = workerCountOf(c);

        //注释1处
        boolean timed = allowCoreThreadTimeOut || wc > corePoolSize;
        //减少线程池中线程数量，返回null
        if ((wc > maximumPoolSize || (timed && timedOut))
            && (wc > 1 || workQueue.isEmpty())) {
            //如果能够减少Worker数量返回null
            if (compareAndDecrementWorkerCount(c))
                return null;
            continue;
        }

        try {
            //注释2处
            //获取任务并返回，如果timed为true，则使用poll方法，在keepAliveTime时间内获取任务，
            //如果timed为false，否则使用take方法，可以无限等待，这也是为什么核心线程不退出的原因。
            Runnable r = timed ?
                workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
                workQueue.take();
            if (r != null)
                //返回获取的任务
                return r;
            timedOut = true;
        } catch (InterruptedException retry) {
            timedOut = false;
        }
    }
}
```

注释1处，如果们设置`allowCoreThreadTimeOut`为true，那么核心线程也是用超时机制，否则只有非核心线程使用超时机制。

我们重点看一下注释2处：

* 如果timed为true，则使用poll方法，在keepAliveTime时间内获取任务，这就是keepAliveTime参数起作用的原因。

* 如果timed为false，否则使用take方法，可以无限等待，这也是为什么核心线程不退出的原因。


### 停止线程池
   
```java
public interface ExecutorService extends Executor {
   
    void shutdown();
   
    List<Runnable> shutdownNow();
}
```
  
ThreadPoolExecutor的shutdown方法

```java
 /**
  * 启动有序关闭，执行以前提交的任务，但不接受新任务。如果线程池状态已经是shut down，调用没有副作用。
  *
  */
public void shutdown() {
    final ReentrantLock mainLock = this.mainLock;
    mainLock.lock();
    try {
        checkShutdownAccess();
        //将线程池状态标记为SHUTDOWN
        advanceRunState(SHUTDOWN);
        //尝试终止空闲的线程
        interruptIdleWorkers();
        onShutdown(); // hook for ScheduledThreadPoolExecutor
    } finally {
        mainLock.unlock();
    }
    //尝试终止线程池
    tryTerminate();
}
```

ThreadPoolExecutor的shutdownNow方法

```java
/**
  * 尝试停止所有正在执行的任务，停止处理队列中的任务，并返回等待执行的任务的列表。
  * @return 阻塞队列中的任务任务列表
  */
public List<Runnable> shutdownNow() {
    List<Runnable> tasks;
    final ReentrantLock mainLock = this.mainLock;
    mainLock.lock();
    try {
        checkShutdownAccess();
        //将状态置为STOP
        advanceRunState(STOP);
        //尝试终止所有工作线程
        interruptWorkers();
        //清空任务队列
        tasks = drainQueue();
    } finally {
        mainLock.unlock();
    }
    //尝试终止线程池
    tryTerminate();
    return tasks;
}
```




先分析到这里，以后有新的理解和体会，再继续写。

现在总结一下 线程池执行任务的流程：
1.如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；
 2.如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，若添加成功，则该任务会等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；
 3.如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
 
结尾：若有不正之处请多多谅解，并欢迎批评指正。

参考链接：
【1】[Java并发编程：线程池的使用](http://www.cnblogs.com/dolphin0520/p/3932921.html)


