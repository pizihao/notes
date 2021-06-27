package helloworld.company.java;

import java.util.Collection;

/**
 * @Description TODO
 * @Author shidacaizi
 * @Date 2020/2/21 22:01
 */
public class HelloLoader {
    private static int a = 1;
    static{
        a = 5;
    }
    public static void main(String[] args) {
//        System.out.println("这里是由ClassLoader加载的");
//        System.out.println("加载完毕。。。");
        System.out.println(a);
    }
}
