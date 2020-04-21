package helloworld.company.liu.cpusz;

/**
 * @author shidacaizi
 * @data 2020/3/14 10:47
 */
public class LookMore01 {

    public static void main(String[] args) {
        Integer  num = 10;
        String arr = "hello";
        StringBuilder  array = new StringBuilder("liu");
        new LookMore01().look(num, arr, array);

        System.out.println(num);
        System.out.println(arr);
        System.out.println(array);
    }

    public void look(Integer n, String a, StringBuilder ar){
        n += 1;
        a += "would";
        ar.append("wen");

        System.out.println(n);
        System.out.println(a);
        System.out.println(ar);
    }

}
