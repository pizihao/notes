package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/13 22:45
 */
//https://leetcode-cn.com/problems/minimum-path-sum/
public class _038 {
    //二维DP
//    public int minPathSum(int[][] grid) {
//        int m = grid.length;
//        int n = grid[0].length;
//        int[][] dp = new int[m][n];
//        dp[0][0] = grid[0][0];
//        for (int i = 1; i < m; i++) {
//            dp[i][0] = dp[i-1][0] + grid[i][0];
//        }
//        for (int i = 1; i < n; i++) {
//            dp[0][i] = dp[0][i-1] + grid[0][i];
//        }
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
//            }
//        }
//        System.out.println(Arrays.deepToString(dp));
//        return dp[m-1][n-1];
//    }
    //一维DP
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i-1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            dp[0] = grid[i][0]+dp[0];
            for (int j = 1; j < n; j++) {
                // System.out.println(Arrays.toString(dp));
                dp[j] = Math.min(dp[j],dp[j-1]) + grid[i][j];
            }
        }
        return dp[n-1];
    }
}