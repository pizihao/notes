package helloworld.company.liu.taijie;

/**
 * @author shidacaizi
 * @date 2020/3/14 13:04
 */
public class NumberTest01 {
    static int s;
    int i;
    int j;

    {
        int i = 1;
        i++;
        j++;
        s++;
    }

    public void test(int j) {
        j++;
        i++;
        s++;
    }

    public static void main(String[] args) {
        NumberTest01 obj1 = new NumberTest01();
        NumberTest01 obj2 = new NumberTest01();
        obj1.test(10);
        obj1.test(20);
        obj2.test(30);
        System.out.println(obj1.i + "," + obj1.j + "," + obj1.s);
        System.out.println(obj2.i + "," + obj2.j + "," + obj2.s);
    }

}
