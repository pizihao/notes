package com.liu.test.leetcode._02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/7/26 13:37
 */
//https://leetcode-cn.com/problems/pascals-triangle-ii/
public class _06 {
//    杨辉三角的第k行就有k个数
//    让我们从 2 开始
//    [1,1]
//    每一次循环都是当前数 加上前一个数
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        if (rowIndex == 1) {
            list.add(1);
            return list;
        }
        if (rowIndex == 2) {
            list.add(1);
            list.add(1);
            return list;
        }
        list.add(1);
        list.add(1);
        for (int i = 0; i < rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                int a = list.get(j) + list.get(j-1);
                list.remove(j);
                list.add(j,a);
            }
            list.add(1);
            System.out.println(list);
        }
        return list;
    }
}
