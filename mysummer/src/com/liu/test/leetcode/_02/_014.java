package com.liu.test.leetcode._02;

/**
 * @author shidacaizi
 * @date 2020/8/9 16:29
 */
//https://leetcode-cn.com/problems/binary-search/
public class _014 {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target){
                left = mid;
            }else if (nums[mid] > target){
                right = mid;
            }else if (nums[mid] == target){
                return mid;
            }
        }
        return -1;
    }
}
