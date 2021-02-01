package com.offer.algorithm;

/**
 * @program: JVMDome
 * @description: Offer 10- I
 * @author: liuwenhao
 * @create: 2021-01-31 14:10
 * @link https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 **/
public class Offer10I {
    class Solution {
        public int fib(int n) {
            int a = 0;
            int b = 1;
            int f;
            for (int i = 0; i < n; i++) {
                f = (a + b) % 1000000007;
                a = b;
                b = f;
            }
            return a;
        }
    }
}