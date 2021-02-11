package com.offer.algorithm;

import com.offer.algorithm.util.ListNode;

/**
 * @program: JVMDome
 * @description: Offer 25. 合并两个排序的链表
 * @author: liuwenhao
 * @create: 2021-02-06 16:47
 * @link https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
 **/
public class Offer25 {
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode dummyHead = new ListNode(-1), pre = dummyHead;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    pre.next = l1;
                    pre = pre.next;
                    l1 = l1.next;
                } else {
                    pre.next = l2;
                    pre = pre.next;
                    l2 = l2.next;
                }
            }
            if (l1 != null) {
                pre.next = l1;
            }
            if (l2 != null) {
                pre.next = l2;
            }

            return dummyHead.next;
        }
    }
}