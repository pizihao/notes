package com.company.liu.taijie;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author shidacaizi
 * @date 2020/3/14 12:18
 * 编程题:有n步台阶，1次只能上1步或2步，共有多少种走法?
 */

public class ForWordTest01 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        System.out.println(new ForWordTest01().temp(100));
        System.out.println(new ForWordTest01().loop(100));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    //使用递归
    public int temp(int n) {
        if (n < 1) throw new IllegalArgumentException(n + "参数不能小于1");
        if (n == 1 || n == 2) {
            return n;
        } else {
            return temp(n - 2) + temp(n - 1);
        }
    }

    //使用迭代循环
    public int loop(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int one = 2;//初始化为走到第二级台阶的走法
        int two = 1;//初始化为走到第一级台阶的走法
        int sum = 0;
        for (int i = 3; i <= n; i++) {//最后跨2步+最后跨1步的走法
            sum = two + one;
            two = one;
            one = sum;
        }
        return sum;
    }
}