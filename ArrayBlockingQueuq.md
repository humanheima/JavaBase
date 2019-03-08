### ArrayBlockingQueue 源码学习

```java
/**
     * Inserts the specified element at the tail of this queue, waiting
     * for space to become available if the queue is full.
     *
     * @throws InterruptedException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public void put(E e) throws InterruptedException {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == items.length)//队列已满则进行等待
                notFull.await();//锁是可中断的，如果被中断，就抛出InterruptedException
            //将数据加入队列
            enqueue(e);
        } finally {
            //释放锁
            lock.unlock();
        }
    }
```
```java
/**
     * Inserts element at current put position, advances, and signals.
     * Call only when holding lock.
     */
    private void enqueue(E x) {
        // assert lock.getHoldCount() == 1;
        // assert items[putIndex] == null;
        final Object[] items = this.items;
        items[putIndex] = x;
        if (++putIndex == items.length)
            putIndex = 0;
        count++;
        //唤醒等待获取数据的线程
        notEmpty.signal();
    }
```
```java
public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0)//为空则等待
                notEmpty.await();//锁是可中断的，如果被中断，就抛出InterruptedException
            //不为空返回数据
            return dequeue();
        } finally {
            
            //释放锁
            lock.unlock();
        }
    }
```
```java
/**
     * Extracts element at current take position, advances, and signals.
     * Call only when holding lock.
     */
    private E dequeue() {
        // assert lock.getHoldCount() == 1;
        // assert items[takeIndex] != null;
        final Object[] items = this.items;
        @SuppressWarnings("unchecked")
        E x = (E) items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length)
            takeIndex = 0;
        count--;
        if (itrs != null)
            itrs.elementDequeued();
        //唤醒生产者向队列中加入数据
        notFull.signal();
        return x;
    }
```