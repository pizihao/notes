package helloworld.liu.wangyi._01;

import java.util.Scanner;

/**
 * @author shidacaizi
 * @date 2020/4/7 19:25
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        int d = -1;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int count = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= (a[i] - a[i-1]); j++) {
                if ((a[i] - a[i-1])%j == 0){
                    ++count;
                }
                if (count == n && (a[i] - a[i-1])%j == 0){
                    d = Math.max(d, j);
                }
            }
        }
        System.out.println(d);
    }
}
