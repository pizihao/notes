package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/6/28 17:14
 */
public class _07 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return root;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;

        return root;
    }
}
