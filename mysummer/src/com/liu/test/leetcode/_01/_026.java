package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/10 14:46
 */
//https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
public class _026 {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{
                2, 6, 1, 3
        }));
    }

    //    public static int maxProfit(int[] prices) {
//        if (prices.length <= 1){
//            return 0;
//        }
//        int max = 0;
//        for (int i = 0; i < prices.length - 1; i++) {
//            for (int j = i+1; j < prices.length; j++) {
//                max = Math.max(prices[j] - prices[i], max);
//                System.out.println(max);
//            }
//        }
//        return max;
//    }
    public static int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }

        //res代表不持有股票时的利润
        int res = 0;
        //nres代表持有股票时的利润
        int nres = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            res = Math.max(res, nres + prices[i]);
            nres = Math.max(nres, -prices[i]);
        }
        //返回值一定是不持有股票的
        return res;
    }
}
