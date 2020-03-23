package com.liu.thread;

/**
 * @author shidacaizi
 * @data 2020/3/10 10:16
 */
public class ThreadTest03{

    private static String winner;

    @Deprecated
    private static boolean gameover(int steps){
        if (winner != null){
            return true;
        }{
          if (steps == 100){
              winner = Thread.currentThread().getName();
              System.out.println("冠军是"+winner);
              return true;
          }
        }
        return false;
    }

    public static void main(String[] args) {
        Runnable runnable = ()-> {
            for (int i = 0; i <= 100; i++) {
//            让兔子休眠
//            if (Thread.currentThread().getName().equals("兔子")&& i%10 == 0){
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

                Boolean falg = gameover(i);
                if (falg) {
                    break;
                }
                System.out.println(Thread.currentThread().getName()+"跑了"+i+"步");
            }
        };

        new Thread(runnable,"兔子").start();
        new Thread(runnable,"乌龟").start();

    }
}
