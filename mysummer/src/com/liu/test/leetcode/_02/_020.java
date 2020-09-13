package com.liu.test.leetcode._02;

import java.util.Arrays;

/**
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * 现在，给出位于一条水平线上的房屋和供暖器的位置，找到可以覆盖所有房屋的最小加热半径。
 * 所以，你的输入将会是房屋和供暖器的位置。你将输出供暖器的最小加热半径。
 * https://leetcode-cn.com/problems/heaters/
 *
 * @author 刘文浩
 * @date 2020/9/13 11:12
 * @description TODO
 */
public class _020 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int res = 0;
        int i = 0;
        for (int house : houses) {
//            i < heaters.length - 1 确保遍历完所有的供暖期
//            Math.abs(heaters[i] - house) 当前供暖期到当前房子的距离
//            Math.abs(heaters[i + 1] - house) 下一个供暖期到当前房子的距离
            while (i < heaters.length - 1 &&
                    Math.abs(heaters[i] - house) >= Math.abs(heaters[i + 1] - house)) {
                i++;
            }
            res = Math.max(res, Math.abs(heaters[i] - house));
        }
        return res;
    }
}
