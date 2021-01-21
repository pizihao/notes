package helloworld.liu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shidacaizi
 * @data 2020/3/11 22:33
 */
public class LockTest01 {

    public static void main(String[] args) {
        Data01 data01 = new Data01();

        new Thread(() ->{ for (int i = 0; i < 10; i++) {
            data01.printA();
        }
        }, "线程一").start();
        new Thread(() ->{ for (int i = 0; i < 10; i++) {
            data01.printB();
        }
        }, "线程二").start();
        new Thread(() ->{ for (int i = 0; i < 10; i++) {
            data01.printC();
        }
        }, "线程三").start();

    }

}

class Data01 {
//    定义一个可重入锁
    /**
     * lock
     */
    private Lock lock = new ReentrantLock();
//    使用Condition接口来实现对lock的操作
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int num = 1;
    public void printA() {
//        上锁
        lock.lock();
        try {
            while (num != 1){
                condition1.await();
            }
            System.out.println("现在是" + Thread.currentThread().getName());
            num = 2;
//          指定唤醒线程二
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            解锁
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();

        try {
            while (num != 2){
                condition2.await();
            }

            System.out.println("现在是" + Thread.currentThread().getName());

            num = 3;
            condition3.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();

        try {
            while (num != 3){
                condition3.await();
            }

            System.out.println("现在是" + Thread.currentThread().getName());

            num = 1;
            condition1.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
