package com.liu.MarkWord;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @program: JVMDome
 * @description: hashcode测试
 * @author: liuwenhao
 * @create: 2021-01-19 22:04
 **/
public class LockRecord04 {

    public static void main(String[] args) throws InterruptedException {

        TimeUnit.SECONDS.sleep(5);
        Object o = new Object();

//        System.out.println(Integer.toBinaryString(o.hashCode()));
//        o.toString();
        synchronized (o) {
            for (int i = 0; i < 5; i++) {
                new Thread(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ClassLayout.parseInstance(o).toPrintable());
                }).start();
            }
        }
    }
}