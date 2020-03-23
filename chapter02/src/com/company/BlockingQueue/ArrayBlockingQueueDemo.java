package com.company.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author shidacaizi
 * @date 2020/3/16 9:04
 */
public class ArrayBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue(3);

//        =====================第四组API====================
//        System.out.println(queue.offer("a", 2, TimeUnit.SECONDS));
//        System.out.println(queue.offer("b", 2, TimeUnit.SECONDS));
//        System.out.println(queue.offer("c", 2, TimeUnit.SECONDS));
//        System.out.println(queue.offer("d", 2, TimeUnit.SECONDS));
//        System.out.println(queue.poll(2, TimeUnit.SECONDS));
//        System.out.println(queue.poll(2, TimeUnit.SECONDS));
//        System.out.println(queue.poll(2, TimeUnit.SECONDS));
//        System.out.println(queue.poll(2, TimeUnit.SECONDS));
//        =====================第三组API====================
//        queue.put("a");
//        queue.put("b");
//        queue.put("c");
//        queue.put("d");
//        System.out.println(queue.take());
//        System.out.println(queue.take());
//        System.out.println(queue.take());
//        System.out.println(queue.take());
//        =====================第二组API====================
//        System.out.println(queue.offer("a"));
//        System.out.println(queue.offer("b"));
//        System.out.println(queue.offer("c"));
//        System.out.println(queue.offer("d"));
//        System.out.println(queue.peek());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        =====================第一组API====================
//        System.out.println(queue.add("a"));
//        System.out.println(queue.add("b"));
//        System.out.println(queue.add("c"));
//        System.out.println(queue.add("d"));
//        System.out.println(queue.element());
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
    }
}
