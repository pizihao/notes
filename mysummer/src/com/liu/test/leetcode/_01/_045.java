package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/19 15:47
 */
//https://leetcode-cn.com/problems/coin-change-2/
public class _045 {
//  假设dp[i][j]代表总金额为j时 对于coins[0~(i-1)] 有多少种组合数
//  关于第i个硬币 有两种情况 选或不选 两者之和就是总的选择数
//      dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]]
//  如果j<coins[i-1]
//      dp[i][j] = dp[i-1][j]
//    public int change(int amount, int[] coins) {
//        int[][] dp = new int[coins.length][amount + 1];
//        for (int i = 0; i < coins.length; i++) {
//            dp[0][i] = 1;
//        }
//        for (int i = 1; i < coins.length; i++) {
//            for (int j = 0; j <= amount; j++) {
//                dp[i][j] = dp[i-1][j];
//                if (j>=coins[i]){
//                    dp[i][j] = dp[i][j] + dp[i][j-coins[i-1]];
//                }
//            }
//        }
//        System.out.println(Arrays.deepToString(dp));
//        return dp[coins.length - 1][amount];
//    }
//  对于上边的代码我们可以进行优化
    public int change(int amount, int[] coins) {

        if (amount == 0){
            return 1;
        }

        int[] dp = new int[amount + 1];
        for (int i = 0; i < coins.length; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j>=coins[i]){
                    dp[j] = dp[j] + dp[j-coins[i-1]];
                }
            }
        }
        return dp[amount];
    }
}