package com.liu.test.leetcode._01;

import java.util.Arrays;

/**
 * @author shidacaizi
 * @date 2020/6/30 13:45
 */
public class _010 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
    }
    public static int[] productExceptSelf(int[] nums) {
        int[] ints = new int[nums.length];
        int sum = 1;
        for (int i = 0; i < nums.length; i++) {
            ints[i] = sum;
            sum = nums[i] * sum;
        }
        int next = 1;
        for (int i = nums.length; i > 0; i--) {
            ints[i-1] = next  * ints[i-1];
            next = nums[i-1] * next;
        }
        return ints;
    }
}
