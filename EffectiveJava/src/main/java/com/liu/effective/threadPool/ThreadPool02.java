package com.liu.effective.threadPool;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: JVMDome
 * @description: 线程池实战
 * @author: liuwenhao
 * @create: 2021-01-08 20:33
 **/
public class ThreadPool02 {
    private static final ThreadPool01 threadPool01 = new ThreadPool01();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = threadPool01.getThreadPoolExecutor();
        List<FutureTask<String>> tasks = new ArrayList<>();

//        for (int i = 0; i < 12; i++) {
//            int finalI = i;
//            tasks.add(new FutureTask<String>(() -> {
//                TimeUnit.SECONDS.sleep(3);
//                return "" + finalI;
//            }));
//        }
//
//        tasks.forEach(integerFutureTask -> {
//            threadPoolExecutor.execute(integerFutureTask);
//            try {
//                System.out.println(integerFutureTask.get());
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        });

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            FutureTask<String> submit = (FutureTask<String>) threadPoolExecutor.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return finalI + "";
            });
            submit.get();
            tasks.add(submit);
        }

        tasks.forEach(integerFutureTask -> {
            try {
                System.out.println(integerFutureTask.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        threadPoolExecutor.shutdown();
    }
}