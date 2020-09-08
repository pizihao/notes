package com.liu.test.leetcode._01;

import java.util.Arrays;

/**
 * @author shidacaizi
 * @date 2020/7/8 16:35
 */
//https://leetcode-cn.com/problems/diving-board-lcci/
public class _023 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(divingBoard(2, 2, 2)));
    }
    public static int[] divingBoard(int shorter, int longer, int k) {
        //k=0?
        if (k == 0 ){
            return new int[0];
        }
        //shorter = longer?
        if (shorter == longer){
            return new int[]{longer * k};
        }
        //other
        //对于短木板来说，可能的个数有(0~k)个，所以一共应该有k+1种情况
        int[] res = new int[k+1];
        for (int i = 0; i < k; i++) {
            res[i] = shorter * (k - i) + longer * (i);
        }
        return  res;
    }
}
