package com.liu.test.leetcode._01;

import java.util.Stack;

/**
 * @author shidacaizi
 * @date 2020/7/10 20:28
 */
//https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/
public class _028 {
    public static void main(String[] args) {
        System.out.println(reverseWords("a good   example"));
    }
    public static String reverseWords(String s) {
        String trim = s.trim();
        String[] split = trim.split(" ");
        Stack<String> stack = new Stack<>();
        for (String value : split) {
            if (!"".equals(value)){
                stack.push(value);
            }
        }
        System.out.println(stack);
        StringBuilder builder = new StringBuilder();
        int len = stack.size();
        for (int i = 0; i < len; i++) {
            builder.append(stack.pop()).append(" ");
        }
        return builder.toString().trim();
    }
}
