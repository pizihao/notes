package helloworld.company.stack;

/**
 * @Description TODO
 * @Author shidacaizi
 * @Date 2020/2/23 17:50
 */
public class StackTest {
    private static int count = 1;
    public static void main(String[] args) {
        System.out.println(count);
        count++;
        main(args);
    }
}
