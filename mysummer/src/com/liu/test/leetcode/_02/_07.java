package com.liu.test.leetcode._02;

/**
 * @author shidacaizi
 * @date 2020/7/26 16:23
 */
//https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
public class _07 {
    public static int lastRemaining(int n, int m) {
            int pos = 0; // 最终活下来那个人的初始位置
            for(int i = 2; i <= n; i++){
                pos = (pos + m) % i;  // 每次循环右移
                System.out.println(pos);
            }
            return pos;
    }

    public static void main(String[] args) {
        System.out.println(lastRemaining(5, 3));
    }
}