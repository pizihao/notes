package com.liu.make;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shidacaizi
 * @date 2020/3/21 18:22
 */
public class Makeres {
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
            while (number != 0) {
//            等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
//         通知其他线程 +1完毕
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
            number--;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
//        通知其他线程 -1完毕
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}