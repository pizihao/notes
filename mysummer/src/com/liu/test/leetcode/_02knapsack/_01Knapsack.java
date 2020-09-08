package com.liu.test.leetcode._02knapsack;

import java.util.Arrays;

/**
 * @author shidacaizi
 * @date 2020/7/14 21:08
 */
/*
* 有N件物品和一个容量是V的背包。
* 第i件物品的体积是v[i]。价值是w[i]。
* 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
* 输出最大价值。
* 输入格式
*  第一行两个整数，N. V,用空格隔开，分别表示物品数量和背包容积。
*  接下来有N行,每行两个整数v[i], w[i],用空格隔开,分别表示第i件物品的体积和价值。
* 输出格式
*   输出一个整数，表示最大价值。
* 数据范围
* 0< N,V≤1000
* 0< v[i], w[i]≤1000
* 输入样例
* 4 5
* 1 2
* 2 4
* 3 4
* 4 5
* 输出样例:
* 8
* */
public class _01Knapsack {
//    dp[i][j] 表示前i个物品中 总体积是j情况下 总价值是多少
//    result = max(dp[i][0],...,dp[i][V])
//    dp[i][j] =max(dp[i-1][j],dp[i-1][j - v[i]])
//    1,如果不选第i个物品 dp[i][j] = dp[i-1][j];
//    2,如果选择第i个物品 dp[i][j] = dp[i-1][j - v[i]] 在剩下的总体积中 减去第i个物品的体积
//    初始化第一个dp[0][0] = 0
    public static int massage(int N, int V, int[] v,int[] w){
        int[] dp = new int[V+1];
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i-1]; j--) {
                    dp[j] =Math.max(dp[j],dp[j - v[i-1]] + w[i-1]);
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[V];
    }
//    public static int massage(int N, int V, int[] v,int[] w){
//        int[][] dp = new int[N+1][V+1];
//        dp[0][0] = 0;
//        for (int i = 1; i <= N; i++) {
//            for (int j = 0; j <= V; j++) {
//                dp[i][j] = dp[i-1][j];
//                if (j >= v[i-1]){
//                    dp[i][j] =Math.max(dp[i][j],dp[i-1][j - v[i-1]] + w[i-1]);
//                }
//            }
//        }
//        System.out.println(Arrays.deepToString(dp));
//        return 0;
//    }
    public static void main(String[] args) {
        System.out.println(massage(4, 5, new int[]{1, 2, 3, 4}, new int[]{2, 4, 4, 5}));
    }
}
