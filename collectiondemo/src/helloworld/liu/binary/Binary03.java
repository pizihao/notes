package helloworld.liu.binary;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: JVMDome
 * @description: 树
 * @author: liuwenhao
 * @create: 2020-11-16 16:10
 * @link https://leetcode-cn.com/problems/deepest-leaves-sum/
 * 给你一棵二叉树，请你返回层数最深的叶子节点的和
 **/
public class Binary03 {

    private Map<Integer,Integer> hashMap = new HashMap<>();
    private int count = 1;
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        /*用来存放每层的总和*/
        hashMap.put(1,root.val);
        allCount(root,1,hashMap);
        return hashMap.get(this.count);
    }

    /**
     * @description: TODO
     * @param root 节点
     * @param lv 当前层数
     * @return int
     * @author liuwenaho
     * @date 2020/11/16 16:40
     */
    public int allCount(TreeNode root, int lv, Map<Integer, Integer> hashMap){
        int left = 0;
        int right = 0;
        if (root.left != null){
            left = allCount(root.left, lv+1, hashMap);
        }
        if (root.right != null){
            right = allCount(root.right, lv+1, hashMap);
        }
        if (root.left != null || root.right != null){
            int all = left + right;
            this.count = Math.max(lv+1,this.count);
            Integer integer = hashMap.get(lv+1);
            if (integer == null){
                integer = 0;
            }
            hashMap.put(lv+1,all+integer);
        }
        return root.val;
    }
}