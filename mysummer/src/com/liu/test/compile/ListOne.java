package com.liu.test.compile;

/**
 * @author shidacaizi
 * @date 2020/5/8 20:12
 */
public class ListOne {
    static String fromFile = "D:\\ideawenjian\\javaweb1\\src\\test\\java\\com\\liu\\test\\compile\\1.txt";
    public static void main(String[] args) {
        System.out.println(Utils.read(fromFile));
    }
}
