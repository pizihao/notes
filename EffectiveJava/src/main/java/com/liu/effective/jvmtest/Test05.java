package com.liu.effective.jvmtest;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * @program: JVMDome
 * @description: 字节码测试
 * @author: liuwenhao
 * @create: 2020-12-02 18:14
 **/
public class Test05 {


    public static void main(String[] args) {

    }


    public synchronized void sleepAll(){
        try {
            TimeUnit.SECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}