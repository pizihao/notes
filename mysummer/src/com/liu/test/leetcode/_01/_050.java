package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/23 17:15
 */
//https://leetcode-cn.com/problems/teemo-attacking/
public class _050 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries.length == 0) {
            return 0;
        }
        int num = 0;
        //duration是持续时间 假设为 d
        //timeSeries[i + 1] - timeSeries[i] 是两次攻击的时间间隔 假设为 t
        //如果 d > t 说明 两次攻击间隔之间存在重复 这时要舍弃 重复的时间
        //如果 d <= t 说明 两次攻击间隔之间 中毒效果已经完毕 这时就使用 中毒效果的持续时间
        for (int i = 0; i < timeSeries.length - 1; i++){
            num += Math.min(timeSeries[i + 1] - timeSeries[i], duration);
        }
        return num + duration;
    }
}
