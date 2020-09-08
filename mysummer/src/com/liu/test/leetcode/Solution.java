package com.liu.test.leetcode;

/**
 * @author shidacaizi
 * @date 2020/6/24 15:55
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }
    public static boolean isPalindrome(String s) {
        //如果这个字符串是空或者是一个字符可以确定是一个回文字符串
        if (s.isEmpty() || s.length() == 1) return true;
        //从左到右，从右到左依次判断
        int l = 0, r = s.length() - 1;
        while (l < r){
            //判断l位置和r位置是不是字母或数字字符，如果不是要跳过,需要使用while，因为可能不只碰见一个非字母和数字
            while (l < r &&!Character.isLetterOrDigit(s.charAt(l))){
                System.out.println(s.charAt(l));
                //如果不是则加一
                l++;
            }
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))){
                //右边的要减一
                r--;
            }
            //判断l位置和r位置是否一样，转换大小写
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))){
                System.out.println(s.charAt(l) + "" + s.charAt(r)+ " " + l + " " + r);
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

}
