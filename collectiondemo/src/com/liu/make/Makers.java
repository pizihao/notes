package com.liu.make;

/**
 * @author shidacaizi
 * @date 2020/3/21 17:17
 */
// 生产者和消费者问题的三种写法
//    等待唤醒，通知唤醒
public class Makers {
    //    synchronized 版
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    data.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "a").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "b").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    data.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "c").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "d").start();
    }
}

// 判断等待，业务，通知
class Data {
    private int number = 0;

    //+1
    public synchronized void increment() throws InterruptedException {
        // 防止虚假唤醒，不要用if判断
        while (number != 0) {
//            等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
//         通知其他线程 +1完毕
        this.notifyAll();
    }

    //-1
    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
//            等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
//        通知其他线程 -1完毕
        this.notifyAll();
    }
}
