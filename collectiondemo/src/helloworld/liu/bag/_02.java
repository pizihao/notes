package helloworld.liu.bag;

import java.util.Arrays;

/**
 * @program: JVMDome
 * @description: 完全背包问题
 * @author: liuwenhao
 * @create: 2020-11-13 08:53
 * <p>
 * 有N种物品和一个容量为T的背包，每种物品都就可以选择任意多个，第i种物品的价值为P[i]，体积为V[i]，
 * 求解：选哪些物品放入背包，使得这些物品的价值最大，并且体积总和不超过背包容量。
 **/
public class _02 {
    public static void main(String[] args) {
        System.out.println(lowbag(10, 4, new int[]{2, 3, 4, 7}, new int[]{1, 3, 5, 9}));
    }

    /**
     * @param bags   背包容量 列
     * @param count  物品总数 行
     * @param value  每个的价值
     * @param weight 每个的重量
     * @return int
     * @description: TODO
     * @author liuwenaho
     * @date 2020/11/13 10:37
     */
    public static int bag(int bags, int count, int[] weight, int[] value) {
        /*建立二维数组*/
        int[][] dp = new int[count + 1][bags + 1];
        /*优先遍历行*/
        for (int i = 1; i < count + 1; i++) {
            /*其次遍历列*/
            for (int j = 0; j < bags + 1; j++) {
                if (weight[i - 1] > 0) {
                    /*最后判断一下可以放几个*/
                    for (int k = 0; k * weight[i - 1] <= j; k++) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - (k * weight[i - 1])] + (k * value[i - 1]));
                    }
                }
            }
        }
        return dp[count][bags];
    }

    /**
     * @param bags   背包容量 列
     * @param count  物品总数 行
     * @param value  每个的价值
     * @param weight 每个的重量
     * @return int
     * @description: TODO
     * @author liuwenaho
     * @date 2020/11/13 10:37
     */
    public static int lowbag(int bags, int count, int[] weight, int[] value) {
        /*建立数组*/
        int[] dp = new int[bags + 1];
        /*优先遍历行*/
        for (int i = 1; i < count + 1; i++) {
            /*其次遍历列*/
            for (int j = 0; j < bags + 1; j++) {
                if (j >= (weight[i - 1])) {
                    dp[j] = Math.max(dp[j], dp[j - (weight[i - 1])] + (value[i - 1]));
                    System.out.println(Arrays.toString(dp));
                }
            }
        }
        return dp[bags];
    }
}