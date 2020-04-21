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

        new Thread(() ->{ for (int i = 0; i < 10; i++) data01.printA();}, "A").start();
        new Thread(() ->{ for (int i = 0; i < 10; i++) data01.printB();}, "B").start();
        new Thread(() ->{ for (int i = 0; i < 10; i++) data01.printC();}, "C").start();

    }

}

class Data01 {
    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    private int num = 1;

    public void printA() {
        lock.lock();

        try {
            while (num != 1){
                condition1.await();
            }

            System.out.println(Thread.currentThread().getName()+"A");

            num = 2;
            condition2.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();

        try {
            while (num != 2){
                condition2.await();
            }

            System.out.println(Thread.currentThread().getName()+"B");

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

            System.out.println(Thread.currentThread().getName()+"C");

            num = 1;
            condition1.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
