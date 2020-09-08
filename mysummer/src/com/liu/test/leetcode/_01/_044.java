package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/19 13:50
 */
//https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
public class _044 {
/*
*终止条件：
*   当越过叶节点，则直接返回 null ；
*   当 root 等于 p, q，则直接返回 root ；
*递推工作：
*   开启递归左子节点，返回值记为 left ；
*   开启递归右子节点，返回值记为 right ；
*返回值： 根据 left 和 right ，可展开为四种情况；
*   当 left 和 right 同时为空 ：说明 root 的左 / 右子树中都不包含 p,q，返回 null ；
*   当 left 和 right 同时不为空 ：说明 p, q 分列在 root 的 异侧 （分别在 左 / 右子树），因此 root为最近公共祖先，返回 root ；
*   当 left 为空 ，right 不为空 ：p,q 都不在 root 的左子树中，直接返回 right 。具体可分为两种情况：
*       p,q其中一个在 root的 右子树 中，此时 right 指向 p（假设为 p ）；
*       p,q两节点都在 root的 右子树 中，此时的 right 指向 最近公共祖先节点 ；
*   当 left 不为空 ， right 为空 ：与情况 3. 同理；
*
*
*
* */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if(root == null || root == p || root == q) {
                return root;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if(left == null && right == null) {
                return null; // 1.
            }
            if(left == null) {
                return right; // 3.
            }
            if(right == null) {
                return left; // 4.
            }
            return root; // 2. if(left != null and right != null)
        }
    }
