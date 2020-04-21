package helloworld.liu.thread;

/**
 * @author shidacaizi
 * @data 2020/3/9 22:53
 */
public class MakesDome {
    private int[] arrays = new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    private int lengths = arrays.length;

    public void numbers(){
                    for (int a = 0; a < lengths; a++) {
                        for (int b = 0; b < lengths; b++) {
                            for (int c = 0; c < lengths; c++) {
                                for (int d = 0; d < lengths; d++) {
                                    int zero = a*1000+b*100+c*10+d-a*100-b*10-c-d*1000-c*100-d*10-c;
                                    if (zero == 0 && a != b && a != c && a != d && b != c && b != d && c != d){
                                        System.out.println("A="+a+",B="+b+",C="+c+",D="+d);
                                    }
                                }
                            }
            }
        }
    }

    public static void main(String[] args) {
       new MakesDome().numbers();
    }
}
