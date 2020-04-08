package com.hao.subject._06;

/**
 * @author shidacaizi
 * @date 2020/4/7 10:29
 */
/*
 * 6、矩形重叠
 * 平面内有n个矩形, 第i个矩形的左下角坐标为(x1[i], y1[i]), 右上角坐标为(x2[i], y2[i])。
 * 如果两个或者多个矩形有公共区域则认为它们是相互重叠的(不考虑边界和角落)。
 * 请你计算出平面内重叠矩形数量最多的地方,有多少个矩形相互重叠。
 * 输入描述:
 * 输入包括五行。
 * 第一行包括一个整数n(2 <= n <= 50), 表示矩形的个数。
 * 第二行包括n个整数x1[i](-10^9 <= x1[i] <= 10^9),表示左下角的横坐标。
 * 第三行包括n个整数y1[i](-10^9 <= y1[i] <= 10^9),表示左下角的纵坐标。
 * 第四行包括n个整数x2[i](-10^9 <= x2[i] <= 10^9),表示右上角的横坐标。
 * 第五行包括n个整数y2[i](-10^9 <= y2[i] <= 10^9),表示右上角的纵坐标。
 * 输出描述:
 * 输出一个正整数, 表示最多的地方有多少个矩形相互重叠,如果矩形都不互相重叠,输出1。
 * 输入例子1:
 * 2
 * 0 90
 * 0 90
 * 100 200
 * 100 200
 * 输出例子1:
 * 2
 */
public class Matirx {
    String str = new String("good");
    char[] ch = {'a','b','c'};
    public static void main(String[] args) {
        Matirx ex = new Matirx();
        ex.change(ex.str, ex.ch);
        System.out.print(ex.str +"and");
        System.out.print(ex.ch);
    }
    public void change(String str, char ch[]){
        str= "test ok";
        ch[0]= 'g';
    }
}
