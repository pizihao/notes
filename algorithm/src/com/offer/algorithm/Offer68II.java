package com.offer.algorithm;

import java.util.Stack;

/**
 * @program: JVMDome
 * @description: Offer 68 - II
 * @author: liuwenhao
 * @create: 2021-01-31 11:03
 * @link https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 **/
public class Offer68II {
    class CQueue {
        //用于入队
        private Stack<Integer> inputStack;
        //用于出队
        private Stack<Integer> outputStack;
        public CQueue() {
            inputStack = new Stack<>();
            outputStack = new Stack<>();
        }
        public void appendTail(int value) {
            inputStack.push(value);
        }
        public int deleteHead() {
            if (outputStack.empty()) {
                while(!inputStack.empty()){
                    outputStack.push(inputStack.pop());
                }
            }
            if (!outputStack.isEmpty()) {
                return outputStack.pop();
            }
            return -1;
        }
    }
}