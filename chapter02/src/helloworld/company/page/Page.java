package helloworld.company.page;

/**
 * @author shidacaizi
 * @date 2020/4/1 15:52
 */
public class Page {

    public static void main(String[] args) {
        float a = duizhe(0.1f, 100);
        float b = 365*24*36*3;
        System.out.println(a/b);
    }

    public static float duizhe(float houdu, int time){
        float var = houdu;
        for (int i = 1; i <= time ; i++){
            var = var * 2;
        }
        return var;
    }
}