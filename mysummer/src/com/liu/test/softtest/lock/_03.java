package com.liu.test.softtest.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shidacaizi
 * @date 2020/8/15 23:21
 */
//juc java.util.concurrent
public class _03 implements Runnable {
//- 可重入锁又名递归锁，是指在同一个线程在外层方法获取锁的时候，再进入该线程的内层方法会自动获取锁，不会因为之前已经获取过还没释放而阻塞。
//- Java中ReentrantLock和synchronized都是可重入锁
//- 优点是可一定程度避免死锁
    private Lock lock = new ReentrantLock(); //创建lock锁 可重入锁
    private int ticke = 100;
//这是一个简单的案例
    @Override
    public void run() {
        while (true){
            lock.lock();//上锁
            try {
                if (ticke > 0){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"完成售票，票价为"+(--ticke));
                }
            } finally {
                lock.unlock();//释放锁
            }
        }
    }
}
