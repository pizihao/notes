package com.liu.test.leetcode._02;

/**
 * @author shidacaizi
 * @date 2020/7/30 18:20
 */
//https://leetcode-cn.com/problems/non-decreasing-array/
public class _010 {
    public boolean checkPossibility(int[] nums) {
        int cnt = 0;
        for(int i = 1; i < nums.length && cnt<=1 ; i++){
            if(nums[i-1] > nums[i]){
                cnt++;
                if(i-2>=0 && nums[i-2] > nums[i]) nums[i] = nums[i-1];
            }
        }
        return cnt<=1;
    }
}
