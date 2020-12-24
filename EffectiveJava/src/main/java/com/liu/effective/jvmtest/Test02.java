package com.liu.effective.jvmtest;

import java.util.concurrent.TimeUnit;

/**
 * @program: JVMDome
 * @description: volatile
 * @author: liuwenhao
 * @create: 2020-11-25 19:53
 **/
/**
 * 1.验证volatile的可见性
 * 1.1假如int number =0 ；number变量之前没有添加volatile修饰，没有可见性,加过之后，及时通知了main线程
 */
public class Test02 {

    volatile  int number = 0;

    public void setNumber() {
        this.number = 1;
    }

    public static void main(String[] args) {
        Test02 est02 = new Test02();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "线程启动");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            est02.setNumber();
            System.out.println(Thread.currentThread().getName() + "修改了值" + est02.number);
        }, "A").start();

        new Thread(() -> {
            while (est02.number == 0) {
            }

            System.out.println(Thread.currentThread().getName() + "结束");
        },"B").start();

    }
}