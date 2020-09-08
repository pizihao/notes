package com.liu.test.leetcode._01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author shidacaizi
 * @date 2020/7/3 18:36
 */
//https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
public class _018 {

    //使用BFS 广度优先搜索
    public List<List<Integer>> BFSlevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        //添加如果不成功则返回false
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = q.peek();
                q.poll();
                if (cur == null) {
                    continue;
                }
                level.add(cur.val);
                q.offer(cur.left);
                q.offer(cur.right);
            }
            if (!level.isEmpty()) {
                res.add(level);
            }
        }
        return res;
    }
    //使用DFS 深度优先搜索
    public List<List<Integer>> DFSlevelOrder(TreeNode root) {
        List<List<Integer>> res  = new ArrayList<>();
        if(root != null){
            dfs(res, root, 0);
        }
        return res;
    }

    private void dfs(List<List<Integer>> res, TreeNode node, int level){
        if(res.size() - 1 < level){
            res.add(new ArrayList<Integer>());
        }
        res.get(level).add(node.val);
        if(node.left!=null){
            dfs(res, node.left, level + 1);
        }
        if(node.right!=null){
            dfs(res, node.right, level + 1);
        }
    }
}
