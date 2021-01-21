package com.liu.MarkWord;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @program: JVMDome
 * @description: LockRecord
 * @author: liuwenhao
 * @create: 2021-01-03 13:20
 **/
public class LockRecord01 {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(6000);
         Object  object = new Object();

        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        LockRecord01 lockRecord01 = new LockRecord01();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    lockRecord01.run01(object);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(6000);
        System.gc();

        new Thread(() -> {
            try {
                for (;;) {
                    lockRecord01.run01(object);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        object.hashCode();
    }

    public void run01(Object o) throws InterruptedException {
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}