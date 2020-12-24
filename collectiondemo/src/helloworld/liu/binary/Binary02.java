package helloworld.liu.binary;

import com.sun.org.apache.xalan.internal.xsltc.cmdline.Compile;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @program: JVMDome
 * @description: 栈
 * @author: liuwenhao
 * @create: 2020-11-14 11:43
 * @link https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid/
 * 给定一个由'('和')'括号组成的字符串 S，我们需要添加最少的括号（ '('或是')'，可以在任何位置），以使得到的括号字符串有效。
 *
 * 从形式上讲，只有满足下面几点之一，括号字符串才是有效的：
 *
 * 它是一个空字符串，或者
 * 它可以被写成AB（A与B连接）, 其中A 和B都是有效字符串，或者
 * 它可以被写作(A)，其中A是有效字符串。
 * 给定一个括号字符串，返回为使结果字符串有效而必须添加的最少括号数
 **/
public class Binary02 {

    public static void main(String[] args) {
        System.out.println(minAddToMakeValid("(()(()()(((())"));
    }

    public static int minAddToMakeValid(String S) {
        /*维护一个栈 最后的结果就是栈的大小*/
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            /*查看栈顶元素，判断和要插入元素是否可以匹配*/
            if (stack.size() > 0 && stack.peek() == '(' && c == ')'){
                stack.pop();
            }else{
                stack.push(c);
            }
        }
        return stack.size();
    }
}