package com.offer.algorithm;

/**
 * @program: JVMDome
 * @description: Offer 10- II. 青蛙跳台阶问题
 * @author: liuwenhao
 * @create: 2021-02-01 10:43
 * @link https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
 **/
public class Offer10II {
    class Solution {
        public int numWays(int n) {
            int a = 0;
            int b = 1;
            if (n == 0 || n ==1){
                return 1;
            }
            int sum;
            for (int i = 0; i < n + 1; i++) {
                sum = (a + b) % 1000000007;
                a = b;
                b = sum;
            }
            return a;
        }
    }
}