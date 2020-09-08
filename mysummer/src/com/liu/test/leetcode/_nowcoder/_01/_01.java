package com.liu.test.leetcode._nowcoder._01;

/**
 * @author shidacaizi
 * @date 2020/7/28 10:47
 */
//https://www.nowcoder.com/practice/1f7280d9897d4305b2da6790fe131729?tpId=117&&tqId=33605&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
public class _01 {
    /**
     *
     * @param n int整型 只剩下一只蛋糕的时候是在第n天发生的．
     * @return int整型
     */
    public int cakeNumber (int n) {
        // 假设 第i天有res个 那么这一天吃了 (res/3) + 1 个
        // 还剩下 res - res/3 - 1 个  也就是 (2 * res)/3 - 1
        // 那么 i-1 天 就有 res = (res+1) * 3 / 2
        int res = 1;

        for (int i = 0; i < n-1; i++) {
            res = (res+1) * 3 / 2;
        }
        return res;
    }
}
