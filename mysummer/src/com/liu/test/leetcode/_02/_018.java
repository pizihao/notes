package com.liu.test.leetcode._02;

/**
 * 给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。
 *   在比较时，字母是依序循环出现的。举个例子：
 * 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'
 * https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/
 * @author 刘文浩
 * @date 2020/9/13 8:50
 * @description 有序的题目要优先想到二分查找
 */
public class _018 {

    public static void main(String[] args) {
        System.out.println(nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'j'));
    }

    public static char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (letters[mid] > target){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
            System.out.println("mid" + mid);
            System.out.println("left" + left);
            System.out.println("right" + right);
        }
        return letters[left % letters.length ];
    }
}
