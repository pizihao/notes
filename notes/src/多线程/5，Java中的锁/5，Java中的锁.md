# Java中的锁

## Lock接口

### 有关锁

- 锁是用来控制多个线程访问共享资源的方式，一般来说，一个锁能够防止多个线程同时访问共享资源
- 有些锁是允许多个线程同时访问共享资源的，比如读写锁
- Lock接口提供了和synchronized关键字类似的同步功能，但是在使用的时候需要显式的获取和释放锁
- 虽然Lock接口缺少了隐式获取释放锁的便捷性，但是却可以实现锁获取与释放的可操作性，可中断的获取锁以及超时获取锁等，这些特性是synchronized所不具备的

### 使用

- 实例代码

  ~~~java
  public class TestLock01 {
      public static void main(String[] args) {
          //声明一个可重入锁
          Lock lock = new ReentrantLock();
          //使用这个Condition对这个锁进行相应的操作
          Condition condition = lock.newCondition();
          lock.lock();
          try {
              //等待
              condition.await(10, TimeUnit.MILLISECONDS);
              System.out.println("拿到锁");
          } catch (InterruptedException e) {
              e.printStackTrace();
          } finally {
              lock.unlock();
          }
      }
  }
  ~~~

- 上述代码展示了Lock接口加锁的过程

- 在finally块中释放锁，目的是保证在获取到锁之后，最终能够被释放

### 注意

- 不要将获取锁的过程写在try块中，如果在获取锁的时候发生了异常，异常抛出的同时也会导致锁无故释放

## 队列同步器

### 介绍

- AbstractQueuedSynchronizer    AQS
- 用来构建锁或者其他同步组件的基础框架，使用了一个int成员变量表示同步状态，通过内置的FIFO队列来完成资源获取线程的排队工作。
- state初始值为0，线程加一次锁，state就加1，重入锁加锁时，state再加1，当释放锁的时候state减1，直到减到0，锁释放完成
- 同步器提供了三个方法,getStare()，setState(new newState)，compareAndSetState(int expect,int update)，进行操作
- 锁的API是面向使用者的，它定义了与锁的公共行为，而每个锁需要完成特定的操作也是透过这些行为来完成的，但是实现是依托给同步器来完成，同步器面向的是线程访问和资源控制，定义了线程对资源是否能够获取以及线程排队等操作

### 同步器

- 同步器可以控制线程是否可以进入临界区
  - 这可以通过compareAndSetState(int expect,int update)来实现
  - 通过一个期望的值来判断是否可以进入临界区实现对资源的操作
- 临界区
  - 多个线程共同读写的资源称之为共享资源，一个只允许一个进程使用的共享资源叫做临界资源
  - 涉及到操作临界资源的代码区域称之为临界区
  - 在同一时刻，只能有一个线程进入临界区，这种情况称为互斥
- 入队和出队
  - 如果同一时刻有多个线程要进入临界区，那么就会产生竞争，竞争的结果是只有一个线程进入，其余的线程会进入等待队列等待唤醒
  - 此处通过一个非阻塞队列完成
- 唤醒操作
- 同步器可以理解为一个对象，可以根据自身状态协调线程的执行顺序，比如锁（Lock），信号量（Semaphore），屏障（CyclicBarrier），阻塞队列（Blocking Queue）

### 图示

![Java并发之同步器设计](D:\Software\idea\ideafile\JVMDome\notes\src\多线程\5，Java中的锁\java同步器设计图示.jpg)

### 设计原理

#### Node

- AQS类中有一个内部类 Node， 这个结点类是构成同步器队列的基础，同步器中拥有首结点(head)和尾结点(tail)

  ~~~java
  private transient volatile Node head;
  private transient volatile Node tail;
  ~~~

- 成功获取同步状态的线程或进入临界区使用系统资源

- 没有成功获取同步状态的线程将会成为结点加入该队列的尾部

  - 这个加入队列的过程必须是线程安全的，因此使用CAS的方式进行入队操作

    ~~~java
    private final boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }
    ~~~

#### Node的组成

- 要获取资源的线程的引用 thread
- 状态 waitStatus
  - cancelled值为1：表示当前结点的线程因为超时或中断被取消。
  - signal值为-1：表示后继结点在等待当前结点唤醒
  - condltion值为-2：表示当前结点处于等待队列中。
  - propagate值为-3：共享模式中的头结点可能处于此状态，表示前驱结点不仅会唤醒后继结点，同时也会唤醒后继结点的后继结点
  - 0：结点的初始状态，默认值
  - 注意，waitStatus为负值时表示结点处于有效状态，正值的时候表示结点已经被取消了，所以可以和0比较来判断结点是否正常
- 前驱结点 prev
- 后继结点 next
- 等待队列的下一个结点 nextWater
- 其中同步队列通过 前驱结点和后继结点连接的是同步队列，nextWater连接的是等待队列，两者并不冲突 

### 同步队列

- 一个Node串组成的是一个CLH队列(以下记为同步队列)，这个同步队列是一个FIFO的双向队列

- 当线程获取同步状态失败时，同步器就会把当前线程信息构造成一个Node，并加入同步对队列

- ![同步队列](D:\Software\idea\ideafile\JVMDome\notes\src\多线程\5，Java中的锁\同步队列.png)

- ~~~java
  public class TestLock01 {
      public static void main(String[] args) {
          //声明一个可重入锁
          Lock lock = new ReentrantLock();
          //使用这个Condition对这个锁进行相应的操作
          Condition condition = lock.newCondition();
          IntStream.range(0,10).forEach(value ->{
              new Thread(() -> {
                  lock.lock();
                  try {
                      condition.await(10, TimeUnit.MILLISECONDS);
                      System.out.println("拿到锁");
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  } finally {
                      //在此处设置断点
                      lock.unlock();
                  }
              }, "线程" + value).start();
          });
      }
  }
  ~~~

- ![同步队列01](D:\Software\idea\ideafile\JVMDome\notes\src\多线程\5，Java中的锁\同步队列01.png)

- 这个sync就是同步队列，首结点就是获取同步状态成功的结点，这个线程在释放状态的时候，将会唤醒后继结点，后继结点被唤醒后，会将自已设置为首结点，head指针会指向这个node，这个过程是由获取了同步状态的线程完成的，所以这个操作不需要CAS来完成。

#### enq()

- 当线程被唤醒的时候，他并不会知道为什么会被唤醒，可能是响应中断处理也可能是响应signal()方法，但是无论是被中断唤醒还是被signal唤醒，被唤醒的线程都将离开条件队列，进入到同步队列中

- 添加到同步队列的方法就是enq()，他会把离开等待队列的线程node或是新加入的线程node添加到同步队列的尾部

- ```java
  private Node enq(final Node node) {
      for (;;) {
          //取出当前的尾结点
          Node t = tail;
          //判断有没有进行过初始化
          if (t == null) { 
              //设置一个新的首结点 初始化
              if (compareAndSetHead(new Node()))
                  //首尾结点相同
                  tail = head;
          } else {
              //新的结点的前驱结点就是之前的尾结点
              node.prev = t;
              //CAS判断是否可以设置尾结点
              if (compareAndSetTail(t, node)) {
                  //之前的尾结点的尾结点指正指向新结点
                  t.next = node;
                  //返回的是当前尾结点的前驱结点
                  return t;
              }
          }
      }
  }
  ```

#### addWaiter()

- 为了快速添加一个结点，在enq方法前面还有一个addWaiter方法

- ~~~java
  private Node addWaiter(Node mode) {
      Node node = new Node(Thread.currentThread(), mode);
      Node pred = tail;
      //这个判断后面的内容和enq方法中已初始化部分完全相同，目的是当队列已经存在时直接添加不用再去判断，从而提高速度
      if (pred != null) {
          node.prev = pred;
          if (compareAndSetTail(pred, node)) {
              pred.next = node;
              return node;
          }
      }
      enq(node);
      return node;
  }
  ~~~

- 总结

  ==往同步队列里添加一个新的node==

  1. ==生成一个新的node并获取同步队列的尾结点==
  2. ==判断尾结点是否为null==
     - ==不为null就将新节点的前驱节点设置为原先的尾结点==
     - ==CAS将当新节点设置为尾结点==
     - ==设置成功后将原来尾结点的后继节点设置为新的节点==
     - ==返回==
  3. ==尾结点是为null就执行enq()方法==
     - ==取出当前的尾结点判断是否为null==
     - ==如果为null，则初始化一个新的队列，并设置新的首尾节点==
     - ==如果不为null就将新节点的前驱节点设置为原先的尾结点==
     - ==CAS将当新节点设置为尾结点==
     - ==设置成功后将原来尾结点的后继节点设置为新的节点==
     - ==循环直到返回==

### 等待队列

- 这个等待队列由AQS中的一个内部类ConditionObject控制，又称作条件队列

- ~~~java
  private transient Node firstWaiter;
  private transient Node lastWaiter;
  ~~~

- `TestLock01``代码中使用到了condition.await(10, TimeUnit.MILLISECONDS);` 就会产生一个等待队列，如果没有调用就不会产生这个等待队列

- ![image-20201208194036590](D:\Software\idea\ideafile\JVMDome\notes\src\多线程\5，Java中的锁\等待队列.png)

- ~~~java
  //以下是AQS对Condition接口中await方法的实现
  public final boolean await(long time, TimeUnit unit)
      throws InterruptedException {
      long nanosTimeout = unit.toNanos(time);
      if (Thread.interrupted())
          throw new InterruptedException();
      //这里生成了一个等待队列
      Node node = addConditionWaiter();
       // 释放当前线程所占用的锁，保存当前的锁状态
      int savedState = fullyRelease(node);
      final long deadline = System.nanoTime() + nanosTimeout;
      boolean timedout = false;
      int interruptMode = 0;
      while (!isOnSyncQueue(node)) {
          if (nanosTimeout <= 0L) {
              timedout = transferAfterCancelledWait(node);
              break;
          }
          if (nanosTimeout >= spinForTimeoutThreshold)
              LockSupport.parkNanos(this, nanosTimeout);
          if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
              break;
          nanosTimeout = deadline - System.nanoTime();
      }
      if (acquireQueued(node, savedState) && interruptMode != THROW_IE)
          interruptMode = REINTERRUPT;
      if (node.nextWaiter != null)
          unlinkCancelledWaiters();
      if (interruptMode != 0)
          reportInterruptAfterWait(interruptMode);
      return !timedout;
  }
  ~~~

- ~~~java
  //添加一个新的node向等待队列中
  private Node addConditionWaiter() {
      Node t = lastWaiter;
      // Node.CONDITION的值为-2 表示当前结点处于等待队列中
      if (t != null && t.waitStatus != Node.CONDITION) {
          //当结点不在等待队列中的时候，有可能是被取消或者响应中断了，所以遍历整队列，清除所有已经失效的node
          unlinkCancelledWaiters();
          //清除后，整个队列中的node都是有效的，重新将当前的node指向尾结点
          t = lastWaiter;
      }
      //生成一个新的结点
      Node node = new Node(Thread.currentThread(), Node.CONDITION);
      //t是null说明这个队列就是个空队列
      if (t == null)
          //首结点
          firstWaiter = node;
      else
          //尾结点的后继结点
          t.nextWaiter = node;
      //重置尾结点
      lastWaiter = node;
      return node;
  }
  ~~~
  
- 总结

  ==在await()方法中会生成一个等待队列，其中使用addConditionWaiter()方法来向等待队列中添加新的node==

  ==添加步骤==

  1. ==获取尾结点 lastWaiter==
  2. ==判断尾结点不为null && 判断尾结点状态不为CONDITION (-2)，CONDITION表示该结点正在等待队列中==
     - ==true：遍历队列，清除所有已失效的node，因为node有可能被中断，之后重新指向尾结点==
     - ==到现在为止，等待队列中的所有node都是有效的==
  3. ==生成一个新的node==
  4. ==判断是否为空队列，即尾结点是否为null==
     - ==如果为空，那么就将新的node设置为首结点==
     - ==如果不为空，那么就将新的node设置为之前尾结点的后继结点==
  5. ==尾结点指向新的node，并返回==

### 唤醒线程

#### signalAll()

- 这个方法用于唤醒等待队列中的所有线程，其中涉及两类

  - 一类是调用这个方法的线程，这个线程持有锁，并且准备释放锁
  - 另一类是将要被唤醒的线程，他们要去争夺释放的锁

- ```java
  public final void signalAll() {
      if (!isHeldExclusively())
          throw new IllegalMonitorStateException();
      //判断是不是有个空的队列
      Node first = firstWaiter;
      if (first != null)
          doSignalAll(first);
  }
  ```

- ~~~java
  private void doSignalAll(Node first) {
      //将等待队列清空，虽然在这里清空了但是各个node之间的连接还在
      lastWaiter = firstWaiter = null;
      do {
          //将所有的结点一个一个的拿出来
          Node next = first.nextWaiter;
          //next设置为null
          first.nextWaiter = null;
          transferForSignal(first);
          first = next;
      } while (first != null);
  }
  ~~~

- ~~~java
  final boolean transferForSignal(Node node) {
      // 如果该结点在调用signal方法前已经被取消了，则直接跳过这个结点
      if (!compareAndSetWaitStatus(node, Node.CONDITION, 0))
          return false;
      // 如果该结点在条件队列中正常等待，则利用enq方法将该结点添加至同步队列的尾部,返回的p是该结点的前驱结点
      Node p = enq(node);
      //为了提高性，没有也一样
      int ws = p.waitStatus;
      if (ws > 0 || !compareAndSetWaitStatus(p, ws, Node.SIGNAL))
          LockSupport.unpark(node.thread); 
      return true;
  }
  ~~~
  
- 总结

  ==唤醒全部要清空等待队列==

  1. ==获取等待队列的首结点，并判断是否为null==

  2. ==不为null：lastWaiter 和firstWaiter置为null==

  3. ==通过一个循环将所有的node取出==

  4. ==先获取当前结点的后继结点，暂存变量next==

  5. ==在将当前结点的后继结点设置为null==

  6. ==进入transferForSignal()方法==
- ==判断当前结点是否已经被中断或取消==
  
- ==如果被取消直接返回false==
     - ==如果依然有效，则调用enq()方法添加到同步队列的尾部==
     - ==之后判断原来尾结点状态是否有效，即waitStatus<0 并通过CAS设置原来尾结点的状态为-1==
     - ==如果原来的尾结点已经无效或者设置状态失败那么就直接唤醒当前线程==
     
7. ==一直循环直到所有的节点都被唤醒==

#### signal()

- 这个方法用于唤醒单个线程，其实就是唤醒条件队列中的第一个有效结点

- ~~~java
  private void doSignal(Node first) {
      do {
          // 将firstWaiter指向条件队列队头的下一个结点，更换头结点
          if ( (firstWaiter = first.nextWaiter) == null)
              lastWaiter = null;
          // 将条件队列原来的队头从条件队列中断开，则此时该结点成为一个孤立的结点
          first.nextWaiter = null;
      } while (!transferForSignal(first) && (first = firstWaiter) != null);
  }
  ~~~

## 类结构

![img](D:\Software\idea\ideafile\JVMDome\notes\src\多线程\5，Java中的锁\类结构.png)





## AQS独占锁

### 说明

- 独占锁同一时刻只允许一个线程对线程加锁，如ReentrantLock

### ReentrantLock

#### 父类和接口

- ReentrantLock实现了Lock接口和Serializable接口

- 重写了Lock接口中的6个方法

  - lock：获取锁，拿不到就阻塞
  - tryLock：获取锁，拿不到就不管了，还有一个给定计时
  - 的同名方法
  - lockInterruptibly ：获取锁，如果在拿不到锁被阻塞之后其他线程调用该线程的中断方法interrupt(),那么该线程就会去处理中断，不会死等
  - unlock：释放锁，只有持有锁的才会释放锁
  - newCondition：返回一个Condition实例

- ~~~java
  //调用了内部类sync的方法
  //也有存在返回值的不过大致相同，都是调用的内部类的方法
  public void xxx() {
      sync.xxx();
  }
  ~~~

#### 内部类

> ReentrantLock的实现是需要同步器支持的，在ReentrantLock类中一共有三个内部类

##### Sync

继承了AbstractQueuedSynchronizer，其中定义了获取锁的方法lock()，以及tryLock的方法体调用方法nonfairTryAcquire(),还有释放锁的方法tryRelease()

- lock 

  ~~~java
  abstract void lock();
  ~~~

  只提供了接口，具体由子类来实现

- nonfairTryAcquire

  ```java
  final boolean nonfairTryAcquire(int acquires) {
      //获取线程信息
      final Thread current = Thread.currentThread();
      //获取加锁信息，是否有加过锁
      int c = getState();
      //未加锁情况
      if (c == 0) {
          //CAS设置state
          if (compareAndSetState(0, acquires)) {
              //将此线程设置为独占锁模式
              setExclusiveOwnerThread(current);
              return true;
          }
      }//已经持有独占锁的情况
      else if (current == getExclusiveOwnerThread()) {
          //重入锁重入次数
          int nextc = c + acquires;
          if (nextc < 0) 
              throw new Error("Maximum lock count exceeded");
          //设置新的state
          setState(nextc);
          return true;
      }
      return false;
  }
  ```
  
  尝试CAS加锁，如果加锁失败，比如有其他线程正持有锁，那么就会直接返回false
  
- 总结

  1. ==首先要获取当前线程和当前AQS的状态==
  2. ==如果当前AQS状态为0,说明还没有被加锁==
  3. ==通过CAS设置锁状态，并将线程设置为独占模式==
  4. ==如果AQS状态不为0，说明已经有被上了锁==
  5. ==判断上锁的是不是当前线程==
  6. ==如果是，修改AQS状态，获取重入锁==
  7. ==如果不是返回false==

- tryRelease

  ~~~java
  protected final boolean tryRelease(int releases) {
      //状态
      int c = getState() - releases;
      //这个解锁过程只适用于独占锁，如果当前线程不持有这个独占锁是无法释放锁的
      if (Thread.currentThread() != getExclusiveOwnerThread())
          throw new IllegalMonitorStateException();
      boolean free = false;
      //所有的锁全部释放后state为0
      if (c == 0) {
          free = true;
          //将独占锁线程设置为null
          setExclusiveOwnerThread(null);
      }
     	//同步器修改持锁状态
      setState(c);
      return free;
  }
  ~~~

  直接释放锁，修改同步器的锁状态
  
- 总结

  1. ==因为存在可重入锁。所以在释放锁的时候不能直接把AQS的状态设置为0==
  2. ==需要将当前AQS状态和一次释放的锁的数量作差==
  3. ==如果这个结果为0，才会去修改独占模式的线程设置为null，说明当前资源已被释放==

##### NonfairSync

非公平锁实现类，是Sync的子类

- 对lock方法进行了实现，同时实现了Sync父类AbstractQueuedSynchronizer的方法tryAcquire获取锁

- tryAcquire

  ```java
  protected final boolean tryAcquire(int acquires) {
      return nonfairTryAcquire(acquires);
  }
  ```

  直接调用父类的nonfairTryAcquire方法

- lock

  ```java
  final void lock() {
      //CAS设置state值 如果设置成功就设置单前线程为独占模式获得锁
      if (compareAndSetState(0, 1))
          setExclusiveOwnerThread(Thread.currentThread());
      else
          //CAS设置失败，进入该方法
          acquire(1);
  }
  ```

  ```java
  public final void acquire(int arg) {
      //两个判断条件
      //!tryAcquire(arg) 要求CAS获取锁失败
      //调用addWaiter方法添加当前的独占模式的结点到同步队列，之后调用acquireQueued方法，如果线程获取了锁但是被中断了执行selfInterrupt
      if (!tryAcquire(arg) &&
          acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
          selfInterrupt();
  }
  ```

  ```java
  final boolean acquireQueued(final Node node, int arg) {
      //线程是否失效，默认无效
      boolean failed = true;
      try {
          //是否中断
          boolean interrupted = false;
          //这里会不断循环，直到p == head && tryAcquire(arg) 为true
          for (;;) {
              //predecessor用于返回上一个结点，如果不存在返回空指针异常
              final Node p = node.predecessor();
              //p是node.prev 判断是不是首结点如果是就再一次尝试获取锁
              if (p == head && tryAcquire(arg)) {
                  //获取锁成功后设置首结点为node
                  setHead(node);
                  //这样之后同步队列中只有一个头结点 即当前线程node
                  p.next = null; 
                  failed = false;
                  return interrupted;
              }
              //当前线程node的状态是-1时才会有后续的判断条件
              //如果线程node状态为-1 并且线程处于被中断的情况则设置中断标志为true
              if (shouldParkAfterFailedAcquire(p, node) &&
                  parkAndCheckInterrupt())
                  interrupted = true;
          }
      } finally {
          //如果异常的话，置线程状态为无效
          if (failed)
              //主要是将线程状态设置为CANCELLED 为1，说明当前线程已经失效
              cancelAcquire(node);
      }
  }
  ```

  shouldParkAfterFailedAcquire的源码部分，用于判断node是否应该被阻塞，

  ~~~java
  private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
      //获取waitStatus node的状态
      int ws = pred.waitStatus;
      //后面有线程node等待当前线程node去唤醒，返回true
      if (ws == Node.SIGNAL)
          return true;
      //大于0 说明当前线程无效
      //循环获取前驱结点 知道某个结点有效位置，将这个有效结点的后继结点设置为参数中的node
      if (ws > 0) {
          do {
              node.prev = pred = pred.prev;
          } while (pred.waitStatus > 0);
          pred.next = node;
      } else {
          //CAS设置当前结点的状态
          compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
      }
      //设置成功后会返回false
      return false;
  }
  ~~~

  parkAndCheckInterrupt的源码部分

  ```java
  private final boolean parkAndCheckInterrupt() {
      //禁用这个线程 相当于wait 可以使用unpark解除这个禁制
      LockSupport.park(this);
      //查看是否中断
      return Thread.interrupted();
  }
  ```

  selfInterrupt的源码部分

  ~~~java
  //执行当前线程的中断方法
  static void selfInterrupt() {
      Thread.currentThread().interrupt();
  }
  ~~~

- 整体有点复杂。。。

  1. ==通过CAS修改state，成功则获取到锁==
  2. ==失败则进入acquire()方法不断去获取锁==
    1. ==刚进入方法时仍然要通过CAS去设置state，成功则获取到锁，如果失败则将本线程添加到同步队列中==
    2. ==addWaiter()会创建一个新的node，并把它放入同步队列尾部，返回值依然是这个node==
    3. ==以新的node为参数进入acquireQueued()方法，判断线程是否应该被中断==
    4. ==在这个方法中有一个循环，直到当前节点的前驱为首节点，并且获取到锁才会终止==
    5. ==每一次循环中都会进入shouldParkAfterFailedAcquire()方法==
    6. ==这个方法有两个参数，(当前线程前驱node，当前线程node)，如果前驱node的状态为-1，直接返回true，前驱结束后会唤醒后面的线程==
    7. ==如果前驱节点状态>0,说明前驱节点已失效，通过循环找到未失效的前驱，设置为当前节点的前驱==
    8. ==在步骤2.4中的循环的下一次设置前驱状态为-1，并返回false==
    9. ==如果shouldParkAfterFailedAcquire()返回的true，还要进入parkAndCheckInterrupt()方法将当前线程禁用，休眠，等待，返回线程中断标志==
  3. ==如果中断标志位false，则线程就会不断的阻塞，获取锁，直到成功拿到资源==
  4. ==如果中断标志位true。那么线程依然不断的获取锁，不过成功后会进入selfInterrupt()方法，去执行中断处理==

##### FairSync

公平锁实现类，是Sync的子类

- lock的执行方法流程与非公平锁相同

- 真正的区别在tryAcquire上

  ~~~java
  protected final boolean tryAcquire(int acquires) {
      //获取当前的线程信息
      final Thread current = Thread.currentThread();
      //锁状态
      int c = getState();
      //没有上锁的话
      if (c == 0) {
          //hasQueuedPredecessors返回true的话，取反为false那么这整个方法就直接返回false，获取失败
          //如果hasQueuedPredecessors返回false的话，就会继续通过CAS获取锁，失败返回false，成功后将当前线程设置为独占锁模式返回true，获取锁成功
          if (!hasQueuedPredecessors() &&
              compareAndSetState(0, acquires)) {
              setExclusiveOwnerThread(current);
              return true;
          }
      }
      //如果线程已经上了锁，并且是独占锁的话
      else if (current == getExclusiveOwnerThread()) {
          //直接加上状态值，成功获取可重入锁
          int nextc = c + acquires;
          if (nextc < 0)
              throw new Error("Maximum lock count exceeded");
          //set新的状态值
          setState(nextc);
          return true;
      }
      return false;
  }
  ~~~

  hasQueuedPredecessors的源码展示

  ~~~java
  public final boolean hasQueuedPredecessors() {
      Node t = tail; 
      Node h = head;
      Node s;
      //如果头结点和尾结点都是null 的话返回false，此时队列为空，当队列中的head和tail指向同一个node时也返回false，因为这个node时虚拟结点，也可以认定为队列为空
      //如果 h != t是true，那么说明该队列中的头结点和尾结点是不同的，判断首结点的后继结点 h.next 是否为null
      //如果s = h.next是null，说明有一个线程正在执行enq()方法，但还没有赋予尾结点值，这个正在执行的线程并不是本线程，也就不需要判断后面的thread，直接返回true，结果就是 true && true = true
      //如果s = h.next==null为false，则就要判断s结点的线程和当前线程是否是同一个线程
      //如果s.thread != Thread.currentThread() 为true则说明首结点的后继结点的线程不是当前线程，当前线程应该去排队，不能去获取锁，整体应该返回 true
      //如果s.thread != Thread.currentThread() 为false则下一个线程就是当前线程，可以通过CAS去尝试获取锁，返回false
      //首先会判断是否存在后继结点，如果存在，则判断头结点的后继是否为null，并赋值给s变量，租后判断s的线程信息是否是当前线程，因为是公平锁所以只能是头结点的后继来获取锁
    return h != t &&
          ((s = h.next) == null || s.thread != Thread.currentThread());
  }
  ~~~
  
- 稍稍总结。。。公平锁的获取锁

  1. ==和nonfairTryAcquire不同的是当资源还没有被上锁时，即state为0时会多一步判断hasQueuedPredecessors()==
  2. ==如果hasQueuedPredecessors()返回为false并且CAS修改state成功才会获取锁成功==
  3. ==因为FairSync是公平锁，所以上锁是必须判断当前节点在同步队列中的位置==
  4. ==如果同步队列中只有一个虚拟节点，即head = tail，那么返回false，当前线程可以获取锁==
  5. ==如果head != tail,但是head.next = null，说明有一个线程正在执行enq()方法,返回true当前线程去排队==
  6. ==如果head != tail，head.next != null,s.thread != Thread.currentThread()，当前线程不是head的后继，返回true，当前线程去排队==
  7. ==如果head != tail，head.next != null,s.thread = Thread.currentThread()，当前线程是head的后继，返回false，当前线程通过CAS获取锁==

#### 锁的释放

- 锁的释放使用的方法是unlock()

  ```java
  public void unlock() {
      sync.release(1);
  }
  ```

- release()方法存在于Sync的父类AQS中

  ~~~java
  public final boolean release(int arg) {
      if (tryRelease(arg)) {
          Node h = head;
          if (h != null && h.waitStatus != 0)
              unparkSuccessor(h);
          return true;
      }
      return false;
  }
  ~~~

- 释放锁后，当前线程会将后继节点中的线程唤醒，在parkAndCheckInterrupt()中线程被阻塞使用的是park()方法，所以在唤醒的时候要使用unpark()方法

- 注意:这里是指定唤醒，所以不应该使用signalAll()和signal()方法

- 唤醒的时候，会获取有效的后继节点，即获取到后继节点的时候会判断后继节点的waitStatus是否大于0，如果大于0就继续找后继，直到小于0为止，这个小于0的节点就是要唤醒的节点

#### 其他方法

- 在ReentrantLock中的构造器默认是生成一个非公平的锁，也可以咋调用构造器的时候指定，参数为一个布尔类型的值，true为公平锁，false为非公平锁。
- trylock的实现是子类Sync中的nonfairTryAcquire()方法，获取不到锁就直接返回

## AQS共享锁

### 综述

- 在AQS的子类Node中存在两个全局变量，SHARED和EXCLUSIVE，用于标明线程是独占还是共享

  ```java
  static final Node SHARED = new Node();
  static final Node EXCLUSIVE = null;
  ```

- 默认就是共享锁，如果想要其成为独占锁，只需要设置一下EXCLUSIVE的值即可

- 设置后，独占锁模式下的线程在获取锁的时候就会自动去判断这个独占标志线程和当前线程是否相同

### CountDownLatch

#### 简单使用

- ```java
  public static void main(String[] args) {
      CountDownLatch latch = new CountDownLatch(5);
      IntStream.of(0,1,2,3,4).forEach(value ->{
          new Thread(() -> {
              try {
  				System.out.println(Thread.currentThread().getName());
                  latch.countDown();
              } catch (Throwable e) {
                  e.printStackTrace();
              }
          }, "线程"+value).start();
      });
      try {
          // 10个线程countDown()都执行之后才会释放当前线程,程序才能继续往后执行
          latch.await();
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      System.out.println("结束");
  }
  ```

- 从结果来看，CountDownLatch会阻塞主线程，当所有的线程执行完了之后才会执行结束，也就是await()方法后面的代码

- 这里的latch也就是CountDownLatch实例相当于一个屏障，只有指定数目的线程执行完成后才会进行后续操作

#### 构造器

- ```java
  public CountDownLatch(int count) {
      if (count < 0) throw new IllegalArgumentException("count < 0");
      this.sync = new Sync(count);
  }
  ```

- 直接调用了内部类Sync的构造器方法

#### 内部类

##### Sync

- Sync继承了AQS

- ```java
  Sync(int count) {
      setState(count);
  }
  ```

- setState()是直接调用了AQS中的方法，其修改的值就是临界区上锁的标记

- 还重写了AQS的tryAcquireShared方法和tryReleaseShared方法，用来在共享模式下获取锁和释放锁

- tryAcquireShared()

  ```java
  protected int tryAcquireShared(int acquires) {
      //状态如果是0就返回1，表示还没有线程加锁或者所有线程已经到达
      //状态如果不是0就返回-1，代表还有线程正在使用资源
      return (getState() == 0) ? 1 : -1;
  }
  ```

- tryReleaseShared()

  ```java
  protected boolean tryReleaseShared(int releases) {
      for (;;) {
          //获取当前上锁状态
          int c = getState();
          //等于0直接返回false，没有要释放的锁了
          if (c == 0)
              return false;
          //自减1，代表释放了一个锁
          int nextc = c-1;
          //通过CAS设置，成功则返回当前是否上锁，失败则继续循环
          if (compareAndSetState(c, nextc))
              return nextc == 0;
      }
  }
  ```

#### countDown()

- countDown()方法用于对上锁状态的自减

  ```java
  public void countDown() {
      //调用了AQS的releaseShared()方法
      sync.releaseShared()方法(1);
  }
  ```

- ```java
  public final boolean releaseShared(int arg) {
      //如果释放了锁后发现所有的锁已经被释放
      if (tryReleaseShared(arg)) {
          doReleaseShared();
          return true;
      }
      return false;
  }
  ```

- ```java
  private void doReleaseShared() {
      for (;;) {
          Node h = head;
          if (h != null && h != tail) {
              int ws = h.waitStatus;
              if (ws == Node.SIGNAL) {
                  if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                      continue;            
                  unparkSuccessor(h);
              }
              else if (ws == 0 &&
                       !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                  continue;               
          }
          if (h == head)                   
              break;
      }
  }
  ```

- 总结 ？？？

  1. ==通过tryReleaseShared()释放一个锁，如果释放了锁后发现所有的锁已经被释放则进入doReleaseShared()方法==
     1. ==在doReleaseShared方法中，会判断当前队列是否为空，即只存在头结点和为null的情况==
     2. ==如果为空，则不需要唤醒节点直接执行`if(h == head) break;`==
     3. ==如果不为空，则判断当前node状态，如果是-1，则说明后续有node需要被唤醒，通过CAS修改-1为0，失败的话，说明有其他的node先于本node去唤醒了，无论如何后续node一定会被唤醒，那么直接 `continue;`，修改成功则亲自去唤醒后续node `unparkSuccessor(h);`==
     4. ==如果node状态为0，说明其后继节点已经被唤醒，要将头结点设置为-3作为中间状态== 
  2. ==因为所有的锁已经被释放了，达到了CountDownLatch的要求，所以要唤醒后续等待的线程。==
  3. ==环检测到head没有变化时就会退出循环。注意，head变化一定是因为：后续线程被唤醒，之后它成功获取锁，然后setHead设置了新head==

#### await()

- 导致当前线程等待，直到锁存器递减至零为止，除非该线程被中断。也就是等待CountDownLatch变为0，再执行，CountDownLatch变为0后会调用doReleaseShared唤醒当期线程

- ```java
  public void await() throws InterruptedException {
      sync.acquireSharedInterruptibly(1);
  }
  ```

- 直接调用内部类的acquireSharedInterruptibly(),实现在父类中

- ```java
  public final void acquireSharedInterruptibly(int arg)
      throws InterruptedException {
      //执行前提，线程不能被中断
      if (Thread.interrupted())
          throw new InterruptedException();
      //tryAcquireShared(arg) < 0说明资源已经上了锁，需要进行阻塞，等待被唤醒
      if (tryAcquireShared(arg) < 0)
          //共享模式下获取，可参照acquireQueued，生成一个节点添加到等待队列尾部
          doAcquireSharedInterruptibly(arg);
  }
  ```

- ```java
  private void doAcquireSharedInterruptibly(int arg)
      throws InterruptedException {
      //生成共享模式的node
      final Node node = addWaiter(Node.SHARED);
      boolean failed = true;
      try {
          for (;;) {
              final Node p = node.predecessor();
              if (p == head) {
                  int r = tryAcquireShared(arg);
                  //大于0说明可以继续执行，不用再阻塞了
                  if (r >= 0) {
                      //离开等待队列
                      setHeadAndPropagate(node, r);
                      p.next = null; 
                      failed = false;
                      return;
                  }
              }
              //查看是否需要park
              if (shouldParkAfterFailedAcquire(p, node) &&
                  parkAndCheckInterrupt())
                  throw new InterruptedException();
          }
      } finally {
          if (failed)
              cancelAcquire(node);
      }
  }
  ```

  和acquireQueued不同的是如果发生了中断，则会直接抛出InterruptedException

#### 总结

- ==CountDownLatch创建对象的时候会将参数设置为state的值，每次调用countDown(),该值会减1，直到减为0，减为0后会尝试唤醒后续的线程node，如果存在的话==
- ==要想达到所有规定线程完成才继续向下进行任务的效果则必须执行await()方法，可以设置时间==
- ==await()会在state不为0的时候生成一个node节点加入到同步队列中，不断尝试去获取锁，直到其前驱节点的状态为-1，该线程进入等待状态，不再CAS消耗资源==

### CyclicBarrier

#### 简单使用

- ```java
  public static void main(String[] args) {
      CyclicBarrier barrier = new CyclicBarrier(5);
      IntStream.of(0, 1, 2, 3, 4).forEach(value -> {
          new Thread(() -> {
              try {
                  System.out.println(Thread.currentThread().getName() + "到达");
                  barrier.await();
                  System.out.println(Thread.currentThread().getName() + "结束");
              } catch (InterruptedException | BrokenBarrierException e) {
                  e.printStackTrace();
              }
          }, "线程" + value).start();
      });
  }
  ```

- CyclicBarrier实例可以看成是一个障碍，这个障碍要求当一个线程到达时会阻塞这个线程，直到规定数目的线程全部到达该障碍位置才会将这些阻塞的线程一同往下执行

- 注意：这里的各个线程没有执行完，是执行到某一个点去等待，等到指定数目的线程都执行到这个点的时候才会全部唤醒，继续往下执行，执行的时候依然是每一个线程执行自己的任务

- CyclicBarrier可以通过重置计数器来重新使用

#### 构造器

- CyclicBarrier的构造器有两个

- ~~~java
  public CyclicBarrier(int parties) {
      this(parties, null);
  }
  ~~~

  parties是拦截线程的个数，所有线程达到要求后不做其他处理，需要程序员自己进行处理

  ~~~java
  public static void main(String[] args) {
      CyclicBarrier barrier = new CyclicBarrier(5);
      IntStream.of(0, 1, 2, 3, 4).forEach(value -> {
          new Thread(() -> {
              try {
                  System.out.println(Thread.currentThread().getName() + "到达");
                  barrier.await();
                    System.out.println(Thread.currentThread().getName() + "结束");
              } catch (InterruptedException | BrokenBarrierException e) {
                  e.printStackTrace();
              }
          }, "线程" + value).start();
      });
  }
  ~~~

  

- ~~~java
  public CyclicBarrier(int parties, Runnable barrierAction) {
      if (parties <= 0) throw new IllegalArgumentException();
      this.parties = parties;
      this.count = parties;
      this.barrierCommand = barrierAction;
  }
  ~~~

  barrierAction是一个线程的实例，所有线程达到要求后需要由最后一个线程执行

  ~~~java
  public static void main(String[] args) {
      CyclicBarrier barrier = new CyclicBarrier(5,() -> {
          System.out.println(Thread.currentThread().getName());
      });
      IntStream.of(0, 1, 2, 3, 4).forEach(value -> {
          new Thread(() -> {
              try {
                  System.out.println(Thread.currentThread().getName() + "到达");
                  barrier.await();
              } catch (InterruptedException | BrokenBarrierException e) {
                  e.printStackTrace();
              }
          }, "线程" + value).start();
      });
  }
  ~~~


#### 内部类

- ```java
  private static class Generation {
      boolean broken = false;
  }
  ```

  重置计数器

#### await()

- 用于进入等待状态，表示线程已经到达了屏障标志点

- 如果还有线程没有到达的话，会park(),等待被唤醒

- 最后一个线程到达，被其他线程中断，其他线程调用了reset(),其他线程等待屏障时超时，则该线程会被唤醒

- ~~~java
  public int await() throws InterruptedException, BrokenBarrierException {
      try {
          //直接调用都wait()方法
          return dowait(false, 0L);
      } catch (TimeoutException toe) {
          throw new Error(toe); // cannot happen
      }
  }
  ~~~

- ~~~java
  private int dowait(boolean timed, long nanos)
      throws InterruptedException, BrokenBarrierException,
  TimeoutException {
      final ReentrantLock lock = this.lock;
      //获取锁
      lock.lock();
      try {
          //屏障是否破损？
          final Generation g = generation;
  		//屏障破损异常
          if (g.broken)
              throw new BrokenBarrierException();
  
          //如果线程被中断了
          //调用了await的线程被中断以为着剩下的线程无法满足屏障要求
          //需要声明屏障破损，并唤醒正在等待的线程
          if (Thread.interrupted()) {
              breakBarrier();
              throw new InterruptedException();
          }
  
          //count 屏障还需要的线程数量
          int index = --count;
          //为 0 说明计数器已归零
          if (index == 0) {  
              boolean ranAction = false;
              try {
                  //根据构造器的不同，有不同的执行
                  final Runnable command = barrierCommand;
                  if (command != null)
                      command.run();
                  ranAction = true;
                  //唤醒正在等待的线程，调用的是signalAll()
                  nextGeneration();
                  return 0;
              } finally {
                  //如果上述代码出现异常导致ranAction没有修改则直接声明屏障破损，并唤醒正在等待的线程
                  if (!ranAction)
                      breakBarrier();
              }
          }
          for (;;) {
              try {
                  //查看有没有时间限制
                  if (!timed)
                      //没有时间限制，直接调用await()方法，实际上调用的是park方法，表示当前线程进入等待，直到被唤醒，调用后会释放当前的锁
                      trip.await();
                  else if (nanos > 0L)
                      //如果有时间限制，最终也是调用park方法
                      nanos = trip.awaitNanos(nanos);
              } catch (InterruptedException ie) {
                  //在执行或等待过程中被中断
                  //判断generation实例是否有关变化，如果没有再判断屏障是否被损坏，如果没有被损坏，那么声明屏障已经被破坏，等待下一次循环
                  if (g == generation && ! g.broken) {
                      breakBarrier();
                      throw ie;
                  } else {
                      //如果已经声明了损坏则直接中断
                      Thread.currentThread().interrupt();
                  }
              }
              if (g.broken)
                  throw new BrokenBarrierException();
              if (g != generation)
                  return index;
              if (timed && nanos <= 0L) {
                  breakBarrier();
                  throw new TimeoutException();
              }
          }
      } finally {
          lock.unlock();
      }
  }
  ~~~

- breakBarrier的源码部分

  ```java
  private void breakBarrier() {
      //将子类中的屏障损坏标记设置为true，声明屏障已经损坏
      generation.broken = true;
      count = parties;
      //唤醒所有已经在等待的线程
      trip.signalAll();
  }
  ```

- nextGeneration的源码部分

  ```java
  private void nextGeneration() {
      // signal completion of last generation
      trip.signalAll();
      // set up next generation
      count = parties;
      generation = new Generation();
  }
  ```
  
- 总结。。。

  1. ==线程调用await()方法。在await()方法中调用dowait()方法==
  2. ==dowait()方法中会使用lock上锁，确保在一个时间点只有一个线程在调用==
  3. ==获取当前屏障状态实例，判断屏障是否破损，如果破损则异常，唤醒所有线程==
  4. ==判断是否被中断了，如果被中断，调用breakBarrier()方法声明屏障破损，报异常，唤醒所有线程==
  5. ==--count,屏障阻断的线程数--==
  6. ==如果 --count后为0 获取构造器上的线程参数，不为null就运行这个方法。==
  7. ==调用nextGeneration()方法重置屏障，唤醒所有线程，当前方法return不再向下执行，否则会被阻塞==
  8. ==之后将此线程调用await()阻塞，调用该方法后会释放刚才获取到的锁，方便下一个线程获取锁==
  9. ==被唤醒后会判断屏障状态实例是否发生了变化，如果变化了则会直接返回当前剩余的屏障节点数==

#### reset()

- reset()可以强制结束当前线程的等待，但是会使已经在等待的线程抛出异常，BrokenBarrierException屏障损坏异常

- ```java
  public void reset() {
      //获取锁
      final ReentrantLock lock = this.lock;
      lock.lock();
      try {
          //这里会使屏障损坏
          breakBarrier();   //     break the current generation
          //重置计数器，也就是屏障
          nextGeneration(); // start a new generation
      } finally {
          lock.unlock();
      }
  }
  ```

- 直接调用breakBarrier()方法使屏障损坏,然后重置计数器

#### 总结

- ==线程执行后在某一个时间点调用CyclicBarrier实例的await方法可以进入等待状态，当CyclicBarrier实例屏障数目达到要求时会执行预先规定的任务，如果有的话，这个任务需要在构造器中声明==
- ==等待时会生成node，进入同步和等待队列，同时会调用线程的park方法让线程等待==
- ==在这个过程中如果有的线程调用了reset()方法，会使屏障损坏，在屏障损坏之后会调用signalAll唤醒所有正在等待的线程，线程被唤醒后探查到屏障破损会抛出异常，这个过程是加锁的，所有等待的异常都处理完毕后会重置屏障==
- ==如果这个过程中没有调用reset()，则会等屏障计数器计满，进行所有线程的唤醒，再重置屏障==

### Semaphore

#### 简单使用

- ~~~java
  public static void main(String[] args) {
      Semaphore semaphore=new Semaphore(9);
      IntStream.of(0,1,2,3,4,5,6,7,8,9).forEach(value -> {
          new Thread(()->{
              try {
                  semaphore.acquire();
                  System.out.println(Thread.currentThread().getName()+"开始");
                  TimeUnit.SECONDS.sleep(3);
                  System.out.println(Thread.currentThread().getName()+"结束");
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }finally {
                  semaphore.release();
              }
          },"线程"+value).start();
      });
  }
  ~~~

- Semaphore也是一个线程同步的辅助类，可以维护当前访问自身的线程个数，并提供了同步机制。使用Semaphore可以控制同时访问资源的线程个数

#### 构造器

- ```java
  public Semaphore(int permits) {
      sync = new NonfairSync(permits);
  }
  ```

- 构造器默认是使用的非公平锁，也可以指定使用公平锁，这和ReentrantLock实现方式差不多

- ~~~java
  public Semaphore(int permits, boolean fair) {
      sync = fair ? new FairSync(permits) : new NonfairSync(permits);
  }
  ~~~

#### 内部类

##### Sync

- Sync继承了AQS

- ~~~java
  Sync(int permits) {
      setState(permits);
  }
  ~~~


  构造器实际上是将程序员指定的线程数赋值给AQS中的上锁状态

- 其中提供了非公平锁获取共享锁的方法

  ~~~java
  final int nonfairTryAcquireShared(int acquires) {
      //循环获取锁
      for (;;) {
          //available当前的上锁状态
          int available = getState();
          //最终的上锁状态
          int remaining = available - acquires;
          //在最终上锁状态大于0的情况下CAS获取锁，知道最终上锁状态小于0，或者获取到锁
          if (remaining < 0 ||
              compareAndSetState(available, remaining))
              //最终上锁状态
              return remaining;
      }
  }
  ~~~


- 释放共享锁的方法

  ~~~java
  protected final boolean tryReleaseShared(int releases) {
      //循环释放锁
      for (;;) {
          //当前上锁状态
          int current = getState();
          //最终上锁状态
          int next = current + releases;
          //releases不能为负
          if (next < current) 
              throw new Error("Maximum permit count exceeded");
          //CAS 释放锁
          if (compareAndSetState(current, next))
              return true;
      }
  }
  ~~~


##### NonfairSync

- NonfairSync继承了Sync
- 在非公平锁中获取锁直接使用的是Sync提供的方法

##### FairSync

- FairSync继承了Sync

- 在公平锁中需要自己重写获取锁的方法

  ```java
  protected int tryAcquireShared(int acquires) {
      for (;;) {
          if (hasQueuedPredecessors())
              return -1;
          int available = getState();
          int remaining = available - acquires;
          if (remaining < 0 ||
              compareAndSetState(available, remaining))
              return remaining;
      }
  }
  ```

  相较于Sync提供的方法只是所处一步判断 hasQueuedPredecessors()

  hasQueuedPredecessors()会判断等待对列是否为空，并且判断等待队列中是否有节点在等待。

#### acquire()

- Semaphore终究还是一个锁，不过和ReentrantLock上锁的方式不同，ReentrantLock是正对一个线程的可重入锁，属于独占锁，而Semaphore是针对指定数目的锁，属于共享锁

- 但是对于超出数目的线程，是不允许获取到锁的，但是它仍然可以获取锁，所以Semaphore也会分为公平锁和非公平锁

- acquire()实现的Semaphore中锁的获取

  ~~~java
  public void acquire() throws InterruptedException {
      sync.acquireSharedInterruptibly(1);
  }
  ~~~

- ~~~java
  public final void acquireSharedInterruptibly(int arg)
      throws InterruptedException {
      if (Thread.interrupted())
          throw new InterruptedException();
      if (tryAcquireShared(arg) < 0)
          doAcquireSharedInterruptibly(arg);
  }
  ~~~

  ~~~java
      private void doAcquireSharedInterruptibly(int arg)
          throws InterruptedException {
          //node时SHARED模式的代表是共享锁
          final Node node = addWaiter(Node.SHARED);
          boolean failed = true;
          try {
              for (;;) {
                  final Node p = node.predecessor();
                  if (p == head) {
                      int r = tryAcquireShared(arg);
                      if (r >= 0) {
                          setHeadAndPropagate(node, r);
                          p.next = null; // help GC
                          failed = false;
                          return;
                      }
                  }
                  if (shouldParkAfterFailedAcquire(p, node) &&
                      parkAndCheckInterrupt())
                      throw new InterruptedException();
              }
          } finally {
              if (failed)
                  cancelAcquire(node);
          }
      }
  ~~~

- ==针对非公平锁这里的tryAcquireShared()方法调用的是nonfairTryAcquireShared()方法，对于公平锁这里的tryAcquireShared()调用的是FairSync自己重写的==

  1. ==总之是要获取锁，并会返回剩余的可容纳线程数.==
  2. ==如果没有可容纳空间了就执行doAcquireSharedInterruptibly()方法，在该方法中会生成一个node，并加入同步队列.==
  3. ==只后在一个循环中，会获取当前node的前驱，如果前驱是首节点，那么就会不断的获取锁，也就是执行tryAcquireShared()方法==
  4. ==如果获取成功，且获取后容量还有剩余，那么就把当前node设置成首节点，原先首节点的后继改为null,return==
  5. ==如果获取失败则继续下一次循环。==
  6. ==在每次循环中还会判断是否需要阻塞当前线程，如果当前线程的前驱节点状态为-1，则park当前线程，等待被unpark，如果线程发生了中断，则抛出异常==

#### release()

- ```java
  public void release() {
      sync.releaseShared(1);
  }
  ```

  ```java
  public final boolean releaseShared(int arg) {
      //释放锁成功后会进入doReleaseShared()方法
      if (tryReleaseShared(arg)) {
          doReleaseShared();
          return true;
      }
      return false;
  }
  ```

  ```java
  private void doReleaseShared() {
      for (;;) {
          Node h = head;
          if (h != null && h != tail) {
              int ws = h.waitStatus;
              if (ws == Node.SIGNAL) {
                  if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                      continue;            
                  unparkSuccessor(h);
              }
              else if (ws == 0 && !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                  continue;                
          }
          if (h == head)                   
              break;
      }
  }
  ```

- 总结

  1. 当线程调用release(),的时候会将当前线程的在总的容量中踢出。
  2. 如果成功释放锁，则会查看等待队列，如果等待队列中存在node，则去唤醒
  3. 不管当前的state中还可以容纳多少线程，当一个线程被释放后就一定会去唤醒等待中的线程。



