package com.offer.algorithm;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/**
 * @program: JVMDome
 * @description: Offer 11. 旋转数组的最小数字
 * @author: liuwenhao
 * @create: 2021-02-01 11:50
 * @link https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 **/
public class Offer11 {
    class Solution {
        public int minArray(int[] numbers) {
            int right = numbers.length - 1;
            int left = 0;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (numbers[mid] > numbers[right]) {
                    left = mid + 1;
                } else if (numbers[mid] < numbers[right]) {
                    right = mid;
                } else {
                    right = right - 1;
                }
            }
            return numbers[left];
        }
    }
}