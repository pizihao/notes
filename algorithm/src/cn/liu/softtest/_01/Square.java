package cn.liu.softtest._01;

import java.util.Scanner;

/**
 * @author shidacaizi
 * @date 2020/4/3 21:52
 */
public class Square {
    // 判断是什么三角形 并计算周长
    public static int theLong(int var1, int var2, int var3) {
        if (var1 == var2 || var1 == var3 || var2 == var3) {
            if (var1 == var2 && var1 == var3) {
                System.out.println("等边三角形,周长为"+ (var1 + var2 + var3));
                return 1;
            } else {
                System.out.println("等腰三角形,周长为"+ (var1 + var2 + var3));
                return 1;
            }
        } else {
            System.out.println("是三角形");
            return 0;
        }
    }

    // 判断是否是三角形
    public static boolean isSquare(int var1, int var2, int var3) {
        if (var1 + var2 > var3 && var1 + var3 > var2 && var2 + var3 > var1){
            return true;
        }
        return false;
    }

    // 检验输入的是否是合法数字
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // 输入方法
    public static int scan(String name, Scanner in) {
        System.out.println("请输入" + name + "的长度:");
        String next = in.next();
        if (isNumeric(next) && Integer.parseInt(next) > 0) {
            return Integer.parseInt(next);
        } else {
            System.out.println("长度不符合规范，请输入大于零的数字");
            return scan(name, in);
        }
    }
}
