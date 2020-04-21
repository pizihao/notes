package helloworld.wen.proxy;

/**
 * @author shidacaizi
 * @date 2020/4/2 20:20
 */
//房东
public class Host implements Rent {
    @Override
    public void rent() {
        System.out.println("我是房东，~我要出租房子");
    }
}
