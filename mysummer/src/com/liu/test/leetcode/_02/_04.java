package com.liu.test.leetcode._02;

/**
 * @author shidacaizi
 * @date 2020/7/25 13:50
 */
//https://leetcode-cn.com/problems/valid-perfect-square/
public class _04 {
    public boolean isPerfectSquare(int num) {
        if (num == 0) return false;
        if (num == 1) return true;
        //使用二分查找
        long left = 2L;
        long right = num/2;
        while (left < right){
            long mid = left + (right - left)/2;
            long a = mid * mid;
            if (a > num){
                right = mid + 1;
            }else if (a < num){
                left = mid;
            }else{
                return true;
            }
        }
        return false;
    }
}
