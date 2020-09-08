package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/10 17:57
 */
//https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
public class _027 {
    public int maxSubArray(int[] nums) {
        if (nums.length < 1 ){
            return 0;
        }
        int[] dp = new int[nums.length];
        int max = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0){
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }
}
