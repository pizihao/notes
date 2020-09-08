package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/21 14:49
 */
//https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
public class _048 {
    //使用二分查找
    public int kthSmallest(int[][] matrix, int k) {
        int col = matrix.length;
        int left = matrix[0][0];
        int right = matrix[col - 1][col - 1];

        //取left和right的中值
        while (left < right){

            int mid =left + (right - left)/2;
            //mid就是当前所选区域的中间值
            //我们需要看一下这个中间值是当前区域中有几个元素小于等于中间值 假设这个几为  n
            //有n个数小于等于中间值 那么中间值就是第n+1小
            //要求是 求前k小元素 那么
            //之后按照逻辑 先需要比较 n+1和k的大小
            //如果 n>k 说明mid比所需结果大 mid的值太大了 应当选取左边的区域 缩小mid的值 继续寻找判断
            //如果 n<k 说明mid比所需结果小 mid的值太小了 应当选取右边的区域 放大mid的值 继续寻找判断
            //如果 n=k 这里就有一些绕了 在数组中 < mid的第一个值是第n小 也就是 第k-1小
            //那么数组中 >= mid 的数就是第k小 也就是我们要求的
            //所以这里 n>k 和n=k的情况是相同的
            //但是每次划分区域的时候必须保证 mid这个中间值 是存在于右边区域的
            //这样来看最后 的left就是我们所需要的结果
            int j = 0;
            int i = col - 1;
            int sum = 0;
            //当前区域中有几个元素小于等于中间值 并使其自增一 和 k比较大小
            while (i >= 0 && j < col) {
                if (matrix[i][j] <= mid){
                    //如果成立 那么 matrix[i][j] 小于等于 中间值 符合标准 sum + 1
                    //同时 当前列 i 上面的所有数 都 符合标准 sum + i
                    sum += 1+i;
                    //看第二列
                    //i的值不需要变
                    //matrix[i+1][j]的值 一定大于 matrix[i+1][j-1]
                    //如果 matrix[i+1][j-1] 大于等于 mid
                    //那么matrix[i+1][j] 一定大于 mid
                    j++;
                }else {
                    //不成立的情况
                    i--;
                }
            }

            if (sum >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        //结果值应该是left
        return left;
    }
}