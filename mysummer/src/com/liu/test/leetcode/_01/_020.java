package com.liu.test.leetcode._01;

import java.util.Arrays;

/**
 * @author shidacaizi
 * @date 2020/7/6 17:03
 */
//https://leetcode-cn.com/problems/unique-paths-ii/
public class _020 {

    public static void main(String[] args) {
        System.out.println(uniquePathsWithObstacles(new int[][]{
                {0, 0},
                {1, 1},
                {0, 0}
        }));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //n代表行，m代表列
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] res = new int[m];
        if (obstacleGrid[n-1][m-1] == 1 || obstacleGrid[0][0] == 1){
            return 0;
        }
        res[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        //一定要先遍历行
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                System.out.println(Arrays.toString(res));
                //判断是否是障碍物
                if (obstacleGrid[i][j] == 1) {
                    //如果是障碍物，就令该处的赋值为0，并跳过循环
                    //也就是说障碍物后的所有列都无法继续本次循环
                    res[j] = 0;
                    continue;
                }
                //如果不是障碍物
                //同时需要满足 该网格的左边一个也不是障碍物
                //如果该网格的左边是障碍物，那么dp[n][m-1]是无法为该点提供路径的
                if (obstacleGrid[i][j - 1] == 0) {
                    res[j] = res[j-1] + res[j];
                }
                System.out.println(Arrays.toString(res));
            }
        }
        return res[m-1];
    }
}
