package com.liu.effective.jvmtest;

import java.util.stream.Stream;

/**
 * @program: JVMDome
 * @description: join
 * @author: liuwenhao
 * @create: 2020-12-03 16:05
 **/
public class Test07 {
    public static void main(String[] args) throws Exception {
        Test03 test03 = new Test03();
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            // 每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        test03.sleepAll();
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }
    static class Domino implements Runnable {
        private Thread thread;
        public Domino(Thread thread) {
            this.thread = thread;
        }
        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }

}