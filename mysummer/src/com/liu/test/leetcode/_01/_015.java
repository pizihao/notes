package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/1 16:12
 */
//https://leetcode-cn.com/problems/add-digits/
public class _015 {

    public static void main(String[] args) {
        System.out.println(addDigits(456));
    }

    public static int addDigits(int num) {
        if (num < 10){
            return num;
        }
        int n = 0;
        for (int i = 0; i < (num+"").length(); i++) {
            n += Integer.parseInt((num + "").substring(i,i+1));
        }
        return addDigits(n);
    }

//https://leetcode-cn.com/problems/add-digits/solution/java-o1jie-fa-de-ge-ren-li-jie-by-liveforexperienc/
//这个方法太强了
//    public int addDigits(int num) {
//        return (num - 1) % 9 + 1;
//    }

//    其他方法
//    public int addDigits(int num) {
//    if (num < 10) {
//        return num;
//    }
//    int sum = 0;
//    while(num != 0) {
//        sum += (num % 10);
//        num = num / 10;
//    }
//    return addDigits(sum);
//}
//    其他方法*2
//    public int addDigits(int num) {
//	while (num >= 10) {
//		num = num / 10 + num % 10;
//	}
//	return num;
//}
}
