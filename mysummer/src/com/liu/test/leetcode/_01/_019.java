package com.liu.test.leetcode._01;

import java.util.Arrays;

/**
 * @author shidacaizi
 * @date 2020/7/6 10:05
 */
//https://leetcode-cn.com/problems/unique-paths/
public class _019 {
    public static void main(String[] args) {
        System.out.println(uniquePaths(7, 3));
    }
    //通过题意可以观察出，在其中一个网格向其他的网格移动最多有两种方式
    //向右和向下
    //位置(m,n)的路径数 = 位置(m-1,n)路径数 + 位置(m,n-1)路径数
    //即 dp[m][n] = dp[m-1][n] + dp[m][n-1]
    //当终点在第一行或者第一列的时候，只能有一种走法，这里就是边界
    //即dp[m][0] = 1, dp[0][n] = 1
    //通过这个方程可以看出来，我们只需要当前行的前一列的值和上一行的当前列值即可
    public static int uniquePaths(int m, int n) {
        int[] cur = new int[n];
        //填充这个数组使这个数组中的每一个数都是1
        Arrays.fill(cur,1);
        //遍历行从1开始，0为边界值并且已经初始化值为1
        for (int i = 1; i < m;i++){
            //第一次遍历时为第一行的每2个网格
            //遍历列从1开始，0为边界值并且已经初始化值为1
            for (int j = 1; j < n; j++){
                System.out.println("i = "+ (i+1));
                System.out.println("j = "+ (j+1));
                System.out.println("cur[j-1] = "+ cur[j-1]);
                System.out.println("cur[j] = "+ cur[j]);
                //此时的cur[j]表示上一行第j列的值
                cur[j] = cur[j-1] + cur[j];
                System.out.println("cur[j] = "+ cur[j]);
                System.out.println("+=====================");
                //此时的cur[j]表示当前行第j列的值
                //就是表示只有一个数组，行数每递进一层就用原来的数组的当前下标的值加上数组上一个下标更新后的值
            }
        }
        return cur[n-1];
    }
}
