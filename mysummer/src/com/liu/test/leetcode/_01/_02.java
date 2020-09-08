package com.liu.test.leetcode._01;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/6/26 8:02
 */
public class _02 {

    public static void main(String[] args) {
        threeSums(new int[]{14, -8, -2, 5, 2, 7, 7, -5, 7, -5, -2, 4, 0}).forEach(System.out::println);
    }

    public static List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length - 2; i++) {
            if (i == 0 || (num[i] != num[i - 1])) {
                int lo = i + 1, hi = num.length - 1, sum = -num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo + 1]) {
                            System.out.println(i);
                            lo++;
                        }
                        while (lo < hi && num[hi] == num[hi - 1]) {
                            hi--;
                        }
                        lo++;
                        hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }
    public static List<List<Integer>> threeSums(int[] nums) {
        //先将数组排序
        Arrays.sort(nums);
        //声明存放数组的list
        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            //如果nums[i] == nums[i-1]的话就说明重复了
            if (i == 0 || nums[i] != nums[i - 1]) {
                int l = i + 1;
                int r = nums.length - 1;
                int num = -nums[i];
                while (l < r) {
                    //判断是否符合题目要求
                    if (nums[l] + nums[r] == num){
                        //如果符合就放入集合中
                        list.add(Arrays.asList(nums[l], nums[r], nums[i]));
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        l++;
                        r--;
                    }else if (nums[l] + nums[r] < num) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return list;
    }
}