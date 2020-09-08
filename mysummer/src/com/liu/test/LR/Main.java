package com.liu.test.LR;

import java.util.Scanner;

/**
 * start : <S>;
 * <S> --> <A> | <B>;
 * <A> --> "a" <A> "b" | "c";
 * <B> --> "a" <B> "b" | "d";
 * #
 */
public class Main {
    public static void main (String[] args) {
        long start = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        String s = new String();
        String c;
        while (true) {
            c = scanner.nextLine();
            int i;
            for (i = 0; i < c.length(); i++) {
                if (c.charAt(i) != '#')
                    s += c.charAt(i);
                else {
                    scanner.close();
                    break;
                }
            }
            if (i != c.length()) {
                break;
            }
        }
        BnfContainer bc = new BnfContainer();
        CodeAnalyzer ca = new CodeAnalyzer(s, bc);
        ca.analyze();
        bc.toLRTable();
        bc.printActionAndGotoTable();
        long end = System.currentTimeMillis();
        System.out.println("时间："+(end - start));
    }
}