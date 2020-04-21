package helloworld.liu.make;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shidacaizi
 * @date 2020/3/21 18:39
 */
public class Makeress {
    //    lock 指定唤醒线程版 版
    public static void main(String[] args) {
        Dataess data = new Dataess();
        new Thread(() -> {
                for (int i = 0; i < 20; i++) {
                    data.printA();
                }
        }, "a").start();

        new Thread(() -> {
                for (int i = 0; i < 20; i++) {
                    data.printB();
                }
        }, "b").start();
        new Thread(() -> {
                for (int i = 0; i < 20; i++) {
                    data.printC();
                }
        }, "c").start();
    }
}

// 判断等待，业务，通知
class Dataess {
    // 使用lock锁实现
    Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number = 1;

    public void printA() {
        lock.lock();
        try {
            while (number != 1) {
                condition1.await();
            }
            number = 2;
            System.out.println(Thread.currentThread().getName() + "=>AAAAAAA");
            condition2.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printB() {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            number = 3;
            System.out.println(Thread.currentThread().getName() + "=>BBBBBBB");
            condition3.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC() {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            number = 1;
            System.out.println(Thread.currentThread().getName() + "=>CCCCCCC");
            condition1.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
