package com.liu.effective.jvmtest;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @program: JVMDome
 * @description: 线程睡眠测试
 * @author: liuwenhao
 * @create: 2020-12-01 14:51
 **/
public class Test03 {

    public static void main(String[] args) {


        Test03 test03 = new Test03();

        new Thread(test03::sleepAll,"c").start();

        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "线程启动");
//            会被中断，中断后会被重置 中断标志
//            try {
//                Thread.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

//           会被中断，中断的同时抛出InterruptedException异常
//            test03.sleepAll();

//            会被中断
            while (!Thread.currentThread().isInterrupted()){

            }

            System.out.println("中断出现"+Thread.currentThread().getName() + "结束");
        },"a");

        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "线程启动");
            try {
                TimeUnit.SECONDS.sleep(1);
                /*中断a线程*/
                a.interrupt();
                System.out.println("a线程是否被中断:"+a.isInterrupted());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "b");
        System.out.println("a线程是否被中断:"+a.isInterrupted());
        a.start();
        b.start();

        Thread.currentThread().getThreadGroup().list();

    }


    public void sleepAll(){
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}