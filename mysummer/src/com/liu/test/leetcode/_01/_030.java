package com.liu.test.leetcode._01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/7/11 12:59
 */
//https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/
public class _030 {
    public static void main(String[] args) {
        System.out.println(countSmaller(new int[]{5, 2, 6, 1}));
    }
    public static List<Integer> countSmaller(int[] nums) {
        //解析
        /*
        * 将这个数组从后向前遍历，放入一个新的数组中，新的数组中的比当前数组小的个数就是符合题目要求的数值
        * 为了减少一次循环，可以将元素插入新的数组时进行排序，那么该元素插入位置后面元素的个数就是题解
        * 也就是 (当前数组len — 1) - 当前元素在新数组的下标
        * */
        LinkedList<Integer> list = new LinkedList<>();
        int[] arr = new int[nums.length];
        for(int i = 0;i < nums.length; i++){
            int num = getNum(arr,i,nums[nums.length-i-1]);
            list.addFirst(num);
        }
        return list;
    }
    private static int getNum(int[] arr, int len, int num){
        int index = len;
        while(index >0 && arr[index-1] < num){
            arr[index] = arr[--index];
        }
        arr[index] = num;
        return len - index;
    }

    public static List<Integer> no01(int[] nums){
        List<Integer> list = new ArrayList<>();
        //暴力法  (超出时间限制)
        for (int i = 0; i < nums.length; i++) {
            int a = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    a++;
                }
            }
            list.add(a);
        }
        return list;
    }
}
