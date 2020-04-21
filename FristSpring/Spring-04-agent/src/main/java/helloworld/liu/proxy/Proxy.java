package helloworld.liu.proxy;

/**
 * @author shidacaizi
 * @date 2020/4/2 20:34
 */
// 代理
public class Proxy implements Rent{
    private Host host;

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }

    @Override
    public void rent() {
        seeHouse();
        host.rent();
        fare();
        heTong();
    }

    // 看房
    public void seeHouse(){
        System.out.println("这里是中介带客户去看房");
    }
    // 收中介费
    public void fare(){
        System.out.println("收中介费");
    }
    // 合同
    public void heTong(){
        System.out.println("合同");
    }
}
