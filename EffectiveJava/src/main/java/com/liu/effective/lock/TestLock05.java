package com.liu.effective.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: JVMDome
 * @description: 构造器
 * @author: liuwenhao
 * @create: 2020-12-16 20:11
 **/
public class TestLock05 {
    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(5, () -> {
            System.out.println(Thread.currentThread().getName());
        });
        IntStream.of(0, 1, 2, 3, 4).forEach(value -> {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "到达");
                    barrier.await();

                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "第二次到达");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "线程" + value).start();
        });
    }
}