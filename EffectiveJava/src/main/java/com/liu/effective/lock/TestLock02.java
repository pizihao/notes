package com.liu.effective.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;
import java.util.stream.IntStream;

/**
 * @program: JVMDome
 * @description: AQS
 * @author: liuwenhao
 * @create: 2020-12-07 13:39
 **/
public class TestLock02 {
    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(5);
        IntStream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).forEach(value -> {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName());

                    latch.countDown();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }, "线程" + value).start();
        });
        try {
            // 10个线程countDown()都执行之后才会释放当前线程,程序才能继续往后执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束");
    }
}