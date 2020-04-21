package helloworld.pojo.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author shidacaizi
 * @data 2020/3/12 16:07
 */
public class Test01 {
    public static void main(String[] args) {
        test1(); // 4536
        test2(); // 3498
        test3(); // 369
    }

    public static void test1() {
        long start = System.currentTimeMillis();
        long sum = 0L;
        for (Long i = 1L; i <= 10_0000_0000L ; i++){
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+"时间："+(end - start));
    }

    public static void test2() {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDome01 dome01 = new ForkJoinDome01(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(dome01);
        Long sum = 0L;
        try {
            sum = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+"时间："+(end - start));
    }

    public static void test3() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+"时间："+(end - start));
    }

}
