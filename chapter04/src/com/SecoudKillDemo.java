package com;

import java.util.concurrent.*;

/**
 * @author shidacaizi
 * @date 2020/3/17 16:40
 */
public class SecoudKillDemo {
    private static final RejectedExecutionHandler defaultHandler =
            new ThreadPoolExecutor.AbortPolicy();

    public static void main(String[] args) {
        SecoudKillDemo secoudKillDemo = new SecoudKillDemo();
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(20);
        BlockingQueue<Runnable> queues = new ArrayBlockingQueue<>(20);
        Executor executorService = new ThreadPoolExecutor(
                1500,
                1501,
                5,
                TimeUnit.SECONDS,
                queues,
                Executors.defaultThreadFactory(),
                defaultHandler
        );
        for (int i = 1; i <= 1500; i++) {
//            try {
//                TimeUnit.MICROSECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            executorService.execute(() -> {
                try {
                    if (!secoudKillDemo.quedeAdd(Thread.currentThread().getName() + "", queue)) {
                        System.out.println(Thread.currentThread().getName() + "很遗憾");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        }

        for (String arr : queue) {
            if (arr.equals(queue.element())) {
                System.out.println("恭喜你" + arr);
            } else {
                System.out.println(arr + "很遗憾");
            }
        }
    }

    public Boolean quedeAdd(String a, BlockingQueue<String> queue) {
        return queue.offer(a);
    }
}


