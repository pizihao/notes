package com.offer.algorithm;

/**
 * @program: JVMDome
 * @description: Offer 03
 * @author: liuwenhao
 * @create: 2021-01-31 14:35
 * @link https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 **/
public class Offer03 {
    class Solution {
        public int findRepeatNumber(int[] nums) {
            int[] arr = new int[nums.length];
            for (int num : nums) {
                arr[num]++;
                if (arr[num] > 1) {
                    return num;
                }
            }
            return -1;
        }
    }
}