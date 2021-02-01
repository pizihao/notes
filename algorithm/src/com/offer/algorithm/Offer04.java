package com.offer.algorithm;

/**
 * @program: JVMDome
 * @description: Offer 04. 二维数组中的查找
 * @author: liuwenhao
 * @create: 2021-01-31 16:23
 * @link https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 **/
public class Offer04 {
    class Solution {
        public boolean findNumberIn2DArray(int[][] matrix, int target) {

            if (matrix.length == 0){
                return false;
            }

            int col = matrix[0].length - 1;
            int row = 0;

            while (row < matrix.length && col >= 0){
                int a = matrix[row][col];
                if (target == a){
                    return true;
                }
                if (target > a){
                    row++;
                }else{
                    col--;
                }
            }
            return false;
        }
    }
}