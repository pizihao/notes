package helloworld.liu.binary;

/**
 * @program: JVMDome
 * @description: 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
 * @author: liuwenhao
 * @create: 2020-11-16 20:04
 * https://leetcode-cn.com/problems/check-balance-lcci/submissions/
 **/
public class Binary04 {
    private boolean falg = true;
    public boolean isBalanced(TreeNode root) {
        /*创建一个方法，使得返回值为参数子树的高度*/
        treeHight(root,1);
        return this.falg;
    }


    public int treeHight(TreeNode root, int lv){
        if (root == null){
            return lv;
        }
        /*lv  为当前的高度*/
        int left = treeHight(root.left, lv+1);
        int right = treeHight(root.right, lv+1);
        if (Math.abs(left - right) > 1){
            this.falg = false;
        }
        return Math.max(left,right);
    }

}