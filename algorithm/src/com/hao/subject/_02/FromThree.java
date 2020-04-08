package com.hao.subject._02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shidacaizi
 * @date 2020/4/6 12:17
 */
/*
 * 2、被3整除
 * 小Q得到一个神奇的数列: 1, 12, 123,...12345678910,1234567891011...。
 * 并且小Q对于能否被3整除这个性质很感兴趣。
 * 小Q现在希望你能帮他计算一下从数列的第l个到第r个(包含端点)有多少个数可以被3整除。
 * 输入描述:
 * 输入包括两个整数l和r(1 <= l <= r <= 1e9), 表示要求解的区间两端。
 * 输出描述:
 * 输出一个整数, 表示区间内能被3整除的数字个数。
 * 输入例子1:
 * 2 5
 * 输出例子1:
 * 3
 * 例子说明1:
 * 12, 123, 1234, 12345...
 * 其中12, 123, 12345能被3整除。
 */
public class FromThree {
    public static void main(String[] args) {
        StringBuffer init = new StringBuffer(); // 初始数 l前面的数
        List<String> list = new ArrayList<>(); // 存放l个到r个的数
        Scanner in = new Scanner(System.in);
        AtomicInteger num = new AtomicInteger();
        int l = in.nextInt();
        int r = in.nextInt();

        for (int i = 1; i < l; i++) {
            init.append(i);
        }
        for (; l <= r; l++) {
            init.append(l);
            list.add(init.toString());
        }
        list.forEach(s -> {
            String[] a = s.toString().split("");
            int all = 0;
            for (int i = 0; i < a.length; i++) {
                all = all + Integer.parseInt(a[i]);
            }
            if (all%3 == 0){
                num.getAndIncrement();
            }
        });
        in.close();
        System.out.println(num.get());
    }
}
