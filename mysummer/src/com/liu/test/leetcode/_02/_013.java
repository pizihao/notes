package com.liu.test.leetcode._02;

/**
 * @author shidacaizi
 * @date 2020/8/8 16:09
 */
//https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/
public class _013 {
    public int missingNumber(int[] nums) {
            int i = 0, j = nums.length - 1;
            while(i <= j) {
                int m = (i + j) / 2;
                if(nums[m] == m) i = m + 1;
                else j = m - 1;
            }
            return i;
        }
}
