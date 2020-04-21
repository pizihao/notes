package helloworld.company;

/**
 * @Description TODO
 * @Author shidacaizi
 * @Date 2020/2/24 15:11
 */
public class AddTest {
    public static void main(String[] args) {
        method();
    }

    private static void method() {
        int i1 = 10;
        int i3 = i1++;
        System.out.println(i3);

        int i2 = 10;
        int i4 = ++i2;
        System.out.println(i4);

        int i5 = 10;
        i5 = i5++;
        System.out.println(i5);

        int i6 = 10;
        i6 = ++i6;
        System.out.println(i6);

        int i7 = 10;
        int i8 = i7++ + ++i7;
        System.out.println(i8);
    }
}
