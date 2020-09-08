package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/6/28 17:55
 */
public class _08 {
    private static int result = 0;
    public int maxDepth(TreeNode root) {
        if (root != null){
            int leftH = maxDepth(root.left);
            int rightH = maxDepth(root.right);
            result = Math.max(leftH,rightH) + 1;
            System.out.println(result);
            return result;
        }else {
            return 0;
        }
    }
}
