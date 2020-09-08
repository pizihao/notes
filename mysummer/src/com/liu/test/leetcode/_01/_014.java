package com.liu.test.leetcode._01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/7/1 12:53
 */
//https://leetcode-cn.com/problems/binary-tree-paths/
public class _014 {
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        //首先要看一下有几个叶子节点,也就代表有几条路径
        helper(root, new StringBuilder(),list);

        return list;
    }

    public static void helper(TreeNode root, StringBuilder str, List<String> list) {
        if (root == null){
            return;
        }
        if (root.left == null && root.right == null){
            list.add(str.append(root.val).toString());
            //可以使用setLength方法，直接改变str的长度
            str.delete(str.length() - (""+root.val).length(), str.length());
            return;
        } else{
            str.append(root.val).append("->");
            helper(root.left, str,list);
            helper(root.right, str,list);
        }
        if (str.length() > 0){
            str.delete(str.length() - ("->"+root.val).length(), str.length());
        }
    }
}