package helloworld.liu;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author shidacaizi
 * @date 2020/3/15 17:30
 */
public class ABADomeTest01 {
//    设置原子引用的初始值为 100 AtomicStampedReference相较于AtomicReference而言 会维护一个时间戳 用来确定变量的状态
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
//      线程一  修改atomicReference对象的值为101，再改回100 虽然只没有变化 但是已经进行过中间操作了
        new Thread(()->{
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();
//      线程二  等待1s 目的是等待 线程一修改完毕 (注意异常的处理)，然后对atomicReference进行修改 由100 修改为2010
        new Thread(()->{
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(atomicReference.compareAndSet(100, 2010)+"\t"+atomicReference.get());
        }, "t2").start();

//      暂停两秒钟
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//      线程三  首先查看一下版本号 也就是 atomicStampedReference 维护的时间戳
//      所谓的时间戳就是一个int类型的变量
//      final int stamp;
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第一次版本号："+stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//          修改atomicStampedReference的值的时候顺便看一下当前的版本号
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+"\t第二次版本号："+atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+"\t第三次版本号："+atomicStampedReference.getStamp());
         }, "t3").start();

//      线程四  在修改的时候版本号 不一致了 所以修改失败
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第一次版本号："+stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean compareAndSet = atomicStampedReference.compareAndSet(100, 1024, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName()+"\t第二次版本号："+atomicStampedReference.getStamp());
            System.out.println(compareAndSet+"\t"+atomicStampedReference.getReference());
        }, "t4").start();
    }
}
