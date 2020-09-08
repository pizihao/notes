package com.liu.test.leetcode._02;

/**
 * @author shidacaizi
 * @date 2020/7/24 16:56
 */
//https://leetcode-cn.com/problems/longest-common-prefix/
public class _02 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
//        定义 字符串 str 存放公共前缀
//        遍历整个 字符串 找到 strs[i] 和 str 的公共前缀 赋值给str
        String str = strs[0];
        int index = str.length();
        for (int i = 1; i < strs.length; i++) {
            int n = Math.min(strs[i].length() , str.length());
            for (int j = i; j < n; j++) {
                if (strs[i].charAt(j) != str.charAt(j)){
                    index = Math.min(index,j);
                    break;
                }
            }
        }
        return str.substring(0,index);
    }
}
