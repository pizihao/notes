package com.liu.test.leetcode._02;

/**
 * 在计算机界中，我们总是追求用有限的资源获取最大的收益。
 * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
 * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 * 注意:
 * 给定 0 和 1 的数量都不会超过 100。
 * 给定字符串数组的长度不会超过 600。
 * https://leetcode-cn.com/problems/ones-and-zeroes/
 * @author 刘文浩
 * @date 2020/9/13 18:54
 * @description TODO
 *
 */
public class _022 {
    public int findMaxForm(String[] strs, int m, int n) {

        /*
        * dp[x][y][z] 代表手中还有 x 个0和 y 个1的时候 在子字符串集 [0,z]中可以拼出的字符串数量
        * 假设对于[0,z]这个区间：在[0,z-1]的时候是dp[x][y][z-1]
        *   那么在z这个位置有两种情况：
        *    --第z个位置的字符串可以被剩下的拼出来 dp[x][y][z] = dp[x - strs[z][0]][y - strs[z][1]][z]
        *    --第z个位置的字符串不可被剩下的拼出来 dp[x][y][z] = dp[x][y][z-1]
        * */

        int z = strs.length - 1;
        int[][][] dp = new int[m][n][z];

        

        return 0;
    }
}
