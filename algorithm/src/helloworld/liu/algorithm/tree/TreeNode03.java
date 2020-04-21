package helloworld.liu.algorithm.tree;

import java.util.*;

/**
 * @author shidacaizi
 * @data 2020/3/11 18:58
 */
public class TreeNode03 {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(10);
        System.out.println( findDuplicateSubtrees(treeNode).size());
    }

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        // 定义map集合
        HashMap<String, Integer> hashMap = new HashMap<>();
        // 定义list集合
        List<TreeNode> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        findTreeVal(hashMap, root, list);
        System.out.println(hashMap);
        return list;
    }

    public static String findTreeVal(HashMap<String, Integer> hashMap, TreeNode treeNode, List<TreeNode> list) {
        // 这里需要获取这个树的所有子树
        // 放在map集合集合中
        if (treeNode == null){
            return "";
        }
        String treestt = treeNode.val + findTreeVal(hashMap, treeNode.left, list) + findTreeVal(hashMap, treeNode.right, list);
        // 在这里查看一下hashmap里是否已经有了
        // 如果有treeNode进入list集合，如果没有进入hashmap集合
        // 如果list里边也有也不要了
        if (hashMap.get(treestt) != null && hashMap.get(treestt) == 1){
                list.add(treeNode);
        }

        hashMap.put(treestt, hashMap.getOrDefault(treestt, 0) + 1);
        return treestt;
    }

}
