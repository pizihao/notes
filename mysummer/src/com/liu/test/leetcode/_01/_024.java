package com.liu.test.leetcode._01;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shidacaizi
 * @date 2020/7/8 18:54
 */
//https://leetcode-cn.com/problems/maximum-level-sum-of-a-binary-tree/
public class _024 {
    //深度优先搜索和广度优先搜索
    //因为广度优先搜索无法确定层号所以这里先使用深度优先搜索
    private int smax = 0;
    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //定义一个层数,最初是在第一层
        int level = 1;
        //定义一个map记录每一层的数
        Map<Integer,Integer> map = new HashMap<>();
        helper(root,map,level);
        //看一下map
        System.out.println(map);
        //找到最大的数
//        int max = 0;
        int min = 0;
//        for (Integer integer : map.keySet()) {
//            int a = max;
//            max = Math.max(map.get(integer), max);
//        }
        //max就是最大值
        for (Integer integer : map.keySet()) {
            if (smax == map.get(integer)){
                min = integer;
                break;
            }
        }
        return min;
    }

    private void helper(TreeNode root, Map<Integer, Integer> map, int level) {
        //递归
        //如果root是null 那么就是到了最后一层了，没有操作
        if (root == null){
            return;
        }
        //放入map中
        map.put(level,map.getOrDefault(level,0) + root.val);
        smax = Math.max(smax,map.get(level));
        helper(root.left, map, level + 1);
        helper(root.right, map, level + 1);
    }
}
