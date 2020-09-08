package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/12 22:39
 */
//https://leetcode-cn.com/problems/min-cost-climbing-stairs/
public class _034 {
    //假设dp[i]是到达i阶楼梯所需的最小体力数(算上这个阶梯的体力，代表走过了以后)
    //Math.min(dp[i-1] + cost[i], dp[i-2] + cost[i])
    //重要的是最后一步，有可能直接跨过了最后一个阶梯，也就是说最后一个阶梯的体力没有算在内
    //所以输出的值是Math.min(dp[cost.length - 1],dp[cost.length - 2])
    //边界值 dp[0] = cost[0]  dp[1] = cost[1]
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i-1] + cost[i], dp[i-2] + cost[i]);
        }
        return dp[cost.length - 1];
    }
}
