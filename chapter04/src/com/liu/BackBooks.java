package com.liu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author shidacaizi
 * @data 2020/3/11 12:58
 */
public class BackBooks {
    public static void main(String[] args) {
        // 调用Scanner 方法
        Scanner in = new Scanner(System.in);
        //请求输入
        String s = in.nextLine();

        //把输入的字符串转为一个集合
        //这个集合有父字符串的所有子串
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                list.add(s.substring(i, j + 1));
            }
        }
        //计算回文子串的个数
        int cnt = 0;
        for (String temp : list) {
//            System.out.println(temp);
            if (isPalindrome(temp)) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    public static boolean isPalindrome(String s) {
        if (s.length() == 1){
            return true;
        }
        StringBuilder sb = new StringBuilder(s);
        return sb.toString().equals(sb.reverse().toString());
    }
}
