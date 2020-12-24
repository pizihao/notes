package com.liu.effective.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: JVMDome
 * @description: Semaphore
 * @author: liuwenhao
 * @create: 2020-12-17 17:16
 **/
public class TestLock06 {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3);
        IntStream.of(0,1,2,3).forEach(value -> {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"开始");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },"线程"+value).start();
        });
    }
}