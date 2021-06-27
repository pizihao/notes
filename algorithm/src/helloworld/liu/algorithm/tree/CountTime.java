package helloworld.liu.algorithm.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @program: JVMDome
 * @description: 括号
 * @author: liuwenhao
 * @create: 2021-02-24 00:37
 **/
public class CountTime {
    public static void main(String[] args) {
//        System.out.println(longestCommonSubsequence("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq"));
        System.out.println(checkString("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq"));
    }

    public static int count(String str) {
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (stack.size() > 0 && stack.peek() == '(' && c == ')') {
                count++;
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return count;
    }

    public static boolean checkString(String str) {
        Stack<Character> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (stack.size() != 0) {
                if (stack.peek() > c) {
                    max = 0;
                    stack.clear();
                } else if (stack.peek() == c) {
                    ++max;
                }
            }
            if (stack.size() == 0 || stack.peek() < c) {
                stack.push(c);
                ++max;
            }
            if (stack.size() >= 3 && max % stack.size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 题目3、现有两个字符串,请尝试以最优的时间与空间复杂度，使用任一擅长语言,
     * 设计一个函数，以这两个字符串作为参数,返回这两个字符串的最长公共子序列。
     * 当多个同样长度的子序列出现时，可返回任意一个(如两字符分别为 "axbxcxdex"与"yaybdycyey"时，应返回"abce"或"abde" )。
     **/
    public static String countEvery(String str1, String str2) {
        int posj = 0;
        String result = "";
        for (int s = 0; s < str1.length(); s++) {
            StringBuilder str = new StringBuilder();
            postion:
            for (int i = s; i < str1.length(); i++) {
                char ci = str1.charAt(i);
                for (int j = posj; j < str2.length(); j++) {
                    char cj = str2.charAt(j);
                    if (cj == ci) {
                        str.append(ci);
                        posj = j + 1;
                        continue postion;
                    }
                }
            }
            result = str.toString().length() > result.length() ? str.toString() : result;
            posj = 0;
        }
        return result;
    }


    public static String checkString(String str1, String str2) {
        int r = str1.length();
        int c = str2.length();

        String[] dp = new String[c + 1];
        Arrays.fill(dp, "");
        String a;
        for (int i = 1; i <= r; i++) {
            a = "";
            for (int j = 1; j <= c; j++) {
                String temp = dp[j];
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[j] = a + str1.charAt(i - 1);
                } else {
                    dp[j] = dp[j].length() > dp[j - 1].length() ? dp[j] : dp[j - 1];
                }
                a = temp;
            }
        }
        return dp[c];
    }
}