package helloworld.liu.bag;

/**
 * @program: JVMDome
 * @description: 多重背包问题
 * @author: liuwenhao
 * @create: 2020-11-13 13:29
 *
 * 有N种物品和一个容量为T的背包，第i种物品最多有M[i]件可用，
 * 价值为P[i]，体积为V[i]，求解：选哪些物品放入背包，可以使得这些物品的价值最大，
 * 并且体积总和不超过背包容量。
 **/
public class _03 {
    public static void main(String[] args) {
        System.out.println(bag(15, 3, new int[]{3, 4, 5}, new int[]{2, 3, 4}, new int[]{4, 3, 2}));
    }

    /**
     * @description: TODO
     * @param bags 背包容量
     * @param count 物品总数
     * @param weight 每件物品重量
     * @param value 每件物品价值
     * @param num 每件物品
     * @return int
     * @author liuwenaho
     * @date 2020/11/13 13:33
     */
    public static int bag(int bags, int count, int[] weight, int[] value, int[] num){
        int[][] dp = new int[count+1][bags+1];
        /*优先遍历行*/
        for (int i = 1; i < count + 1; i++) {
            /*其次遍历列*/
            for (int j = 0; j < bags + 1; j++) {
                if (weight[i - 1] > 0) {
                    /*最后判断一下可以放几个*/
                    for (int k = 0; k <= num[i - 1] && k * weight[i - 1] <= j; k++) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - (k * weight[i - 1])] + (k * value[i - 1]));
                    }
                }
            }
        }
        return dp[count][bags];
    }
}