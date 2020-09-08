package com.liu.test.leetcode._02;

/**
 * @author shidacaizi
 * @date 2020/7/28 10:28
 */
public class _09 {
    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));
    }
    public static int lengthOfLastWord(String s) {
        if(s == null || s.length() == 0) return 0;
        int count = 0;
        for(int i = s.length() - 1;i >= 0; i--){
            if(s.charAt(i) == ' '){
                if (count == 0) {
                    continue;
                }
                break;
            }
            count++;
        }
        return count;
    }
}