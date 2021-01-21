package com.liu.MarkWord;

import com.liu.effective.threadPool.ThreadPool01;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: JVMDome
 * @description: hashCode对偏向锁的影响
 * @author: liuwenhao
 * @create: 2021-01-19 19:35
 **/
public class LockRecord03 {

    public static void main(String[] args) throws InterruptedException {

        ThreadPool01 threadPool01 = new ThreadPool01();

        Thread.sleep(5000);
        Object object = new Object();
//        object.hashCode();
//            System.out.println(Integer.toBinaryString(object.hashCode()));

        ThreadPoolExecutor threadPoolExecutor = threadPool01.getThreadPoolExecutor();

        threadPoolExecutor.execute(() -> System.out.println(ClassLayout.parseInstance(object).toPrintable()));
        threadPoolExecutor.execute(() -> System.out.println(ClassLayout.parseInstance(object).toPrintable()));

        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//        new Thread(() -> {
//            System.out.println(ClassLayout.parseInstance(object).toPrintable());
//        }).start();


//        for (int i = 0; i < 2; i++) {
//            new Thread(() -> {
//                synchronized (new Object()) {
//                    System.out.println(ClassLayout.parseInstance(object).toPrintable());
//                    try {
//                        TimeUnit.SECONDS.sleep(2);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
    }
}