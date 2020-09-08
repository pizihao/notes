package com.liu.test.leetcode._02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/8/5 14:22
 */
//https://leetcode-cn.com/problems/find-k-closest-elements/
public class _011 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int size = arr.length;

        int left = 0;
        int right = size - 1;

        int removeNums = size - k;
        while (removeNums > 0) {
            if (x - arr[left] <= arr[right] - x) {
                right--;
            } else {
                left++;
            }
            removeNums--;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }
}
