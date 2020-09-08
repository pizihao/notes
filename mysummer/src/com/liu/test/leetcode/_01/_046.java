package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/20 9:58
 */
//https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
public class _046 {
//    已知数组是有序的，
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int mid = numbers[left] + numbers[right];
            if (mid == target) {
                return new int[]{left + 1, right + 1};
            } else if (mid > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{0, 0};
    }
}