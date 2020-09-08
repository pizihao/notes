package com.liu.test.leetcode._02;

/**
 * @author shidacaizi
 * @date 2020/7/27 16:02
 */
//https://leetcode-cn.com/problems/remove-outermost-parentheses/
public class _08 {
    public String removeOuterParentheses(String S) {
        StringBuilder str = new StringBuilder();
        int level = 0;
        for (char c : S.toCharArray()) {
            if (c == ')') {
                --level;
            }
            if (level >= 1) {
                str.append(c);
            }
            if (c == '(') {
                ++level;
            }
        }
        return str.toString();
    }
}
