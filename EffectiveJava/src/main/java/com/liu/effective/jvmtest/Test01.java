package com.liu.effective.jvmtest;

/**
 * @program: JVMDome
 * @description: 重排序测试
 * @author: liuwenhao
 * @create: 2020-11-24 16:25
 **/
public class Test01 extends Throwable {
    int a = 0;
    static boolean flag = false;

    public void writer() {
//        TEst01 est01 = new TEst01();
        // 1
        a = 1;
        // 2
        flag = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + flag);
        /*线程B中的falg依然是 false*/
        /*volatile不能保证原子性？*/
//        new Thread(est01::reader, "B").start();
    }

    public void reader() {
        System.out.println(Thread.currentThread().getName() + ":" + flag);
        System.out.println(Thread.currentThread().getName() + ":" + a);
        // 3
        if (flag) {
            // 4
            int i = a;
            System.out.println(Thread.currentThread().getName() + ":" + i);
            System.out.println(Thread.currentThread().getName() + ":" + a);
        }
    }

    public static void main(String[] args) {
        Test01 est01 = new Test01();

        new Thread(est01::writer,"A").start();
//        System.out.println("二号线程启动！");
        new Thread(est01::reader,"C").start();
    }

}