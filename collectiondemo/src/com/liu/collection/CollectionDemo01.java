package com.liu.collection;

import sun.misc.Queue;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author shidacaizi
 * @date 2020/3/20 20:51
 */

//  (/\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+/) 去除注释
public class CollectionDemo01 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new LinkedList<>();
        List<Integer> list1 = new CopyOnWriteArrayList();
        System.out.println(list1.size());
        Collections.addAll(list1, 1);
        System.out.println(list1.size());

        AbstractQueue<Integer> integers = new PriorityQueue<>();

        HashMap map = new HashMap();
    }
}