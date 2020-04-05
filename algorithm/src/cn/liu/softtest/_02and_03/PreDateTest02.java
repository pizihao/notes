package cn.liu.softtest._02and_03;

import cn.hutool.core.date.DateException;

import java.util.Scanner;

/**
 * @author shidacaizi
 * @date 2020/4/4 0:29
 */
/*
* 3、设计隔一天日期函数的程序
* 程序有三个输入变量month、day、year
* （month、day和year均为整数值，并且满足：1≤month≤12、1≤day≤31、1900≤ year ≤2050。），
* 分别作为输入日期的月份、日、年份，通过程序可以输出该输入日期在日历上隔一天的日期。
* 例如，输入为2005年11月29日，则该程序的输出为2005年12月1日。
* */
public class PreDateTest02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PreDate preDate = new PreDate();
        try {
            System.out.println(preDate.sepaDate(in));
        } catch (DateException e) {
            System.out.println("日期格式不正确，请重新输入");
        }
        in.close();
    }
}
