package com.liu.test.leetcode._01;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author shidacaizi
 * @date 2020/6/27 11:16
 */
public class _04 {
    public static void main(String[] args) { System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 5, 6, -9, -2, 2, 8, -1}, 3)));
    }
    public static int[] maxSlidingWindow(int[] nums, int k) {
        //如果长度是0的情况
        if(nums.length == 0 || k == 0) {
            return new int[0];
        }
        //声明双端队列
        Deque<Integer> deque = new LinkedList<>();
        //存放结果的数组
        int[] res = new int[nums.length - k + 1];
        //未形成窗口的时候
        for(int i = 0; i < k; i++) {
            //队列顶端的数值必须大于要放入的数值，不是就把队列首删除
            while(!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        //队列的第一个值是最大值
        res[0] = deque.peekFirst();
        for(int i = k; i < nums.length; i++) { // 形成窗口后
            System.out.println("1j"+deque.peekFirst());
            System.out.println("2i"+nums[i - k]);
            if(deque.peekFirst() == nums[i - k]) {
                deque.removeFirst();
            }
            while(!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }
}
