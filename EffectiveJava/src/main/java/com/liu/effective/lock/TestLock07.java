package com.liu.effective.lock;


import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.*;
import java.util.stream.IntStream;

/**
 * @program: JVMDome
 * @description: 读写锁
 * @author: liuwenhao
 * @create: 2020-12-18 09:25
 **/
public class TestLock07 {
    ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    ReadLock readLock = rwLock.readLock();
    WriteLock writeLock = rwLock.writeLock();

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
        TestLock07 urwLock = new TestLock07();
//        IntStream.of(1, 2).forEach(value -> {
//        new Thread(urwLock::read, "读线程1").start();
//        new Thread(urwLock::write, "写线程").start();
//        new Thread(urwLock::read, "读线程2").start();
//        });
        String str = "123456";
    }
}