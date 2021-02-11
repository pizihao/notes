package com.offer.algorithm;

import com.offer.algorithm.util.TreeNode;

/**
 * @program: JVMDome
 * @description: Offer 27. 二叉树的镜像
 * @author: liuwenhao
 * @create: 2021-02-06 16:56
 * @link https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
 **/
public class Offer27 {
    class Solution {
        public TreeNode mirrorTree(TreeNode root) {
            if(root == null) {
                return null;
            }
            TreeNode tmp = root.left;
            root.left = mirrorTree(root.right);
            root.right = mirrorTree(tmp);
            return root;
        }
    }
}