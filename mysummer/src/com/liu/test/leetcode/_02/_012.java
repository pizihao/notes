package com.liu.test.leetcode._02;

/**
 * @author shidacaizi
 * @date 2020/8/5 16:54
 */
//https://leetcode-cn.com/problems/arranging-coins/
public class _012 {
    public int arrangeCoins(int n) {
        int low = 0, high = n;
        while (low <= high) {
            long mid = (high - low) / 2 + low;
            //cost是mid行所需的硬币总数
            long cost = ((mid + 1) * mid) / 2;
            if (cost == n) {
                return (int)mid;
            } else if (cost > n) {
                high = (int)mid - 1;
            } else {
                low = (int)mid + 1;
            }
        }
        return high;
    }
}
