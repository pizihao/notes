package com.puls.javapool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author shidacaizi
 * @date 2020/3/16 18:33
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
//       ExecutorService executorService = Executors.newFixedThreadPool(5); // 一个线程池中固定个数
//       ExecutorService executorService = Executors.newSingleThreadExecutor();// 一个线程池中固定一个
//        ExecutorService executorService = Executors.newCachedThreadPool();// 一个线程池中不固定线程数，可扩容
        ExecutorService executorService = Executors.newWorkStealingPool();
        try {
            for (int i = 0; i < 20; i++) {
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName() +"\t 办理业务");
                });
                TimeUnit.MICROSECONDS.sleep(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }
}
