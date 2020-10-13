package com.liu.test.leetcode._02;


import java.util.Arrays;

/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * <p>
 * - 每行中的整数从左到右按升序排列。
 * - 每行的第一个整数大于前一行的最后一个整数。
 * <p>
 * https://leetcode-cn.com/problems/search-a-2d-matrix/
 * @author 刘文浩
 * @date 2020/9/13 17:16
 * @description 二分查找
 */
public class _021 {

    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{
                {1,3}
        }, 4));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {

        if (matrix.length < 1) return false;
        int row = matrix.length;
        int col = matrix[0].length;
//        使用第row列
        int index = 0;//默认在第0行
        for (int i = 0; i < row - 1; i++) {
            System.out.println(row);

            System.out.println(col - 1);
            if (row > 1 && matrix[i][col - 1] < target && matrix[i+1][col - 1] >= target){
                index = i + 1;
            }
        }
//        System.out.println(index);
//        对第index行使用二分查找，直到找到目标
        int left = 0;
        int right = col - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(matrix[index][mid] == target){
                return true;
            }else if(matrix[index][mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return false;
    }
}
