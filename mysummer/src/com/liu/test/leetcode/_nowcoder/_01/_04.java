package com.liu.test.leetcode._nowcoder._01;

import java.util.Arrays;

/**
 * @author shidacaizi
 * @date 2020/7/29 9:47
 */
//https://www.nowcoder.com/practice/c29467f48afe4f53aaa97db5f7a95e18?tpId=117&&tqId=33627&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
public class _04 {
    /**
     * 扩散
     * @param n int整型
     * @param m int整型
     * @param u int整型一维数组
     * @param v int整型一维数组
     * @param q int整型一维数组
     * @return int整型一维数组
     */
    public static int[] solve (int n, int m, int[] u, int[] v, int[] q) {
        // write code here
        int[] res=new int[n];
        int[] crush=new int[n];
        for(int i=0;i<m;i++){
            if(q[i] <= 0 || q[i] > n){
                continue;
            }

            res[q[i]-1]++;
            crush[q[i]-1]++;

        }
        for(int i = 0;i < n-1; i++){
            res[u[i]-1] = res[u[i]-1] + crush[v[i]-1];
            res[v[i]-1] = res[v[i]-1] + crush[u[i]-1];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solve(4, 2, new int[]{1, 2, 2}, new int[]{2, 3, 4}, new int[]{2,1})));
    }
}
