package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/6/29 20:24
 */
public class _09 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null && right == null) {
            return null;
        }
        if(left == null) {
            return right;
        }
        if(right == null) {
            return left;
        }
        return root;
    }
}
