package com.liu.effective.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * @program: JVMDome
 * @description: CyclicBarrier
 * @author: liuwenhao
 * @create: 2020-12-16 10:41
 **/
public class TestLock04 {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(5, () -> {
            System.out.println(Thread.currentThread().getName());
        });
        IntStream.of(0, 1, 2, 3, 4, 5).forEach(value -> {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "到达");

                    if (value == 4) {
                        barrier.reset();
                    } else {
                        barrier.await();
                    }
//                    System.out.println(Thread.currentThread().getName() + "第二次到达");
//                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "线程" + value).start();
        });
    }
}