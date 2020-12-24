package helloworld.liu.thread.some;

/**
 * @author shidacaizi
 * @data 2020/3/10 9:57
 */
public class ThreadTest02 implements Runnable{
    private int tickes = 2000;


    @Override
    public void run() {
        for (;;) {
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized(this){
                if (tickes <= 0 ){
                    break;
                }
                System.out.println(Thread.currentThread().getName()+"=====您的票号为："+tickes--);
            }
        }
    }

    public static void main(String[] args) {
        ThreadTest02 test2 = new ThreadTest02();

        new Thread(test2,"刘文浩").start();
        new Thread(test2,"CD-开阳").start();
        new Thread(test2,"十大才子之首").start();
    }
}
