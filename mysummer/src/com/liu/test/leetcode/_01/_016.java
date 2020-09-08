package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/1 17:07
 */
//https://leetcode-cn.com/problems/powx-n/
public class _016 {

    public static void main(String[] args) {
        System.out.println(myPow(2.00000, -2147483648));
    }

    //    public static double myPow(double x, int n) {
//        double m = x;
//        if (n == 0){
//            return 1;
//        }
//        if (n < 0){
//            for (int i = 1; i < -n; i++) {
//                x *= m;
//            }
//            return 1/x;
//        }
//        for (int i = 1; i < n; i++) {
//            x *= m;
//        }
//        return x;
//    }
    public static double myPow(double x, int n) {
        //分治 分成子问题
        if (n == 0){
            return 1;
        }
        if (n < 0){
            n = -n;
            x = 1 / x;
        }
        double result = myPow(x,(n/2));
        //当n是偶数时
        if(n%2==1){
            return result * result * x;
        }else {
            return result * result;
        }
    }
}