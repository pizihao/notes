package helloworld.company.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shidacaizi
 * @date 2020/3/16 13:25
 */
// 阻塞队列版生产者消费者
public class ProdConsumer4 {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(1));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"消费线程启动");
            try {
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main线程请求结束，所有活动停止");

        myResource.stop();
    }
}

class MyResource{
    private boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myProd() throws Exception {
       String data = null;
       boolean retValue;
       while (FLAG){
           data = atomicInteger.incrementAndGet() + "";
           retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
           if (retValue){
               System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"成功");
           }else {

               System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"失败");
           }
           TimeUnit.SECONDS.sleep(1);
       }
        System.out.println(Thread.currentThread().getName()+"\t 停，FALG=false，生产结束");
    }

    public void myConsumer() throws InterruptedException {
        String result = null;
        while(FLAG){
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 消费停止");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+result+"成功");
        }
    }

    public void stop(){
        this.FLAG = false;
    }
}
