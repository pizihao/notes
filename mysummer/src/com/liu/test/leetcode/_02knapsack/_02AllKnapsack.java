package com.liu.test.leetcode._02knapsack;

/**
 * @author shidacaizi
 * @date 2020/7/15 16:34
 */
/*
有N种物品和一个容量是V的背包，每种物品都有无限件可用。
第i种物品的体积是v[i]，价值是w[i]。
求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
输出最大价值。
输入格式
    第一行两个整数, N, V,用空格隔开,分别表示物品种数和背包容积。
    接下来有N行，每行两个整数v[i], w[i],用空格隔开,分别表示第i种物品的体积和价值。
输出格式,
    输出一个整数，表示最大价值。
数据范围
0< N,V≤1000
0< v[i], w[i]≤1000
输入样例
4 5
1 2
2 4
3 4
4 5
输出样例:
10
*/
//完全背包问题
public class _02AllKnapsack {
// dp[i]表示剩余体积是i的情况下，最大价值是多少
//    结果是max{dp[0~V]}
    public static int massage(int N, int V, int[] v,int[] w){
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(massage(4, 5, new int[]{1, 2, 3, 4}, new int[]{2, 4, 4, 5}));
    }
}
