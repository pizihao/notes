package com.offer.algorithm;

/**
 * @program: JVMDome
 * @description: Offer 15. 二进制中1的个数
 * @author: liuwenhao
 * @create: 2021-02-06 17:07
 **/
public class Offer15 {
    public class Solution {
        public int hammingWeight(int n) {
            return Integer.bitCount(n);
        }
    }
}