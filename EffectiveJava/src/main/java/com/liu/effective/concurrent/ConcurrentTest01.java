package com.liu.effective.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @program: JVMDome
 * @description: ConcurrentHashMap
 * @author: liuwenhao
 * @create: 2020-12-24 11:16
 **/
public class ConcurrentTest01 {
    public static void main(String[] args) {

        ConcurrentHashMap<Integer, Integer> hashMap = new ConcurrentHashMap<>(16);

        IntStream.range(1,20).forEach(value -> {
            hashMap.put(value,value + 1);
        });
        System.out.println(hashMap.getOrDefault(30, 500));

    }
}