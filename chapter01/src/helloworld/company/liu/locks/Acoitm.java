package helloworld.company.liu.locks;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: JVMDome
 * @description: 原子类测试
 * @author: liuwenhao
 * @create: 2021-03-09 10:53
 **/
public class Acoitm {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(0);
        integer.incrementAndGet();
        integer.decrementAndGet();
    }
}