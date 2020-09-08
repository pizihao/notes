package com.liu.test.leetcode._01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/7/2 12:55
 */
//https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
public class _017 {
    //因为数组的下标是从0开始的所以这里需要放两个空字符串 代表 0 和 1
    private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        StringBuilder str = new StringBuilder();
        combine(res, digits, str, 0);
        return res;
    }

    private void combine(List<String> res, String digits, StringBuilder str, int posn) {
        //posn代表的是层数，，指的是需要递归几层，也就是原字符串有多少个字符组成
        if (posn == digits.length()) {
            //到最后一层把字符串加入到结果集合中
            res.add(str.toString());
            return;
        }
        //这里减去的是'0'这个字符的ASCII码，也就是91 用来获取当前的letters究竟是几代表的字符
        String letters = KEYS[digits.charAt(posn) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            int sbLen = str.length();
            //每加一层 posn就递进1
            str.append(letters.charAt(i));
            combine(res, digits, str, posn+1);
            //setLength改变str的长度，sbLen等于在该层时目标字符串的长度，
            // 目的是为了去除在该层之下添加的字符
            str.setLength(sbLen);
        }
    }
}
