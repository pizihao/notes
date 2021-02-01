package com.offer.algorithm;

/**
 * @program: JVMDome
 * @description: 剑指 Offer 12. 矩阵中的路径
 * @author: liuwenhao
 * @create: 2021-02-01 13:34
 * @link https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
 **/
public class Offer12 {
    class Solution {
        public boolean exist(char[][] board, String word) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (dfSearch(board, chars, i, j, 0)){
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean dfSearch(char[][] board, char[] chars, int col, int row, int index) {
            //根据当前节点的前后左右四个位置的值，判断各个新位置的数据是否和word数组中的数据一致
            //判断数值合法性
            if (col < 0 || row < 0) {
                return false;
            }
            //判断越界情况
            if (col >= board.length || row >= board[0].length) {
                return false;
            }
            //判断值是否是期望值
            if (board[col][row] != chars[index]) {
                return false;
            }
            //判断word是否已经判断完
            if (index == chars.length - 1) {
                return true;
            }
            //对board[col][row]进行标记，表示已经走过了
            board[col][row] = '/';

            boolean b = (dfSearch(board, chars, col + 1, row, index + 1) ||
                    dfSearch(board, chars, col - 1, row, index + 1) ||
                    dfSearch(board, chars, col, row + 1, index + 1) ||
                    dfSearch(board, chars, col, row - 1, index + 1));
            //还原
            board[col][row] = chars[index];
            return b;
        }
    }
}