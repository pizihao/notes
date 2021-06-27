package com.liu.effective.threadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: JVMDome
 * @description: TheadRunable
 * @author: liuwenhao
 * @create: 2021-02-27 11:11
 **/
public class ThreadTest01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask;
        new Thread(futureTask = new FutureTask<>(() -> 10)).start();
        System.out.println(futureTask.get());
    }
}