package helloworld.pojo.bq;


/**
 * @author shidacaizi
 * @data 2020/3/12 11:17
 */
public class Test01 {
    private static int a = 100;

    public static void main(String[] args) {
        Test01 test01 = new Test01();

        new Thread(() -> {
            test01.one01();
        }, "a").start();
        new Thread(() -> {
            test01.one01();
        }, "b").start();
        new Thread(() -> {
            test01.one01();
        }, "c").start();
        new Thread(() -> {
            test01.one01();
        }, "d").start();
    }


    public void one01() {
        while (a >= 0) {
            System.out.println(Thread.currentThread().getName() + "--->" + a--);
        }

    }
}

