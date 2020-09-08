package com.liu.test.leetcode._01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shidacaizi
 * @date 2020/7/13 11:44
 */
//https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
public class _035 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(intersect(new int[]{4,9,5}, new int[]{9,4,9,8,4})));
    }
    //hash
    public static int[] intersect(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        Map<Integer,Integer> map = new HashMap<>();
        int[] arr = new int[Math.min(n1,n2)];
        int index = 0;
        for (Integer integer : (n1>n2?nums1:nums2)) {
            map.put(integer,map.getOrDefault(integer,0) + 1);
        }
        for (Integer integer : (n1>n2?nums2:nums1)) {
            if (map.containsKey(integer) && map.get(integer) > 0){
                map.put(integer,map.get(integer) - 1);
                arr[index++] = integer;
            }
        }
//        System.out.println(map);
        return Arrays.copyOfRange(arr,0,index);
    }
}
