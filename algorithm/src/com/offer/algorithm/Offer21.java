package com.offer.algorithm;

/**
 * @program: JVMDome
 * @description: Offer 21. 调整数组顺序使奇数位于偶数前面
 * @author: liuwenhao
 * @create: 2021-02-06 17:06
 **/
public class Offer21 {
    class Solution {
        public int[] exchange(int[] nums) {
            if(nums.length < 2) {
                return nums;
            }
            int l = 0;
            int r = nums.length - 1;
            while(l < r){
                while((nums[l] & 1) == 1 && l < r) {
                    l++;
                }
                while((nums[r] & 1) == 0 && l < r) {
                    r--;
                }
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
                l++;
                r--;
            }
            return nums;
        }
    }
}