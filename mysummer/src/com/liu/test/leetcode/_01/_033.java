package com.liu.test.leetcode._01;

import java.util.Arrays;
import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/7/12 15:34
 */
//https://leetcode-cn.com/problems/triangle/
public class _033 {
    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0){
            return 0;
        }
        int[] dp = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0 ; i--) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j < list.size(); j++) {
                dp[j] = Math.min(dp[j],dp[j+1]) + list.get(j);
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[0];
    }
}
