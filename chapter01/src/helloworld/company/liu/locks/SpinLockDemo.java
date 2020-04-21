package helloworld.company.liu.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author shidacaizi
 * @date 2020/3/15 21:33
 */

class AtomicReferenceDemo {
    private  AtomicReference<Thread> integerAtomicReference = new AtomicReference<>();

    public void mylocks() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "我是一个线程");

        while (!integerAtomicReference.compareAndSet(null, thread)) {
            System.out.println(thread.getName()+"------>自旋中");
        }
    }

    public void myUNLocks() {
        Thread thread = Thread.currentThread();
        integerAtomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "--->myUNLocks");
    }
}

//手写一个自旋锁
public class SpinLockDemo {

    public static void main(String[] args) {
        AtomicReferenceDemo spinLockDemo = new AtomicReferenceDemo();
        new Thread(() -> {
            spinLockDemo.mylocks();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUNLocks();
        }, "a").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLockDemo.mylocks();

            spinLockDemo.myUNLocks();
        }, "b").start();
    }
}
