package com.liu.test.softtest.lock;

/**
 * @author shidacaizi
 * @date 2020/8/16 21:07
 */
//单例模式
public class _04 {
//饿汉式(静态变量)
//    class Singleton {
//        // 1，构造器私有化,外部不能new了
//        private Singleton(){}
//        // 2，在本类内部创建对象实例
//        private final static Singleton instance = new Singleton();
//        // 3，提供一个公有的静态方法，返回实例对象
//        public static Singleton getInstance(){
//            return instance;
//        }
//    }
//饿汉式(静态代码块)
//    class Singleton {
//        // 2，在本类内部创建对象实例
//        private static Singleton instance;
//        //在静态代码块中创建单例对象
//        static {
//            instance = new Singleton();
//        }
//        // 1，构造器私有化,外部不能new了
//        private Singleton() {
//        }
//        // 3，提供一个公有的静态方法，返回实例对象
//        public static Singleton getInstance() {
//            return instance;
//        }
//    }
//懒汉式(线程不安全)
//    class Singleton {
//        //静态私有声明
//        private static Singleton singleton;
//        //私有构造器
//        private Singleton(){}
//        //当调用的时候才会创建实例对象
//        public static Singleton getInstance() {
//            if (singleton == null) {
//                singleton =  new Singleton();
//            }
//            return singleton;
//        }
//    }
//懒汉式(线程安全,同步方法)
//    class Singleton {
//        //静态私有声明
//        private static Singleton singleton;
//        //私有构造器
//        private Singleton(){}
//        //当调用的时候才会创建实例对象，
//        //使用synchronized关键字上锁，线程同步
//        public static synchronized Singleton getInstance() {
//            if (singleton == null) {
//                singleton =  new Singleton();
//            }
//            return singleton;
//        }
//    }
//懒汉式(线程安全,同步代码块)
//    class Singleton {
//        //静态私有声明
//        private static Singleton singleton;
//        //私有构造器
//        private Singleton(){}
//        //当调用的时候才会创建实例对象，
//        //使用synchronized关键字上锁，线程同步
//        public static Singleton getInstance() {
//            if (singleton == null) {
//                synchronized(Singleton.class){
//                    singleton =  new Singleton();
//                }
//            }
//            return singleton;
//        }
//    }
//双重检查
//    class Singleton {
//        //静态私有声明，使用volatile关键字
//        private static volatile Singleton singleton;
//        //私有构造器
//        private Singleton(){}
//        //当调用的时候才会创建实例对象，
//        //使用synchronized关键字上锁，线程同步，双重检查
//        public static Singleton getInstance() {
//            if (singleton == null) {
//                synchronized(Singleton.class){
//                    if (singleton == null) {
//                        singleton =  new Singleton();
//                    }
//                }
//            }
//            return singleton;
//        }
//    }
//静态内部类
//    class Singleton {
//        //私有构造器
//        private Singleton(){}
//        //使用静态私有内部类创建实例对象
//        //放外部类被装载时内部类不会被装载
//        //内部类被装载时是线程安全的
//        private static class SingletonInstance {
//            private static final Singleton INSTANCE = new Singleton();
//        }
//        //返回实例对象
//        public static Singleton getInstance() {
//            return SingletonInstance.INSTANCE;
//        }
//    }
//枚举
//    enum Singleton {
//        INSTANCE;
//        public void sayOK(){
//            System.out.println("ok");
//        }
//    }
}
