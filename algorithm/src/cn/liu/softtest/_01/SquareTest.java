package cn.liu.softtest._01;

import java.util.Scanner;

/**
 * @author shidacaizi
 * @date 2020/4/3 23:35
 */
/*
 * 1、设计三角形问题的程序
 * 在三角形计算中，要求输入三角型的三个边长：A、B 和C。
 * 当三边不可能构成三角形时提示错误，
 * 可构成三角形时计算三角形周长。
 * 若是等腰三角形打印“等腰三角形”，
 * 若是等边三角形，则提示“等边三角形”。
 * */
public class SquareTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Square square = new Square();
        int one = Square.scan("第一个边", in);
        int two = Square.scan("第二个边", in);
        int three = Square.scan("第三个边", in);

        if (Square.isSquare(one, two, three)) {
            Square.theLong(one, two, three);
        } else {
            System.out.println("不是三角形");
        }
        in.close();
    }
}
