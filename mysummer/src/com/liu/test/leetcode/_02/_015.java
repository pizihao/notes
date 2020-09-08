package com.liu.test.leetcode._02;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author shidacaizi
 * @date 2020/8/11 12:20
 */
//https://leetcode-cn.com/problems/the-k-weakest-rows-in-a-matrix/submissions/
public class _015 {
    public int[] kWeakestRows(int[][] mat, int k) {
        int[][] tmp = new int[mat.length][2];
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                tmp[i][0] = i;
                if(mat[i][j] == 1)  tmp[i][1] += 1;
            }
        }
        Arrays.sort(tmp, Comparator.comparingInt(o -> o[1]));
        int[] res = new int[k];
        for(int i =0; i < k; i++){
            res[i] = tmp[i][0];
        }
        return res;
    }
}