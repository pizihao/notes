package com.hao.subject._04;

import java.util.Scanner;

/**
 * @author shidacaizi
 * @date 2020/4/6 18:46
 */
/*
 * 4、迷路的牛牛
 * 牛牛去犇犇老师家补课，出门的时候面向北方，但是现在他迷路了。虽然他手里有一张地图，但是他需要知道自己面向哪个方向，请你帮帮他。
 * 输入描述:
 * 每个输入包含一个测试用例。
 * 每个测试用例的第一行包含一个正整数，表示转方向的次数N(N<=1000)。
 * 接下来的一行包含一个长度为N的字符串，由L和R组成，L表示向左转，R表示向右转。
 * 输出描述:
 * 输出牛牛最后面向的方向，N表示北，S表示南，E表示东，W表示西。
 * 输入例子1:
 * 3
 * LRR
 * 输出例子1:
 * E
 */
public class NiuNiu {
    public static void main(String[] args) {
        int a = 0; // L加一，R减一
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        String s = in.next();
        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == 'L'){
                a++;
            }else{
                a--;
            }
        }
        in.close();
        int b = (a%3)%4;
        if (b == 1 || b == -3){
            System.out.println("W");
        }else if (b == 2 || b == -2){
            System.out.println("S");
        }else if (b == -1 || b == 3){
            System.out.println("E");
        }else{
            System.out.println("N");
        }
    }
}
