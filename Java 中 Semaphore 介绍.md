### Java 中 Semaphore 介绍

`Semaphore` 是 Java 并发包（`java.util.concurrent`）中的一个同步工具，用于控制多个线程对共享资源的访问。它通过维护一组许可（permits）来限制并发访问或协调线程执行顺序。`Semaphore` 是一个计数信号量，内部通过一个计数器管理可用许可，基于 `AbstractQueuedSynchronizer` (AQS) 实现。

#### **核心概念**
- **许可计数**：`Semaphore` 维护一个整数，表示可用许可数量。
- **获取许可**：线程通过 `acquire()` 获取许可，计数减 1；如果计数 < 0，线程阻塞。
- **释放许可**：线程通过 `release()` 释放许可，计数加 1，唤醒等待线程。
- **公平性**：支持公平模式（FIFO 队列）和非公平模式（默认）。
- **灵活性**：许可可以由任何线程释放，不要求是获取者，依赖编程约定。

#### **构造方法**
```java
public Semaphore(int permits); // 默认非公平
public Semaphore(int permits, boolean fair); // 指定公平性
```
- `permits`：初始许可数。
- `fair`：true 为公平模式，false 为非公平模式。

#### **主要方法**
- **获取许可**：
    - `acquire()`：阻塞获取 1 个许可。
    - `acquire(int permits)`：获取指定数量许可。
    - `tryAcquire()`：非阻塞尝试获取许可。
    - `tryAcquire(long timeout, TimeUnit unit)`：带超时尝试获取。
- **释放许可**：
    - `release()`：释放 1 个许可。
    - `release(int permits)`：释放指定数量许可。
- **其他**：
    - `availablePermits()`：返回当前可用许可数。
    - `drainPermits()`：清空并返回所有许可。
    - `isFair()`：检查是否为公平模式。

#### **底层实现**
- 基于 AQS 的共享锁模式：
    - AQS 的 `state` 表示许可计数。
    - `acquire` 调用 `tryAcquireShared`，尝试减少 `state`。
    - `release` 调用 `tryReleaseShared`，增加 `state` 并唤醒等待线程。
- 公平模式下，AQS 使用 FIFO 队列；非公平模式下，新线程可能抢占。

---

### Semaphore 的使用场景

`Semaphore` 适用于需要限制并发访问或协调线程顺序的场景。以下是常见使用场景：

1. **限制资源访问（限流）**
    - **场景**：控制同时访问共享资源的线程数，如数据库连接池、线程池、文件访问。
    - **示例**：限制最多 5 个线程同时访问数据库。
      ```java
      Semaphore semaphore = new Semaphore(5);
      public void accessDatabase() throws InterruptedException {
          semaphore.acquire();
          try {
              // 访问数据库
          } finally {
              semaphore.release();
          }
      }
      ```
    - **效果**：最多 5 个线程并发访问，其他线程阻塞等待。

2. **线程协作与顺序控制**
    - **场景**：协调多个线程按特定顺序执行任务，如交替打印（你的 ABC 打印问题）。
    - **示例**：三个线程交替打印 ABC。
      ```java
      Semaphore semA = new Semaphore(1), semB = new Semaphore(0), semC = new Semaphore(0);
      public void printA() throws InterruptedException {
          semA.acquire();
          System.out.print("A");
          semB.release();
      }
      // 类似实现 printB 和 printC
      ```
    - **效果**：通过信号量控制，确保输出为 ABCABC...。

3. **生产者-消费者模型**
    - **场景**：控制生产者和消费者之间的资源数量，如缓冲区大小限制。
    - **示例**：限制队列最多存放 10 个元素。
      ```java
      Semaphore empty = new Semaphore(10); // 空槽
      Semaphore full = new Semaphore(0); // 满槽
      public void produce() throws InterruptedException {
          empty.acquire();
          // 添加元素到队列
          full.release();
      }
      public void consume() throws InterruptedException {
          full.acquire();
          // 从队列移除元素
          empty.release();
      }
      ```
    - **效果**：生产者等待空位，消费者等待元素，维持队列界限。

4. **停车场管理**
    - **场景**：模拟停车场，限制停车位数量。
    - **示例**：
      ```java
      Semaphore parking = new Semaphore(10); // 10 个停车位
      public void park() throws InterruptedException {
          parking.acquire();
          System.out.println("车辆停入");
          Thread.sleep(1000); // 模拟停车时间
          parking.release();
          System.out.println("车辆离开");
      }
      ```
    - **效果**：最多 10 辆车同时停车，其他车辆等待。

5. **任务分阶段执行**
    - **场景**：多个线程需要等待某阶段完成后再进入下一阶段。
    - **示例**：所有线程完成初始化后开始处理数据。
      ```java
      Semaphore barrier = new Semaphore(0);
      public void initialize() throws InterruptedException {
          // 初始化工作
          barrier.release(); // 每个线程完成初始化
      }
      public void process() throws InterruptedException {
          barrier.acquire(n); // 等待 n 个线程完成初始化
          // 处理数据
      }
      ```
    - **效果**：类似 `CyclicBarrier`，但更灵活。

---

### Semaphore 的优缺点

#### **优点**
- 简单高效，易于实现资源限制和线程协调。
- 支持多许可，灵活性强。
- 可动态调整许可（通过 `release` 增加许可）。
- 支持公平/非公平模式，适应不同场景。

#### **缺点**
- 依赖编程约定，误用 `release` 可能导致计数异常。
- 不支持读写分离（相比 `ReadWriteLock`）。
- 复杂场景下调试困难，需小心死锁。

---

### 与你的 ABC 打印代码的关联
在你的 ABC 交替打印代码中，`Semaphore` 用于线程协作：
- **初始化**：`semA(1)`、`semB(0)`、`semC(0)` 确保 A 线程先运行。
- **协作**：每个线程打印后释放下一个线程的信号量，形成 A → B → C → A 的循环。
- **场景**：这是典型的线程顺序控制场景，`Semaphore` 简洁地解决了同步问题。

---

### 总结
`Semaphore` 是一个强大的并发控制工具，通过许可计数管理线程访问和协作。它的主要场景包括资源限流、线程顺序控制、生产者-消费者模型等。在你的 ABC 打印任务中，`Semaphore` 展示了其在多线程协作中的灵活性和高效性。如果需要更深入的代码示例、其他同步工具对比（如 `Lock` 或 `CyclicBarrier`），或性能优化建议，请告诉我！