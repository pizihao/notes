package helloworld.liu;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shidacaizi
 * @date 2020/3/15 15:26
 */
public class CASTest01 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5,2019)+"\t"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,2016)+"\t"+atomicInteger.get());

    }
}
