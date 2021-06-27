package helloworld.pojo.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author shidacaizi
 * @data 2020/3/12 15:46
 */
public class ForkJoinDome01 extends RecursiveTask<Long> {

    private static final long serialVersionUID = -7274126236233552589L;
    private final Long start;
    private final Long end;

    public ForkJoinDome01(Long start, Long end) {
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        long temp = 1000L;
        if ((end - start) > temp){
           long sum = 0;
           for (Long i = start; i <= end ; i++){
                sum += i;
           }
           return sum;
        }else{
            long middle = (start + end);
            ForkJoinDome01 forkJoinDome01 = new ForkJoinDome01(start, middle);
            forkJoinDome01.fork();
            ForkJoinDome01 forkJoinDome02 = new ForkJoinDome01(middle+1, end);
            forkJoinDome02.fork();
            return forkJoinDome01.join() + forkJoinDome02.join();
        }
    }
}
