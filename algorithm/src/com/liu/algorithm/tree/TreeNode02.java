package com.liu.algorithm.tree;

/**
 * @author shidacaizi
 * @data 2020/3/10 22:42
 */
public class TreeNode02 {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        System.out.println(new TreeNode02().richMouse(treeNode));
        }

        public String tree2str(TreeNode t) {
            return richMouse(t);
        }

        public String richMouse(TreeNode t){
            // 如果t为null 那么直接返回一个空
            if (t == null){
                return "";
            }
            // 如果t的左子节点和右子节点全为null 返回t.val
            if (t.left == null && t.right == null){
                return t.val+"";
            }
            // 判断到这里 一定可以确定右子节点不为null
            // 如果左子节点为null，那么需要加括号，如果右子节点为null，则不需要加括号
            if (t.left == null){
                return t.val+"()"+"("+richMouse(t.right)+")";
            }
            // 同上
            if (t.right == null){
                return t.val+"("+richMouse(t.left)+")";
            }

            // 左右子节点都不为null，两个都需要括号，先左再右
            return t.val+"("+richMouse(t.left)+")("+richMouse(t.right)+")";
        }

}
