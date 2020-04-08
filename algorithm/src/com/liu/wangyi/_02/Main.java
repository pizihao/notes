package com.liu.wangyi._02;

import java.util.Scanner;

/**
 * @author shidacaizi
 * @date 2020/4/7 19:50
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int D = in.nextInt();
        int[] A = new int[n];
        int[] B = new int[n];
        int count = 0;

        int[] A1 = new int[n];
        int[] B1 = new int[n];

        for (int i = 0; i < n; i++) {
            A[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            B[i] = in.nextInt();
        }
        
        for (int i = 0; i < n; i++) {
            int a = 0;
            int b = 0;
            for (int j = 0; j < n; j++) {
                int c = b;
                b = a;
                a = Math.min(A[i], A[j]);
                if (b != a){
                    b = j;
                }else {
                    b = c;
                }
            }
            A1[i] = a;
            B1[i] = B[b];
        }

        for (int i = 0; i < n; i++) {
            System.out.println(A1[i]);
            if (A1[i] >= D){
                count += B1[i];
            }
            D++;
        }
        System.out.println(count);
        in.close();
    }
}
