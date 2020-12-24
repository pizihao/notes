package com.liu.effective.lock;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @program: JVMDome
 * @description: Lock简单使用
 * @author: liuwenhao
 * @create: 2020-12-04 17:47
 **/
public class TestLock01 {
    public static void main(String[] args) {
        //声明一个可重入锁
        Lock lock = new ReentrantLock();
        Lock lock01 = new ReentrantLock();
        //使用这个Condition对这个锁进行相应的操作
        Condition condition = lock.newCondition();
        IntStream.of(0,1,2,3,4,5,6,7,8,9).forEach(value ->{
            new Thread(() -> {
                lock.lock();
//                lock01.lock();
                try {
//                    condition.await(10000, TimeUnit.MILLISECONDS);
                    System.out.println(Thread.currentThread().getName() + "拿到锁");
                }  finally {
                    lock.unlock();
//                    lock01.unlock();
                }
            }, "线程" + value).start();
        });
    }
}