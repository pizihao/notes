package helloworld.liu.bag;

import java.util.Arrays;

/**
 * @program: JVMDome
 * @description: 背包问题 0/1背包
 * @author: liuwenhao
 * @create: 2020-11-12 17:26
 *
 * 有N件物品和一个容量为V的背包，第i件物品消耗的容量为Ci，价值为Wi，求解放入哪些物品可以使得背包中总价值最大。
 * */
public class _01 {
    public static void main(String[] args) {
        System.out.println(lowbag(100, 5, new int[]{77,22,29,50,99}, new int[]{92,22,87,46,90}));
    }

    /**
     * @description: TODO
     * @param bags 背包容量 列
     * @param count 物品总数 行
     * @param value 每个的价值
     * @param weight 每个的重量
     * @return int
     * @author liuwenaho
     * @date 2020/11/12 20:03
     */
    public static int bag(int bags, int count, int[] weight, int[] value){
        /*定义一个二维数组*/
        int[][] dp = new int[count+1][bags+1];
        /*判断当前的而是否可以放入背包中*/
        for (int i = 1; i < count+1; i++) {
            for (int j = 1; j < bags+1; j++) {
                if (weight[i-1] > j){
                    /*放不下的情况*/
                    dp[i][j] = dp[i-1][j];
                } else {
                    /*放的下的情况*/
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i-1]] + value[i-1]);
                }
            }
        }
        /*确定最后的最大值*/
        return dp[count][bags];
    }

    /**
     * @description: TODO
     * @param bags 背包容量 列
     * @param count 物品总数 行
     * @param value 每个的价值
     * @param weight 每个的重量
     * @return int
     * @author liuwenaho
     * @date 2020/11/12 20:03
     */
    public static int lowbag(int bags, int count, int[] weight, int[] value){
        /*定义一个数组*/
        int[] dp = new int[bags+1];
        /*判断当前的而是否可以放入背包中*/
        for (int i = 0; i < count; i++) {
            for (int j = bags; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }

//	如果正序进行的话 前边的值会随着数组下标的移动而发生变化，从而影响结果
//	这里需要从后向前进行迭代
//        for (int i = 0; i < count; i++) {
//            for (int j = 0; j <= bags; j++) {
//                if (j >= weight[i]){
//                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
//                }
//            }
//        }
        /*确定最后的最大值*/
        return dp[bags];
    }

}