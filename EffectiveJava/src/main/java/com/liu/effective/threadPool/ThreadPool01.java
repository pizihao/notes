package com.liu.effective.threadPool;

import java.util.concurrent.*;

/**
 * @program: JVMDome
 * @description: 线程池实战
 * @author: liuwenhao
 * @create: 2021-01-08 15:55
 **/
public class ThreadPool01 {
    private final LinkedBlockingQueue<Runnable> linkedQueue = new LinkedBlockingQueue<Runnable>(10);
    private final ThreadPoolExecutor.DiscardPolicy policy = new ThreadPoolExecutor.DiscardPolicy();
    //一个线程池
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            1,
            1,
            5,
            TimeUnit.SECONDS,
            linkedQueue,
            Executors.defaultThreadFactory(),
            policy
    );

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }

    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
    }

}