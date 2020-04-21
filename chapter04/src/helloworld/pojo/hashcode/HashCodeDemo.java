package helloworld.pojo.hashcode;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author shidacaizi
 * @date 2020/3/22 11:10
 */
public class HashCodeDemo {

    public static void main(String[] args) {
//        char[] c = {1,2,3};
//        String a = new String(c,1,2);
//        Integer integer = 5;
//        System.out.println(integer.hashCode());
//        Double d = 5D;
//        System.out.println(d.hashCode());
        Long value = 2147483648L;
        System.out.println((int)(value ^ (value >>> 32)));
//        Float f = 5F;
//        Character ch = 'c';
//        Short s = 's';
//        Boolean bo = true;
//        Byte by = 1;
//        System.out.println(by);
//        System.out.println(5^0);
    }
}
