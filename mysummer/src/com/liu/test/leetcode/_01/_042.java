package com.liu.test.leetcode._01;

import java.util.Arrays;

/**
 * @author shidacaizi
 * @date 2020/7/17 15:02
 */
//https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
public class _042 {
//    题目要求的是 查看一个数组是否是 ”后序遍历的结果“
//    那么这个数组num的最后一个数 num[num.length - 1] 一定是root 根节点
//    按照这个数往前找 找到第一个小于它得数 记下标为i
//    那么下标 0~i 对应的元素都是小于 num[num.length - 1]
//    另一段 [i+1] ~ [num.length - 2] 都是大于 root
//    以此类推 对于 num[i] 和 num[num.length - 2] 有着和root下相同的特点
//    可以使用递归
    public static boolean verifyPostorder(int[] postorder) {
        if (postorder.length == 2) {
            return true;
        }
        int a = postorder[postorder.length - 1];
        int index = postorder.length - 1;
//        这里反过来了 不过一样
        for (int i = 0; i < postorder.length - 1; i++) {
            if (postorder[i] > a){
                index = i;
                break;
            }
        }
//        System.out.println(index);

//        判断 0~[index-1]
        for (int i = 0; i < index; i++) {
            if (postorder[i] > a){
                return false;
            }
        }
//        判断 index~[postorder.length - 2]
        for (int i = index; i < postorder.length - 1; i++) {
            if (postorder[i] < a){
                return false;
            }
        }

        return verifyPostorder(Arrays.copyOfRange(postorder, 0, index)) && verifyPostorder(Arrays.copyOfRange(postorder, index, postorder.length - 1));
    }

    public static void main(String[] args) {
        int[] n = new int[]{1, 2, 3, 4, 5};
        System.out.println(verifyPostorder(n));
    }
}
