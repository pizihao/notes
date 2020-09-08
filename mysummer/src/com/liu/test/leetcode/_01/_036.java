package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/13 17:12
 */
//https://leetcode-cn.com/problems/is-subsequence/
public class _036 {
    public static void main(String[] args) {
        System.out.println(isSubsequence("abc", "acbefab"));
    }
    //在t中 匹配s中的每一个字符
    //设定一个标志,保证每次都是在上一次判断之后进行判断的
    public static boolean isSubsequence(String s, String t) {
        int i = 0;
        for (char ch : s.toCharArray()) {
            while (i<t.length() && t.charAt(i) != ch) {
                i++;
            }
            i++;
        }
        return i <= t.length();
    }
}
