package com.liu.effective.lock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * @program: JVMDome
 * @description: 读锁加锁时的HoldCount变化StampedLock
 * @author: liuwenhao
 * @create: 2020-12-22 16:14
 **/
public class TestLock08 {
    ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

    public void read() {
        readLock.lock();
        try {
            System.err.println(Thread.currentThread().getName() + " 进入了读方法。。。");
            Thread.sleep(3000);
            System.err.println(Thread.currentThread().getName() + " 退出了读方法。。。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public void write() {
        writeLock.lock();
        try {
            System.err.println(Thread.currentThread().getName() + " 进入了写方法。。。");
            Thread.sleep(10000);
            System.err.println(Thread.currentThread().getName() + " 执行了写方法。。。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        TestLock08 urwLock = new TestLock08();
        new Thread(urwLock::read, "读线程").start();
//        IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0).forEach(value -> {
//            new Thread(urwLock::read, "读线程" + value).start();
//            new Thread(urwLock::read, "读线程" + value).start();
//            new Thread(urwLock::read, "读线程" + value).start();
//        });
    }
}