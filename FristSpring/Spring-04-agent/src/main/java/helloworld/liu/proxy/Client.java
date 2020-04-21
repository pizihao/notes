package helloworld.liu.proxy;

/**
 * @author shidacaizi
 * @date 2020/4/2 20:21
 */
// 客户
public class Client {
    public static void main(String[] args) {
        Host host = new Host();
        // 代理 代理角色一般会有被代理角色无法做操作
        Proxy proxy = new Proxy(new Host());
        proxy.rent();
        proxy.seeHouse();
    }
}
