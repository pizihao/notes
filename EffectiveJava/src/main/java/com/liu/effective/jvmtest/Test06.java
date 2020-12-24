package com.liu.effective.jvmtest;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: JVMDome
 * @description: 等待通知机制
 * @author: liuwenhao
 * @create: 2020-12-02 19:04
 **/
public class Test06 {
    static boolean flag = true;
    static final Object lock = new Object();

    public static void main(String[] args) throws Exception {
//        Thread waitThread = new Thread(new Wait(), "WaitThread");
//        waitThread.start();
//        TimeUnit.SECONDS.sleep(1);
//        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
//        notifyThread.start();
        Stream.of(1,2,3,4).collect(Collectors.toList()).forEach(System.out::println);
    }

    public static String getDate() {
        return new SimpleDateFormat(" HH: mm: ss ").format(new Date());
    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            // 加锁，拥有 lock 的 Monitor
            synchronized (lock) {
                // 当条件不满足时，继续 wait，同时释放了 lock 的锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wa @ " + getDate());
                        lock.wait();
                    } catch (InterruptedException ignored) {
                    }
                }
                // 条件满足时，完成工作
                System.out.println(Thread.currentThread() + " flag is false. running @ " + getDate());
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            Test03 test03 = new Test03();
            // 加锁，拥有 lock 的 Monitor
            synchronized (lock) {
                // 获取 lock 的锁，然后进行通知，通知时不会释放 lock 的锁，
                // 直到当前线程释放了 lock 后，WaitThread 才能从 wait 方法中返回
                System.out.println(Thread.currentThread() + " hold lock. notify @ " + getDate());
                lock.notifyAll();
                flag = false;
                test03.sleepAll();
            }
            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ " + getDate());
                test03.sleepAll();
            }
        }
    }
}