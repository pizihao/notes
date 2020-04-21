package helloworld.wen.proxy;

/**
 * @author shidacaizi
 * @date 2020/4/2 20:21
 */
// 客户
public class Client {
    public static void main(String[] args) {
        // 真实角色
        Host host = new Host();

        // 代理角色 现在没有
        ProxyInvocationHandler handler = new ProxyInvocationHandler();
        // 通过调用程序处理角色来处理我们调用的接口
        handler.setRent(host);

        Rent proxy = (Rent) handler.getProxy();
        proxy.rent();
    }
}
