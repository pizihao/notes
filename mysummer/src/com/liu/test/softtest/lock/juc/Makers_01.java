package com.liu.test.softtest.lock.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shidacaizi
 * @date 2020/8/17 12:44
 */
public class Makers_01 {
    //    lock锁 版
    public static void main(String[] args) {
        Dataes data = new Dataes();
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    data.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "a").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "b").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    data.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "c").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "d").start();
    }
}

// 判断等待，业务，通知
class Dataes {
    // 使用lock锁实现
    Lock lock = new ReentrantLock();
//    condition对象是依赖于lock对象的，意思就是说condition对象需要通过lock对象进行创建出来
    Condition condition = lock.newCondition();
    private int number = 0;
    //condition.await();
    //condition.signalAll();
    //+1
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            // 业务代码
            // 防止虚假唤醒，不要用if判断
//            while (number != 0) {
////            等待
//                condition.await();
//            }
            TimeUnit.MICROSECONDS.sleep(100);
//            生产
            number++;
            System.out.println(Thread.currentThread().getName() + "生产了一个=>" + number);
//         通知其他线程 +1完毕 随机通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //-1
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
//            等待
                condition.await();
            }
            TimeUnit.MICROSECONDS.sleep(200);
//            消费
            number--;
            System.out.println(Thread.currentThread().getName() + "消费了一个=>" + number);
//        通知其他线程 -1完毕
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
