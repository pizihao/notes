package com.liu.collection;

import java.util.concurrent.TimeUnit;

/**
 * @author shidacaizi
 * @date 2020/3/21 11:56
 */
// 手写单例模式
public class SingletonDemo {
//    懒汉式  线程不安全
//    private static SingletonDemo instance;
//    private SingletonDemo(){}
//    public static SingletonDemo getInstance(){
//        if(instance == null){
//            instance = new SingletonDemo();
//            System.out.println("创建对象");
//        }
//        return instance;
//    }
//    ================================================================================
//   懒汉式   线程安全
//    private static SingletonDemo instance;
//    private SingletonDemo() {}
//    private static synchronized SingletonDemo getInstance() {
//        try {
//            TimeUnit.MICROSECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if (instance == null) {
//            instance = new SingletonDemo();
//            System.out.println("创建线程");
//        }
//        return instance;
//    }
////    两种都可以
////    private static SingletonDemo getInstance(){
////        synchronized (SingletonDemo.class){
////            if (instance == null){
////                instance = new SingletonDemo();
////                System.out.println("创建线程");
////            }
////        }
////        return instance;
////    }
//    ================================================================================
//    饿汉式 线程不安全
//    private SingletonDemo(){}
//    private static final SingletonDemo instance = new SingletonDemo();
//    private static SingletonDemo getInstance(){
//        return instance;
//    }
//    ================================================================================
//   双重检锁机制DCL +
    private static volatile  SingletonDemo instance;
    private SingletonDemo(){}
    private static SingletonDemo getInstance(){
        if (instance == null){ // 如果已经有了实例，线程就不需要排队了
            synchronized (SingletonDemo.class){
                if (instance == null){
                    instance = new SingletonDemo();
                    System.out.println("创建线程");
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
//        SingletonDemo singetonDemo1 = SingletonDemo.getInstance();
//        SingletonDemo singletonDemo2 = SingletonDemo.getInstance();
//        System.out.println(singletonDemo1.hashCode());
//        System.out.println(singletonDemo2.hashCode());

        for (int i = 0; i < 200; i++) {
            new Thread(()->{
                SingletonDemo singetonDemo1 = SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
