Java内存模型

> java内存模型的基础

1. 并发编程模型的两个关键问题
   - 线程之间如何通信和线程之间如何同步
   - 通信是指线程之间以何种机制来交换信息，在命令式编程中，线程之间的通信机制有两种：共享内存和消息传递
   - 同步是指程序中用于控制不同线程间操作发生相对顺序的机制。
   - java采用的是共享内存模型，Java线程之间的通信总是隐式进行
2. java内存模型的抽象结构
   - 在java中，所有实例域，静态域和数组元素都存储在堆内存中，堆内存在线程之间共享，
   - 局部变量，方法定义参数和异常处理参数不会在线程之间共享，它们不会有内存可见性问题，也不受内存模型的影响
   - java线程之间的通信是由java内存模型即JMM 控制，他决定了一个线程对共享变量的写入何时对另一个线程可见
   - JMM定义了线程和主内存之间的抽象关系：线程之间的共享变量存储在主内存中，每个线程都有一个私有的本地内存，本地线程中存储了该线程以读/写共享变量的副本
   - 一个线程修改了本地内存中的值，刷新到主内存中，另一个线程去主内存中获取这个值，并拿到自己的本地内存中，这样就完成了两个线程之间通过主内存这个媒介进行通信
3. 从源代码到指令序列的重排序
   - 编译器优化的重排序，编译器在不改变单线程程序语义的前提下可以重新安排语句的执行顺序
   - 指令级并行的重排序，如果不存在数据依赖性，处理器可以改变语句对应机器指令的执行顺序
   - 内存系统的重排序。
4. happens-before
   - 在 JMM 中，如果一个操作执行的结果需要对另一个操作可见，那么这两个操作之间必 须要存在 happens-before 关系。
   - happens-before规则如下：
     - 程序顺序规则：一个线程中的每个操作，happens-before于改线程中的任意后续操作，编译器不一定按照程序的推演顺序编译，但是编译器保证运行结果一定和程序推演的结果相同
     - 监视器锁规则：对一个锁的解锁，happens-before于随后对这个锁的加锁。对于一个已被持有的锁，解锁一定在加锁之前
     - volatile变量规则：对一个volatile域的写，happens-before于任意后续对这个volatile域的读。就是如果一个线程先去写一个volatile变量，然后一个线程去读这个变量，那么这个写操作的结果一定对读的这个线程可见。
     - 传递性：如果A happens-before B 且 B happens-before C 那么 A happens-before C
   - 注意 两个操作之间具有happens-before关系，并不意味着前一个操作必须要在后一个操作之前执行，happens-before仅仅要求前一个操作对后一个操作可见，且 前一个操作按顺序排在第二个操作之前

> 顺序一致性

1. 数据竞争与顺序一致性
   - 如果一个多线程程序能正确同步，这个程序将是一个没有数据竞争的程序
   - 程序的顺序一致性：程序的执行结果与该程序在顺序一致性内存模型中的执行结果相同
2. 顺序一致性内存模型
   - 一个线程中的所有操作必须按照程序的顺序来执行
   - 所有线程都只能看到一个单一的操作执行顺序，在顺序一致性内存模型中，每个操作都必须原子执行且立刻对所有线程可见
   - 顺序一致性内存模型的视图在概念上，顺序一致性模型有一个单一的全局内存，这 个内存通过一个左右摆动的开关可以连接到任意一个线程，同时每一个线程必须按照程 序的顺序来执行内存读/写操作。
   - 未同步程序在顺序一致性模型中虽然整体执行顺序是无序的，但所有线程都只能看 到一个一致的整体执行顺序。
   - JMM 不保证未同步程序的执行结果与该程序在顺序一致性模型中的执行结果一致

> volatile的内存语义

- volatile的特性

  - 可见性。对一个 volatile 变量的读，其他所有线程总是能看到对这个 volatile 变量 最后的写入。
  - 原子性：对任意单个 volatile 变量的读/写具有原子性，但类似于 volatile++这种 复合操作不具有原子性。

- volatile 写和锁的释放有相同的内存语义；volatile 读与锁的获取有相同的内存语义。

- A 线程写一个 volatile 变量后，B 线程读同一个 volatile 变量。A 线程在写 volatile 变量之前所有可见的共享变量，在 B 线程读同一个 volatile 变量后，将立即变得 对 B 线程可见。

- ~~~java
  	    volatile  int number = 0;
   
       public void setNumber() {
           this.number = 1;
       }
   
       public static void main(String[] args) {
          TEst02 est02 = new TEst02();
          new Thread(() -> {
              System.out.println(Thread.currentThread().getName() + "线程启动");
              try {
                  TimeUnit.SECONDS.sleep(3);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              est02.setNumber();
              System.out.println(Thread.currentThread().getName() + "修改了值" + est02.number);
          }, "A").start();
  
          new Thread(() -> {
              while (est02.number == 0) {
              }
  
              System.out.println(Thread.currentThread().getName() + "结束");
          },"B").start();
  
      }
  ~~~
  
- volatile 写的内存语义:当写一个 volatile 变量时，JMM 会把该线程对应的本地 内存中的共享变量值刷新到主内存。

- volatile 读的内存语义:当读一个 volatile 变量时，JMM 会把该线程对应的本地内存置为无效。线程接下来 将从主内存中读取共享变量。

- 当线程释放锁时，JMM 会把该线程对应的本地内存中的共享变量刷新到主内存中

- 线程A释放一个锁，实质上是线程A向接下来将要获取这个锁的所有线程发出了消息，消息可能是线程A对共享变量所做的修改

- 线程B获取一个锁，实质上是线程B接受了之前某个线程发出的消息，可能是之前线程在释放锁的时候对共享变量的修改

- 线程A释放锁，随后线程B获取这个锁，这歌过程实质上是线程A通过主内存向线程B发送消息

> happens-before

- 关系定义
  - 如果一个操作happens-before另一个操作，那么第一个操作的执行结果将对第二个操作可见，而且第一个操作的执行顺序排在第二个操作之前
  - 两个操作之间存在happens-before关系，并不意味之java平台的具体实现必须要按照happens-before关系指定的顺序来执行，如果重排序之后的执行结果与按照happens-before 关系来执行的结果一致，那么这种重排序是被允许的
- 规则
  - 程序顺序规则：一个线程中的每个操作，happens-before于线程中的任意后续操作
  - 监视器锁规则：对一个锁的解锁，happens-before于随后对这个锁的加锁
  - volatile变量规则：对一个volatile域的写，happens-before于任意后续对这个volatile域的读
  - 传递性：如果 A happens-before B，且 B happens-before C，那么 A happens-before C。
  - start()规则：如果线程A执行操作ThreadB.start(),那么A线程的ThreadB.start()操作 happens-before 于线程 B 中的任意操作。
  - join()规则：如果线程 A 执行操作 ThreadB.join()并成功返回，那么线程 B 中的任 意操作 happens-before 于线程 A 从 ThreadB.join()操作成功返回。





