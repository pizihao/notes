package com.pojo.unface;

import java.awt.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author shidacaizi
 * @data 2020/3/12 9:40
 */
public class InSet {
    public static void main(String[] args) {
//        多线程时不安全
//        解决方案1，Collections.synchronizedSet(new HashSet<>());
//        2,new CopyOnWriteArraySet<>();
        Set<String> set = new HashSet<>();
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());
//        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set    );
            },String.valueOf(i)).start();
    
        }
    
    }
}
