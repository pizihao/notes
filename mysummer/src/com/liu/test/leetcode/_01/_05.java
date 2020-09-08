package com.liu.test.leetcode._01;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author shidacaizi
 * @date 2020/6/27 17:32
 */
public class _05 {
    public static void main(String[] args) {

    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                //加入一个二叉树节点到栈中
                stack.push(curr);
                //该节点的左节点
                curr = curr.left;
            }
            //栈中首个元素就是中序遍历的左子树的所有节点
            curr = stack.pop();
            //接入结果数组中 中间节点
            res.add(curr.val);
            //右节点，中序遍历 左->中->右
            curr = curr.right;
        }
        return res;
    }
}


