package helloworld.company.tacke;

import java.lang.management.MemoryPoolMXBean;

/**
 * @author shidacaizi
 * @date 2020/4/21 9:21
 */
public class Test {

        public boolean found = false;
        public static void main(String[] args) {
            final Test demo = new Test();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("等基友送笔来...");
                    while (!demo.found) {
                    }
                    System.out.println("笔来了，开始写字...");
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("基友找到笔了，送过去...");

                    demo.found = true;
                }
            }).start();
        }
}
