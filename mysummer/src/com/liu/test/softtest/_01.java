package com.liu.test.softtest;

import java.util.Scanner;

/**
 * @author shidacaizi
 * @date 2020/5/15 15:09
 */
public class _01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(DoWork(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
    }

    public static int DoWork(int x, int y, int z) {
        int k = 0;
        int j = 0;
        if ((x > 3) && (z < 10)) {
            k = x * y - 1;
            j = (int) Math.sqrt(k);
        }
        if ((x == 4) || (y > 5))
            j = x * y + 10;
        j = j % 3;

        return j;
    }
}
