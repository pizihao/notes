package helloworld.liu;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author shidacaizi
 * @data 2020/3/11 12:33
 */
public class StringToNumber {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println(Math.addExact(Long.parseLong(input.nextLine()), Long.parseLong(input.nextLine()))+"");
    }
}
