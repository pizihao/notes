package com.offer.algorithm;

import sun.security.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: JVMDome
 * @description: 剑指 Offer 05. 替换空格
 * @author: liuwenhao
 * @create: 2021-02-01 14:34
 * @link https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 **/
public class Offer05 {
    static class Solution {
        public String replaceSpace(String s) {
            return Arrays.stream(s.split("")).map(s1 -> " ".equals(s1) ? "%20" : s1).collect(Collectors.joining());
        }
    }
}