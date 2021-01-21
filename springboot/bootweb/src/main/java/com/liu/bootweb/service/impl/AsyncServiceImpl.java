package com.liu.bootweb.service.impl;

import com.liu.bootweb.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @program: JVMDome
 * @description: AsyncServiceImpl
 * @author: liuwenhao
 * @create: 2021-01-10 16:51
 **/
@Service
public class AsyncServiceImpl implements AsyncService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() throws InterruptedException {
        logger.info("开始异步任务");
        System.out.println(Thread.currentThread().getName() + "sleep。。。");
        TimeUnit.SECONDS.sleep(2);
        logger.info("终止异步任务");
    }

    @Override
    @Async("asyncServiceExecutor")
    public Future<Integer> submitAsync(int task) throws InterruptedException {
        logger.info("开始异步计算");
        System.out.println(Thread.currentThread().getName() + "sleep。。。");
        TimeUnit.SECONDS.sleep(2);
        logger.info("终止异步计算");
        return new AsyncResult<Integer>(task*2);
    }
}