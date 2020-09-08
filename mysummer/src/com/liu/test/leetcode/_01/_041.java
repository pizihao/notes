package com.liu.test.leetcode._01;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author shidacaizi
 * @date 2020/7/16 18:50
 */
/*
请从字符串中找出一个最长的不包含重复字符的子字符串，
    计算该最长子字符串的长度。
示例 1:

输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
*/
//https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/
public class _041 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0){
            return 0;
        }
        Queue<Character> queue = new LinkedList<>();
        int res = 0;
        int max = 0;
        //
        for (int i = 0; i < s.length(); i++) {
            if (!queue.contains(s.charAt(i))) {
                queue.add(s.charAt(i));
                res += 1;
            }else{
                queue = deleteAOne(s.charAt(i),queue);
                res = queue.size();
                queue.add(s.charAt(i));
                res += 1;
            }
            max = Math.max(res,max);
        }
        return max;
    }

    private Queue<Character> deleteAOne(char charAt,Queue<Character> queue) {
        if (queue.poll() != charAt){
            return deleteAOne(charAt,queue);
        }else{
            return queue;
        }
    }
}
