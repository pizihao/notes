package com.liu;

/**
 * @author shidacaizi
 * @data 2020/3/10 17:11
 */
public class TreeNodeTest01 {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(50);
        new TreeNodeTest01().diameterOfBinaryTree(treeNode);
    }

//    int res = 0;
//    public int diameterOfBinaryTree(TreeNode root) {
//        dfs(root);
//        return res;
//    }
//
//    // 函数dfs的作用是：找到以root为根节点的二叉树的最大深度
//    public int dfs(TreeNode root){
//        if(root == null){
//            return 0;
//        }
//        int leftDepth = dfs(root.left);
//        int rigthDepth = dfs(root.right);
//        res = Math.max(res,leftDepth + rigthDepth);
//        return Math.max(leftDepth,rigthDepth) + 1;
//    }

    int mh = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }else{
            getTree(root);
            System.out.println(mh);
            return mh;
        }
    }

    private int getTree(TreeNode theRoot) {
        if (theRoot == null) {
            return 0;
        }
        int leftMx = getTree(theRoot.left);
        int rightMx = getTree(theRoot.right);
        mh = Math.max(mh,leftMx + rightMx);
        return Math.max(leftMx,rightMx) + 1;
    }
}

//-Xms8m -Xmx8m -XX:+PrintGCDetails

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
