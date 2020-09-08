package com.liu.test.leetcode._01;

import java.util.Arrays;

/**
 * @author shidacaizi
 * @date 2020/7/11 19:21
 */
//https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
public class _031 {
    public static void main(String[] args) {
        System.out.println(findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
    }
    public static int findRepeatNumber(int[] nums) {
        int[] arr = new int[nums.length];
        for (int num : nums) {
            arr[num]++;
            System.out.println(Arrays.toString(arr));
            //那个大于1那个就是出现了重复
            if (arr[num] > 1) return num;
        }
        return -1;
    }
}
