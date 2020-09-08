package com.liu.test.leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/7/3 18:33
 */
public class DFStemplate {
    //递归写法：
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if(root==null){
            return allResults;
        }
        travel(root,0,allResults);
        return allResults;
    }
    private void travel(TreeNode root, int level, List<List<Integer>> results){
        if(results.size()==level){
            results.add(new ArrayList<>());
        }
        results.get(level).add(root.val);
        if(root.left!=null){
            travel(root.left,level+1,results);
        }
        if(root.right!=null){
            travel(root.right,level+1,results);
        }
    }

//      C++ 非递归写法
//    def DFS(self, root):
//
//            if tree.root is None:
//            return []
//
//    visited, stack = [], [root]
//
//            while stack:
//    node = stack.pop()
//            visited.add(node)
//
//    process (node)
//        # 生成相关的节点
//            nodes = generate_related_nodes(node)
//		stack.push(nodes)
//
//            # other processing work
//	...
//      递归写法
//    visited = set()
//
//    def dfs(node, visited):
//            if node in visited: # terminator
//    	# already visited
//    	return
//
//                visited.add(node)
//
//            # process current node here.
//            ...
//            for next_node in node.children():
//            if next_node not in visited:
//    dfs(next_node, visited)
}
