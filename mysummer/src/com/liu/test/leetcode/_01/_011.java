package com.liu.test.leetcode._01;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @author shidacaizi
 * @date 2020/6/30 14:21
 */
public class _011 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        //生成滑动窗口之前 队列中的元素<k之前
        for (int i = 0; i < k; i++) {
            //查看队首的值 如果是空的话。。如果不是空的话
            //这里必须使用while
            while (!deque.isEmpty() && deque.peekFirst() < nums[i]){
                //这个条件下弹出队首的值，因为出现了比它大的数
                deque.removeFirst();
            }
            //这个时候把符合条件的值加进去，在这里的值是比队首值小的或者队列为空时的元素
            deque.addFirst(nums[i]);
        }
        //这个时候的队列中至少有一个值
        //而且队尾元素就是前K个值里的最大值
        res[0] = deque.peekLast();
        System.out.println(deque);
        //生成了滑动窗口之后 从k到最大长度 1 <= k <= nums.length
        for (int i = k; i < nums.length ; i++) {
            //这里需要一个条件弹出队尾的值，因为它已经不属于滑动窗口所判断的范围了
            //当队首的值正好不属于滑动窗口时，则这个队尾的值应该正好和nums[i-k]的值相等
            //因为滑动窗口的长度是k,所以当前i向前k个值就是刚好超出范围的值
            //因为每次循环只需要判断一次即可所以使用if便可
            if (deque.peekLast() == nums[i-k]){
                deque.removeLast();
            }
            //这里的条件和上边是差不多的
            //因为队列中可能连续的几个队首都比nums[i]要小
            //所以使用while
            while (!deque.isEmpty() && deque.peekFirst() < nums[i]){
                deque.removeFirst();
            }
            deque.addFirst(nums[i]);
            //把对应的最大值放入数组中,也就是队列中队尾的值
            //因为这里的i是从k开始而res需要从1开始
            //所以应该把值放入i-k+1的位置
            res[i-k+1] = deque.peekLast();
        }
        return res;
    }
}
