package com.offer.algorithm;

import com.offer.algorithm.util.ListNode;

/**
 * @program: JVMDome
 * @description: 剑指 Offer 06. 从尾到头打印链表
 * @author: liuwenhao
 * @create: 2021-02-01 14:54
 * @link https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 **/
public class Offer06 {
    class Solution {
        public int[] reversePrint(ListNode head) {
            int length = 0;
            ListNode node = head;
            if (head == null){
                return new int[0];
            }
            while (node.next != null){
                length++;
                node = node.next;
            }
            System.out.println(length);
            int[] ints = new int[length];
            node = head;
            for (int i = length - 1; i >= 0; i--) {
                ints[i] = node.val;
                node = node.next;
            }
            return ints;
        }
    }
}