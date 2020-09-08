package com.liu.test.softtest.lock;

/**
 * @author shidacaizi
 * @date 2020/8/15 23:16
 */
//volatile
public class _02 {
    //我们知道volatile可以看做是一种synchronized的轻量级锁，
// 他能够保证并发时，被它修饰的共享变量的可见性，
// 那么他是如何实现可见性的呢？
//我们从jmm的角度来看一下，每个线程拥有自己的工作内存，
// 实际上线程所修改的共享变量是从主内存中拷贝的副本，
// 当一个共享变量被volatile修饰时，它会保证修改的值会立即被更新到主存，
// 当有其他线程需要读取时，它会去内存中读取新值。
// 实现原理
//
//被volatile修饰的共享变量在进行写操作的时候：
//  1、将当前处理器缓存行的数据写回到系统内存。
//  2、这个写回内存的操作会使在其他CPU里缓存了该内存地址的数据无效。
//为了提高处理速度，处理器不直接和内存进行通信，
// 而是先将系统内存的数据读到内部缓存（L1，L2或其他）后再进行操作，
// 但操作完不知道何时会写到内存。如果对声明了volatile的变量进行写操作，
// JVM就会向处理器发送一条Lock前缀的指令，
// 将这个变量所在缓存行的数据写回到系统内存。但是，就算写回到内存，
// 如果其他处理器缓存的值还是旧的，再执行计算操作就会有问题。
// 所以，在多处理器下，为了保证各个处理器的缓存是一致的，
// 就会实现缓存一致性协议，每个处理器通过嗅探在总线上传播的数据来检查自己
// 缓存的值是不是过期了，当处理器发现自己缓存行对应的内存地址被修改，
// 就会将当前处理器的缓存行设置成无效状态，当处理器对这个数据进行修改操作
// 的时候，会重新从系统内存中把数据读到处理器缓存里
//**相同点**：都保证了可见性
//**不同点**：volatile不能保证原子性，
//           但是synchronized会发生阻塞（在线程状态转换中详说），
//           开销更大。
//特点：
//    1.保证此变量对所有的线程的可见性，
//    2.禁止指令重排序优化
//    3.不能保证原子性
    static class VolatileDemo {
        private static volatile boolean isOver = false;
        public static void main(String[] args) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!isOver) ;
                }
            });
            thread.start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isOver = true;
        }
    }
}
