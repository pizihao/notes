package com.liu.wangyi._03;

import java.util.*;

/**
 * @author shidacaizi
 * @date 2020/4/7 20:28
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int f = in.nextInt();

        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < m; i++) {
            int q = in.nextInt();
            int[] Q = new int[q];
            Boolean c = false;
            for (int j = 0; j < q; j++) {
                int d  = in.nextInt();
                if (d == f){
                    c = true;
                }
                Q[j] = d;
            }
            if (c){
                for (int j = 0; j < q ; j++) {
                    list.add(Q[j]);
                }
            }
        }
        set.addAll(list);
        System.out.println(set.size()>n ? n : set.size());
        in.close();
    }
}
