package com.liu.test.leetcode._01;

import java.util.Arrays;

/**
 * @author shidacaizi
 * @date 2020/7/16 17:11
 */
//https://leetcode-cn.com/problems/coin-change/
public class _040 {
//    假设 dp[i] 代表总金额为i时，组成它需要的最少硬币个数
//    对于每一个 dp[i] 都有两种情况
//      -不包含当前枚举的硬币面额
//      -包含当前枚举的硬币面额 这样 i 要更改为 {i - 当前枚举的硬币面额}  dp[i]++ 因为增加了一盒硬币
//      -对于第二种情况 必须要确保 i >= 当前枚举的硬币面额
//      -如果不符合第三条 那么就执行第一条
//    dp[0] = 0
    public static int coinChange(int[] coins, int amount) {
        //常规判断
        if (amount == 0) return -1;
        //常规声明
        int[] dp = new int[amount + 1];
        //边界
//        因为要填充最小
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    System.out.println(Arrays.toString(dp));
                }
            }
        }
        if (dp[amount] == Integer.MAX_VALUE){
            return -1;
        }else{
            return dp[amount];
        }
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
    }
}