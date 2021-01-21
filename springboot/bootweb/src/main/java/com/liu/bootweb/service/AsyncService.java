package com.liu.bootweb.service;

import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

/**
 * @program: JVMDome
 * @description: AsyncService
 * @author: liuwenhao
 * @create: 2021-01-10 16:09
 **/
public interface AsyncService {
    /**
     * 执行异步任务
     */
    void executeAsync() throws InterruptedException;

    /**
     * 执行异步计算
     * @return int
     */
    Future<Integer> submitAsync(int task) throws InterruptedException;
}