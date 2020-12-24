package com.liu.effective.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @program: JVMDome
 * @description: Lock和CountDownLatch
 * @author: liuwenhao
 * @create: 2020-12-16 09:56
 **/
public class TestLock03 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        CountDownLatch count = new CountDownLatch(5);
        lock.lock();
        try {
            IntStream.of(0, 1, 2, 3, 4).forEach(value -> {
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName());
                    count.countDown();
                }, "线程" + value).start();
            });
        } finally {
            try {
                count.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("锁已释放");
        }
    }
}