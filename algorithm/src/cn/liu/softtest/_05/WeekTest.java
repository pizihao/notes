package cn.liu.softtest._05;

import cn.hutool.core.date.DateUtil;

import java.util.Scanner;

/**
 * @author shidacaizi
 * @date 2020/4/4 9:43
 */
/*
 *	5、设计计算日期是星期几的程序
 * 	已知公元1年1月1日是星期一。
 * 	编写一个程序，只要输入年月日，就能回答那天是星期几。
 */
public class WeekTest {
    public static void main(String[] args) {
        Week week = new Week();
        Scanner in = new Scanner(System.in);
        week.weekes(in);
        in.close();
    }
}
