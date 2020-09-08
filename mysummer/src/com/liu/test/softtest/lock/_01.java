package com.liu.test.softtest.lock;

/**
 * @author shidacaizi
 * @date 2020/8/15 23:15
 */
//synchronized
public class _01 {
// synchronized是并发编程中接触的最基本的同步工具，
// 是一种重量级锁，也是java内置的同步机制，
// 首先我们知道synchronized提供了互斥性的语义和可见性，
// 那么我们可以通过使用它来保证并发的安全。

// 在并发编程中存在线程安全问题，主要原因有：1.存在共享数据
// 2.多线程共同操作共享数据。关键字synchronized可以保证在同一时刻，
// 只有一个线程可以执行某个方法或某个代码块，
// 同时synchronized可以保证一个线程的变化可见（可见性），
// 即可以代替volatile。

// 原理
// synchronized可以保证方法或者代码块在运行时，
// 同一时刻只有一个方法可以进入到临界区，同时它还可以保证
// 共享变量的内存可见性

//  三种应用方式
//  Java中每一个对象都可以作为锁，这是synchronized实现同步的基础：
//  普通同步方法（实例方法），锁是当前实例对象 ，进入同步代码前要获得当前实例的锁
//  静态同步方法，锁是当前类的class对象 ，进入同步代码前要获得当前类对象的锁
//  同步方法块，锁是括号里面的对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
//  作用
//  Synchronized是Java中解决并发问题的一种最常用最简单的方法 ，他可以确保线程互斥的访问同步代码
//    一、synchronized作用于普通同步方法
//  例子一 多个线程访问同一个对象的同一个方法
//    static class synchronizedTest implements Runnable {
//        //共享资源
//        static int i = 0;
//        /**
//         * synchronized 修饰实例方法
//         */
//        public synchronized void increase() {
//            i++;
//        }
//        @Override
//        public void run() {
//            for (int j = 0; j < 10000; j++) {
//                System.out.println(Thread.currentThread().getName());
//                increase();
//            }
//        }
//        public static void main(String[] args) throws InterruptedException {
//            synchronizedTest test = new synchronizedTest();
////          创建了两个线程 t1 和 t2
//            Thread t1 = new Thread(test);
//            Thread t2 = new Thread(test);
//            t1.start();
//            t2.start();
////            join 等待这个线程执行完毕并死亡
//            t1.join();
//            t2.join();
//            System.out.println(i);
//        }
//    }

    //  例子二 一个线程获取了该对象的锁之后，其他线程来访问其他synchronized实例方法现象
//    static class SynchronizedTest {
//        public static void main(String[] args) {
//            final SynchronizedTest test = new SynchronizedTest();
//            new Thread(() -> test.method1()).start();
//            new Thread(test::method2).start();
//        }
//        public synchronized void method1() {
//            System.out.println("方法一开始");
//            try {
//                System.out.println("方法一运行");
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("方法一结束");
//        }
//        public synchronized void method2() {
//            System.out.println("方法二开始");
//            try {
//                System.out.println("方法二运行");
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("方法二结束");
//        }
//    }
//  例子三 一个线程获取了该对象的锁之后，其他线程来访问其他非synchronized实例方法现象 去掉②中方法二的synchronized
//    static class SynchronizedTest {
//        public static void main(String[] args) {
//            final SynchronizedTest test = new SynchronizedTest();
//            new Thread(test::method1).start();
//            new Thread(test::method2).start();
//        }
//        public synchronized void method1() {
//            System.out.println("方法一开始");
//            try {
//                System.out.println("方法一运行");
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("方法一结束");
//        }
//        public void method2() {
//            System.out.println("方法二开始");
//            try {
//                System.out.println("方法二运行");
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("方法二结束");
//        }
//    }
//    二会在一结束前开始
//    例子四 当多个线程作用于不同的对象
//    static class SynchronizedTest {
//        public synchronized void method1() {
//            System.out.println("Method 1 start");
//            try {
//                System.out.println("Method 1 execute");
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Method 1 end");
//        }
//
//        public synchronized void method2() {
//            System.out.println("Method 2 start");
//            try {
//                System.out.println("Method 2 execute");
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Method 2 end");
//        }
//        public static void main(String[] args) {
//            final SynchronizedTest test1 = new SynchronizedTest();
//            final SynchronizedTest test2 = new SynchronizedTest();
//            new Thread(test1::method1).start();
//            new Thread(test2::method2).start();
//        }
//    }
//   一 二会同时开始
//  二、synchronized作用于静态方法
//    例子
//    static class synchronizedTest implements Runnable {
//        //共享资源
//        static int i = 0;
//        /**
//         * synchronized 修饰静态方法
//         */
//        public static synchronized void increase() {
//            i++;
//        }
//        public static void main(String[] args) throws InterruptedException {
//            Thread t1 = new Thread(new synchronizedTest());
//            Thread t2 = new Thread(new synchronizedTest());
//            t1.start();
//            t2.start();
//            t1.join();
//            t2.join();
//            System.out.println(i);
//        }
//        @Override
//        public void run() {
//            for (int j = 0; j < 10000; j++) {
//                increase();
//            }
//        }
//    }
//  三、synchronized作用于同步代码块
    static class synchronizedTest implements Runnable {
        static final synchronizedTest instance=new synchronizedTest();
        static int i=0;
        @Override
        public void run() {
            //省略其他耗时操作....
            //使用同步代码块对变量i进行同步操作,锁对象为instance
            synchronized(instance){
                for(int j=0;j<10000;j++){
                    i++;
                }
            }
        }
        public static void main(String[] args) throws InterruptedException {
            Thread t1=new Thread(instance);
            Thread t2=new Thread(instance);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println(i);
        }
    }
}
