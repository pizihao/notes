package com.liu.test.leetcode._01;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shidacaizi
 * @date 2020/7/7 17:25
 */
//https://leetcode-cn.com/problems/single-number/
public class _022 {

    public static void main(String[] args) {
//        System.out.println(singleNumber(new int[]{
//                2,2,1
//        }));
//        System.out.println(0 ^ 45);
//        System.out.println(45 ^ 45);
        System.out.println((2 ^ 1) ^ 1);
    }

    public static int singleNumber(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer integer : map.keySet()) {
            if (map.get(integer) == 1){
                return integer;
            }
        }
        return 0;
    }
//    public int singleNumber(int[] nums) {
//        int single = 0;
//        for (int num : nums) {
//            single ^= num;
//        }
//        return single;
//    }
}
