package com.liu.test.leetcode._01;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author shidacaizi
 * @date 2020/7/10 21:42
 */
//https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
public class _029 {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        // 使用一个最大堆（大顶堆）
        // Java 的 PriorityQueue 默认是小顶堆，添加 comparator 参数使其变成最大堆
        //重写一下比较器
        Queue<Integer> heap = new PriorityQueue<>(k, (i1, i2) -> Integer.compare(i2, i1));
        for (int e : arr) {
            // 当前数字小于堆顶元素才会入堆
            if (heap.isEmpty() || heap.size() < k || e < heap.peek()) {
                heap.offer(e);
            }
            if (heap.size() > k) {
                heap.poll(); // 删除堆顶最大元素
            }
        }
        // 将堆中的元素存入数组
        int[] res = new int[heap.size()];
        int j = 0;
        for (int e : heap) {
            res[j++] = e;
        }
        return res;
    }
}
