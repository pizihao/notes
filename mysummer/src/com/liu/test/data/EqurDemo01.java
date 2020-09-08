package com.liu.test.data;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author shidacaizi
 * @date 2020/3/28 15:38
 */
public class EqurDemo01 {
    public static void main(String[] args) {
        String atr = "123";
        BlockingDeque<Runnable> runnables = new LinkedBlockingDeque<>(3);
        String a = "123";
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                6,
                5,
                4,
                TimeUnit.SECONDS,
                runnables
        );
        System.out.println(atr.equals(a));
    }
}
