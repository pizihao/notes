package com.pojo.unface;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author shidacaizi
 * @data 2020/3/12 9:12
 */
public class InList {
    public static void main(String[] args) {
        //单线程时list集合是安全的

//        List<String> strings = Arrays.asList("1", "2", "3");
//        strings.forEach(System.out :: println);

//        多线程下是不安全的
//        ConcurrentModificationException 并发修改异常
//        解决：1，Collections.synchronizedList(new ArrayList<>());
//        2,JUC new CopyOnWriteArrayList<>();
//        List<Object> arrayList = new ArrayList<>();
//        List<Object> arrayList = Collections.synchronizedList(new ArrayList<>());
        List<String> arrayList = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                arrayList.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(arrayList    );
            },String.valueOf(i)).start();

        }

    }
}
