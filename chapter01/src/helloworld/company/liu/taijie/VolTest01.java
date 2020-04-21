package helloworld.company.liu.taijie;

/**
 * @author shidacaizi
 * @date 2020/3/15 13:49
 */
public class VolTest01 {
    volatile int number = 0;
    public synchronized void addPuls(){
        number++;
    }

    public static void main(String[] args) {
        VolTest01 volTest01 = new VolTest01();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    volTest01.addPuls();
                }
            },String.valueOf(i)).start();
        }
        //判断除去main线程和GC线程之后已经没有其他的线程
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t OK"+volTest01.number);
    }
}
