package com.liu.MarkWord;

import org.openjdk.jol.info.ClassData;
import org.openjdk.jol.info.ClassLayout;

/**
 * @program: JVMDome
 * @description: 二进制
 * @author: liuwenhao
 * @create: 2021-01-03 20:11
 **/
public class LockRecord02 {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        Object object = new Object();
        synchronized (object){
            System.out.println(Integer.toBinaryString(object.hashCode()));
        }

        for (int i = 0; i < 10; i++){
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ClassLayout.parseInstance(object).toPrintable());
            }).start();
        }
    }
}