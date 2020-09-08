package com.liu.test.leetcode._01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/7/18 11:07
 */
//https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
public class _043 {
    List<List<Integer>> result;
    List<Integer> col;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root==null) return new ArrayList<>();
        result=new ArrayList<>();
        col=new ArrayList<>();
        dfs(root,sum);
        return result;
    }
    private void dfs(TreeNode node,int num){
        if(node==null) return;
        num-=node.val;
        col.add(node.val);
        if(num==0&&node.left==null&&node.right==null) result.add(new ArrayList<Integer>(col));
        dfs(node.left,num);
        dfs(node.right,num);
        col.remove(col.size()-1);
    }
}
