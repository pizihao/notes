package com.offer.algorithm;

import com.offer.algorithm.util.TreeNode;

/**
 * @program: JVMDome
 * @description: Offer 28. 对称的二叉树
 * @author: liuwenhao
 * @create: 2021-02-06 16:57
 * @link https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
 **/
public class Offer28 {
    class Solution {
        public boolean isSymmetric(TreeNode root) {
            return root == null || recur(root.left, root.right);
        }

        boolean recur(TreeNode left, TreeNode right) {
            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            return recur(left.left, right.right) && recur(left.right, right.left);
        }
    }
}