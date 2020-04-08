# Java线程池

## 作用：

线程池做的工作主要是控制运行的线程的数量，处理过程中将任务放入列队，然后在线程创建后启动这些任务，如果线程数量超过了最大数量超出数量的线程排队等候，等其他线程执行完毕，再从队列中取出任务来执行。

## 特点：

线程复用;控制最大并发数;管理线程。

- 降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消柜。
- 提高响应速度。当任务到达时，任务可以不需要的等到线程创建就能立即执行。
- 提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行然一的公配，调优和监控

Java中的线程池是通过Executor框架实现的，该框架中用到了Executor, Executors,
ExecutorService，ThreadPoolExecutor这几个类 。

~~~java
public class MyThreadPoolDemo {
    public static void main(String[] args) {
//       ExecutorService executorService = Executors.newFixedThreadPool(5); // 一个线程池中固定个数
//       ExecutorService executorService = Executors.newSingleThreadExecutor();// 一个线程池中固定一个
        ExecutorService executorService = Executors.newCachedThreadPool();// 一个线程池中不固定线程数，可扩容
        try {
            for (int i = 0; i < 20; i++) {
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName() +"\t 办理业务");
                });

//                TimeUnit.MICROSECONDS.sleep(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }
}
~~~



newFixedThreadPool(),

newSingleThreadExecutor(),

newCachedThreadPool()

它们的底层都是ThreadPoolExecutor()

newWorkStealingPool的底层为ForkJoinPool

## 线程池的七大参数

- corePoolSize:线程池中的常驻核心线程数
- maximumPoolSize: 线程池能够容纳同时执行的最大线程数，此值必须大于等于1
- keepAliveTime: 多余的空闲线程的存活时间。前线程池数量超过corePooSize时， 当空闲时间达到keepAliveTime值时，多余空闲线程会被销毁直到只剩下corePoolSize个线程为止
- unit: keepAliveTime的单位。
- workQueue: 任务队列，被提交但尚未被执行的任务。
- threadFactory: 表示生成线程池中工作线程的线程工厂，用于创建线程一-般用默认的即可。
- handler: 拒绝策略，表示当队列满了并且工作线程大于等于线程池的最大线程数( maximumPoolSize) 时如何来拒绝

## 四个拒绝策略

- AbortPolicy(默认):直接抛出RejectedExecutionException异常阻止系统正常运行。
- CallerRunsPolioy: “调用者运行“一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新的任务流量
- DiscardOldestPolicy:抛弃队列中等待最久的任务，然后把当前任务加入队员中尝试再次提交当前任务。
- DiscardPolicy;:直接丢弃任务，不予任何处理也不抛出异常。如果允许任务丢失，这是最好的-一种方案。

## CPU核数

- CPU密集型
  - CPU密集的意思是该任务需要大量的运算，而没有阻塞，CPU一直全速运行。
  - CPU密集任务只有在真正的多核CPU上才可能得到加速(通过多线程)
  - 而在单核CPU上， 无论你开几个模拟的多线程该任务都不可能得到加速，因为CPU总的运算能力就那些。
  - CPU密集型任务配置尽可能少的线程数量:
    一般公式: CPU核数+1个线程的线程池

- IO密集型
  - 第一种
    - 由于IO密集型任务线程并不是一直在执行任务，则应配置尽可能多的线程，如CPU数*2
  - 第二种
    - IO密集型，即该任务需要大量的IO，即大量的阻塞。
    - 在单线程上运行IO密集型的任务会导致浪费大量的CPU运算能力浪费在等待。
    - 所以在IO密集型任务中使用多线程可以大大的加速程序运行，即使在单核CPU上，这种加速主要就是利用了被浪费掉的阻塞时间。
    - IO密集型时，大部分线程都阻塞，故需要多配置线程数:
      - 参考公式: CPU核数/1-阻塞系数
      - 阻塞系数在0.8~0.9之间
      - 比如8核CPU: 8/1-0.9= 80个线程数