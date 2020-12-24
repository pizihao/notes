package com.liu.effective.jvmtest;

import java.util.stream.IntStream;

/**
 * @program: JVMDome
 * @description: ThreadLocalMap
 * @author: liuwenhao
 * @create: 2020-12-04 15:24
 **/
public class Text09 {
    public static void main(String[] args) {

        ThreadLocal<String> local01 = new ThreadLocal<>();
        ThreadLocal<String> local02 = new ThreadLocal<>();
        Test03 test03 = new Test03();
        IntStream.range(0,10).forEach(value ->{
            new Thread(() -> {
                local01.set(Thread.currentThread().getName() + "local01:" + value);
                local02.set(Thread.currentThread().getName() + "local02:" + value);
                test03.sleepAll();
                System.out.println(local01.get());
                test03.sleepAll();
                System.out.println(local02.get());
            }).start();
        });
    }
}