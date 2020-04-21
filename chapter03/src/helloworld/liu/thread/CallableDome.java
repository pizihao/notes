package helloworld.liu.thread;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author shidacaizi
 * @data 2020/3/10 8:16
 */
public class CallableDome {

    public static void main(String[] args) {

        FutureTask<Integer> task = new FutureTask<>((Callable<Integer>) () -> {
            int i;
            for (i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"===当前线程名称==="+ i);
            }
            return i;
        });

        new Thread(task).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName()+"============这里是MAIN方法"+i);
        }

        try {
            Integer freturn = task.get();
            System.out.println(freturn);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
