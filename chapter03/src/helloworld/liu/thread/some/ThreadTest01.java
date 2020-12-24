package helloworld.liu.thread.some;

/**
 * @author shidacaizi
 * @data 2020/3/10 8:53
 */
public class ThreadTest01 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("=====这里是run()====="+i);
        }
    }

    public static void main(String[] args) {
        new ThreadTest01().start();

        for (int i = 0; i < 300; i++) {
            System.out.println("=====这里是main()===="+i);
        }
    }
}
