package com.liu.test.leetcode._02;

/**
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * @link https://leetcode-cn.com/problems/sqrtx/
 * @author 刘文浩
 * @date 2020/9/12 22:01
 * @description 二分查找，用中间值来确定，用中间值的平方 m 和 x 进行比较
 * m > x 则选取左侧部分
 * 其余情况则选择右侧部分 同时
 */
public class _017 {
    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }
    public static int mySqrt(int x) {
        int left = 0;
        int right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long)mid * mid > x) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
//            System.out.println(mid);
//            System.out.println(left);
//            System.out.println(right);
        }
        return right;
    }
}
