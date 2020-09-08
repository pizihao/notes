package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/6 18:58
 */
//https://leetcode-cn.com/problems/path-sum/
public class _021 {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return helper(root,sum,0);
    }

    private boolean helper(TreeNode root, int sum, int i) {
        if (root == null){
            return false;
        }
        if (root.right == null && root.left == null){
            return i + root.val == sum;
        }
        return helper(root.left, sum, i+root.val) || helper(root.right, sum,i+root.val);
    }
}
