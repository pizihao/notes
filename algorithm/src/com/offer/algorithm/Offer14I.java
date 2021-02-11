package com.offer.algorithm;

/**
 * @program: JVMDome
 * @description: Offer 14- I. 剪绳子
 * @author: liuwenhao
 * @create: 2021-02-06 16:23
 * @link https://leetcode-cn.com/problems/jian-sheng-zi-lcof/
 **/
public class Offer14I {
    class Solution {
        public int cuttingRope(int n) {
            if (n <= 3) {
                return n - 1;
            }
            int div = n / 3;
            int rem = n % 3;
            long result = 1;
            for (int i = 0; i < div; i++) {
                result *= i < div - 1 ? 3 : (rem == 2 ? 3 * rem : (3 + rem));
                if (result >= 1000000007) {
                    result = result % 1000000007;
                }
            }
            return (int) result;
        }
    }
}