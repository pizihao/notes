package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/12 12:49
 */
//https://leetcode-cn.com/problems/dungeon-game/
public class _032 {
//    dp[i][j]=max(min(dp[i+1][j],dp[i][j+1])−dungeon(i,j),1)
//    dp[i][j]代表从(i,j)到(m,n)也就是终点所需要的最少健康点,也就是和当前房间的数值进行操作之前的数
//    因为所需最少健康点 所以在dp[i+1][j]和dp[i][j+1]中取最小值。这样才能保证dp[i][j]最小
//    同时需要减去dungeon(i,j)
//          解释：如果dungeon(i,j)是正数，那么dp[i][j]会相对减小，也就是说到达终点所需的最小健康点会变小
//               如果dungeon(i,j)是负数，那么dp[i][j]会相对增加，也就是说到达终点所需的最小健康点会变大
//    满足以上条件的同时需要保证dp[i][j]是大于0的，因为初始的健康点数必须是大于0的
//    dp数组声明时的初始值为0，这是不允许的，所以需要将初始值赋值为MAX
    public static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;// 行
        int n = dungeon[0].length;// 列
        int[][] dp = new int[m][n];
        //救完公主后 健康点保持在1，不会死亡的最少点
        dp[m-1][n-1] = Math.max(1 - dungeon[m-1][n-1], 1);
        //填充 边界 即 i=m-1 和 j=n-1时
        //i=m-1时 ，即dp[m-1][j]时，能影响到它的值只有dp[m-1][j+1]，不存在dp[m][j](越界)
        //j=n-1时 ，即dp[i][n-1]时，能影响到它的值只有dp[i-1][n-1]，不存在dp[i][n](越界)
        for(int i = m-2; i >= 0; i--){
            dp[i][n-1] = Math.max(dp[i+1][n-1] - dungeon[i][n-1] , 1) ;
        }
        for(int i = n-2; i >= 0; i--){
            dp[m-1][i] = Math.max(dp[m-1][i+1] - dungeon[m-1][i] , 1);
        }
        //下面是填充除边界外的其他值，即
        for (int i = m - 2; i >= 0; --i) {
            for (int j = n - 2; j >= 0; --j) {
                dp[i][j]=Math.max(Math.min(dp[i+1][j],dp[i][j+1])-dungeon[i][j] ,1);
            }
        }
//        System.out.println(Arrays.deepToString(dp));
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(calculateMinimumHP(new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        }));
    }
}
