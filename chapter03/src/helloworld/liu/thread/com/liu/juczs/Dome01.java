package helloworld.liu.thread.com.liu.juczs;

import java.util.concurrent.TimeUnit;

/**
 * @author shidacaizi
 * @date 2020/3/11 8:28
 */
// 手写一个死锁
public class Dome01 {
    public static void main(String[] args) {
        String lockA = "A";
        String lockB = "B";
        new Thread(new DeadLockTest01(lockA, lockB), "A").start();
        new Thread(new DeadLockTest01(lockB, lockA), "B").start();
    }
}

class DeadLockTest01 implements  Runnable{
    private String lockA;
    private String lockB;

    public DeadLockTest01(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"拿到了锁1");
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"拿到了锁2");
            }
        }
    }
}