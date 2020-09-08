package com.liu.test.leetcode._nowcoder._01;

/**
 * @author shidacaizi
 * @date 2020/7/28 18:55
 */
//https://www.nowcoder.com/practice/ac72e27f34c94856bf62b19f949b8f36?tpId=117&&tqId=33619&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
public class _03 {
    /**
     *
     * @param s string字符串
     * @return int整型
     */
    public static int solve (String s) {
        // write code here
        if(s==null || s.isEmpty()){
            return 0;
        }

        int res = Integer.MIN_VALUE;
        int temp = 0;

        char[] str = s.toCharArray();
        for (char c : str) {
            if (c >= '0' && c <= '9') {
                temp *= 16;
                temp += (c - '0');
            } else if (c >= 'A' && c <= 'F') {
                temp *= 16;
                temp += (c - 'A' + 10);
            } else {
                res = Math.max(temp, res);
                temp = 0;
            }
        }

        if (res == Integer.MIN_VALUE){
            res = temp;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(solve("9FA8C"));
    }
}
