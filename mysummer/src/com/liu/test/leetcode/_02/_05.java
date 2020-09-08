package com.liu.test.leetcode._02;

import java.util.Arrays;

/**
 * @author shidacaizi
 * @date 2020/7/25 18:02
 */
//https://leetcode-cn.com/problems/ugly-number-ii/
public class _05 {
//解题思路
//  在最小堆方法中，我们的思路是把当前丑数能生成的丑数都加入到堆中，
//      然后再弹出最小值。如果我们能知道下一个最小的丑数，
//      每次只生成最小的那个，就可以节省最小值查询的时间消耗。
//  采用动态规划的方法，用一个有序数组dp记录前n个丑数。三个指针l2，
//      l3和l5指向dp中的元素，最小的丑数只可能出现在dp[l2]的2倍、
//      dp[l3]的3倍和dp[l5]的5倍三者中间。通过移动三个指针，
//      就能保证生成的丑数是有序的。
//算法流程
//  初始化数组dp和三个指针l2、l3和l5。dp[0] = 1，表示最小的丑数为1。
//      三个指针都指向dp[0]。
//  重复以下步骤n次，dp[i]表示第i + 1小的丑数：
//  比较2 * dp[l2], 3 * dp[l3], 5 * dp[l5]三者大小，
//      令dp[i]为其中的最小值。
//  如果dp[i] == 2 * dp[l2]，l2指针后移一位。
//  如果dp[i] == 3 * dp[l3]，l3指针后移一位。
//  如果dp[i] == 2 * dp[l5]，l5指针后移一位。
//  dp[n - 1]即为第n小的丑数，返回。
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for(int i = 1; i < n; ++i) {
            dp[i] = Math.min(Math.min(dp[p2] * 2, dp[p3] * 3), dp[p5] * 5);
            if (dp[i] == dp[p2] * 2) ++p2;
            if (dp[i] == dp[p3] * 3) ++p3;
            if (dp[i] == dp[p5] * 5) ++p5;
            System.out.println(Arrays.toString(dp));
        }
        return dp[n - 1];
    }
}
