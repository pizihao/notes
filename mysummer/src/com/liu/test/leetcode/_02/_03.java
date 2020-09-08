package com.liu.test.leetcode._02;

/**
 * @author shidacaizi
 * @date 2020/7/25 12:02
 */
//https://leetcode-cn.com/problems/string-compression/
public class _03 {
    //最普通的方法
    public int compress(char[] chars) {
        if (chars.length == 0) return 0;
        if (chars.length == 1) return 1;

        int a = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != chars[i-1]){
                a++;
            }
        }
        System.out.println();
        return a;
    }
}
