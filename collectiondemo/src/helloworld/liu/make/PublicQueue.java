package helloworld.liu.make;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author shidacaizi
 * @date 2020/3/21 19:15
 */
public class PublicQueue<T> {

    private BlockingDeque<T> blockingDeque = new LinkedBlockingDeque<>(1);//缓冲区

    public void add(T msg) {

        try {
            blockingDeque.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("生产一个产品，当前商品角标为：" + "===文本为：" + msg);
    }

    public T remove() {

        T t = null;
        try {
            t = blockingDeque.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("消费一个产品，当前商品角标为：" + "===文本为：" + t);
        return t;
    }

    public static void main(String[] args) {
        PublicQueue publicQueue = new PublicQueue();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                publicQueue.add(i);
            }
        },"a").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                publicQueue.remove();
            }
        },"b").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                publicQueue.add(i);
            }
        },"c").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                publicQueue.remove();
            }
        },"d").start();
    }
}
