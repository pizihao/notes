package com.liu.test.leetcode._02;

/**
 * 给你一个 m * n 的矩阵 grid，矩阵中的元素无论是按行还是按列，都以非递增顺序排列。
 * 请你统计并返回 grid 中 负数 的数目。
 *
 * https://leetcode-cn.com/problems/count-negative-numbers-in-a-sorted-matrix/
 * @author 刘文浩
 * @date 2020/9/13 10:01
 * @description 不论按行还是按列 自左向右不会变大
 */
public class _019 {

    public static void main(String[] args) {
        System.out.println(countNegatives(new int[][]{
                {4, 3, 2, -1},
                {3, 2, 1, -1},
                {1, 1, -1, -2},
                {-1, -1, -2, -3}
        }));
    }

    public static int countNegatives(int[][] grid) {
        int num = 0;
        for (int i = 0; i <= grid.length - 1; i++){
            int left = 0;
            int right = grid[i].length - 1;
            while (left <= right){
                int mid = left + (right - left) / 2;
                if (grid[i][mid] < 0){
                    num += right - mid + 1;
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
        }
        return num;
    }
}
