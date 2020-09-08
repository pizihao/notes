package com.liu.thead;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author shidacaizi
 * @date 2020/8/30 15:39
 */
public class MyThead {
    /**
     * 测试多个线程结束后开始线程
     */
    public synchronized static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Executor executor = Executors.newFixedThreadPool(5);
        //测试阻塞其他线程
        new Thread(new MyRunnable(countDownLatch)).start();
        //为了测试效果进行线程休眠
        Thread.sleep(1000);
        for (int i = 1; i <= 5; i++) {
            countDownLatch.countDown();
            System.out.println("第" + i + "调用countDown方法结束");
            //为了测试效果进行线程休眠
            Thread.sleep(1000);
        }
    }
}

class MyRunnable implements Runnable {
    CountDownLatch countDownLatch;

    public MyRunnable(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("进入线程，即将进入阻塞状态");
            //调用await进行线程阻塞
            countDownLatch.await();
            System.out.println("线程进行执行...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyRunnable1 implements Runnable {
    private CountDownLatch countDownLatch;
    private String mark;

    public MyRunnable1(CountDownLatch countDownLatch, String mark) {
        super();
        this.countDownLatch = countDownLatch;
        this.mark = mark;
    }

    @Override
    public void run() {
        System.out.println(mark + "号线程开始");
        try {
            //使线程休眠，看到更好的测试效果
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(mark + "号线程结束");
        //调用CountDownLatch的countDown方法进行次数减1
        countDownLatch.countDown();
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
