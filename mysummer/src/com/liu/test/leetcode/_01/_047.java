package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/21 10:27
 */
//https://leetcode-cn.com/problems/number-of-segments-in-a-string/
public class _047 {
    public int countSegments(String s) {
        if (s.length() == 0){
            return 0;
        }

        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i-1) == ' ')) {
                n++;
            }
        }
        return n;
    }
}
