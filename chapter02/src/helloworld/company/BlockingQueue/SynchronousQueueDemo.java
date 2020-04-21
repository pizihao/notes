package helloworld.company.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author shidacaizi
 * @date 2020/3/16 10:00
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> synchronous = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                synchronous.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put 2");
                synchronous.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put 3");
                synchronous.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(synchronous.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(synchronous.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(synchronous.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();
    }
}
