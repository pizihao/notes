package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/6/30 16:36
 */
//https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
public class _012 {
    private int[][] matrix;
    private int target;
    private boolean searchRec(int left, int up, int right, int down) {
        if (left > right || up > down) {
            return false;
        } else if (target < matrix[up][left] || target > matrix[down][right]) {
            return false;
        }
        int mid = left + (right-left)/2;
        int row = up;
        while (row <= down && matrix[row][mid] <= target) {
            if (matrix[row][mid] == target) {
                return true;
            }
            row++;
        }
        return searchRec(left, row, mid-1, down) || searchRec(mid+1, up, right, row-1);
    }
    public boolean searchMatrix(int[][] mat, int targ) {
        matrix = mat;
        target = targ;
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        return searchRec(0, 0, matrix[0].length-1, matrix.length-1);
    }
}
