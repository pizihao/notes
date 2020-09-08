package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/6/28 15:22
 */
/*
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
 */
/*
二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）
它或者是一棵空树，或者是具有下列性质的二叉树：
若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
它的左、右子树也分别为二叉排序树。
 */
public class _06 {
    Boolean flag = true;

    public boolean isValidBST(TreeNode root) {
        helper(root,null,null);
        return flag;
    }

    public void helper(TreeNode root,Integer max , Integer min){

        if (flag==false||root==null)
            return;

        if ((max!=null&&root.val>=max)||(min!=null&&root.val<=min)){
            flag=false;
            return ;
        }
        System.out.println(max);
        helper(root.left,root.val,min);
        helper(root.right,max,root.val);
    }

}