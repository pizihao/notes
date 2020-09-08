package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/13 23:22
 */
//https://leetcode-cn.com/problems/climbing-stairs/
public class _039 {
    public int climbStairs(int n) {
        if(n == 1) return 2;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i-2] + dp[i-1];
        }
        return dp[n - 1];
    }
}
