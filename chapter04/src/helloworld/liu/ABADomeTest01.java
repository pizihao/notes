package helloworld.liu;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author shidacaizi
 * @date 2020/3/15 17:30
 */
public class ABADomeTest01 {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        new Thread(()->{
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(()->{
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(atomicReference.compareAndSet(100, 2010)+"\t"+atomicReference.get());
        }, "t2").start();

        //暂停两秒钟
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第一次版本号："+stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+"\t第二次版本号："+atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+"\t第三次版本号："+atomicStampedReference.getStamp());
         }, "t3").start();

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
