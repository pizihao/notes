package com.liu.test.leetcode._02;

/**
 * A.length >= 3 这是一个条件
 * 其中A的数值先递增 再递减 如果有值的话
 * 要求最大值所在的下标
 * https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/
 * @author shidacaizi
 * @date 2020/8/12 17:09
 */
public class _016 {
    public int peakIndexInMountainArray(int[] A) {
//        使用二分查找 因为A已经确定为山脉 所以 A.length >= 3 为true
        int left = 0;//左边界
        int right = A.length - 1;//右边界
        while (left < right) {
            int mid = left + (right - left) / 2;//中间位置
            if (A[mid] > A[mid + 1]) {
                // mid位置的值大于mid+1的值 说明最大值的位置在mid或者mid前面
                right = mid;
            } else {
                //因为符合山峰 所以没有相等的值
                left = mid + 1;
            }
        }
        //最后左边的值就是最大值的下标
        return left;
    }
}
