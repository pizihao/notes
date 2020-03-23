package com.liu.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author shidacaizi
 * @data 2020/3/9 21:33
 */
public class SynchroDemo01 {

    private static Integer MAXTIME = 5;

    public static void main(String[] args) {

        SynchroDemo01 demo01 = new SynchroDemo01();

        for (int i = 0; i < MAXTIME ; i++) {
            new Thread(SynchroDemo01 :: accessResources1).start();
        }

        for (int i = 0; i < MAXTIME ; i++) {
            new Thread(demo01 :: accessResources2).start();
        }

    }

    //synchronized修饰静态方法
    public synchronized static void accessResources1() {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"\t accessResources1 running o(╥﹏╥)o");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //synchronized修饰非静态方法
    public synchronized void accessResources2() {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"\t accessResources2 running (*^▽^*)");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
