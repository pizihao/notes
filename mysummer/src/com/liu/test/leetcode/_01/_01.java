package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/6/25 10:39
 */
public class _01 {
    public static void main(String[] args) {
        maxArea(new int[]{5,1,1,3,12,2});
    }
    public static int[] moveZeroes(int[] nums) {
        //i用来确定0的位置
        int i = 0;
        for (int j = 0; j < nums.length; ++j) {
            if (nums[j] != 0){
                nums[i] = nums[j];
                if (j != i){
                    nums[j] = 0;
                }
                i++;
            }
        }
        return nums;
    }
    public static void maxArea(int[] a){
        int max = 0;
        for (int i = 0, j = a.length -1; i < j ;){
            int minHeight = a[i] < a[j] ? a[i++] : a[j--];
            int area = (j - i + 1) * minHeight;
            max = Math.max(max,area);
        }
    }

}
