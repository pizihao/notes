package com.liu.test.leetcode._01;

import java.util.Stack;

/**
 * @author shidacaizi
 * @date 2020/6/26 15:32
 */
public class _03 {

    public static void main(String[] args) {
        System.out.println(isValid("([[][]{({}({}))}])"));
//        System.out.println(isStack("{()[]{}}"));
    }
    public static boolean isValid(String s) {
        //使用最基本的匹配方法
        if (s.length()%2 != 0){
            return false;
        }
        int num = s.length();
        for (int i = 0; i <= num ; i++){
            s = s.replace("()", "");
            s = s.replace("{}", "");
            s = s.replace("[]", "");
        }
        System.out.println(s);
        return s.length() == 0;
    }
    public static boolean isStack(String s) {
        //使用栈
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
}
