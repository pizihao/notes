package com.wen.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author shidacaizi
 * @date 2020/4/3 11:29
 */
// 会用这个类动态的自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {

    // 被代理的接口
    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    // 生成得到代理类
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(), this);
    }

    // 处理代理实例 并返回结果
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 动态代理的本质，就是使用反射机制实现
//        System.out.println(proxy);
        Object invoke = method.invoke(rent, args);
        return invoke;
    }


    // 看房
    public void seeHouse() {
        System.out.println("这里是中介带客户去看房");
    }

    // 收中介费
    public void fare() {
        System.out.println("收中介费");
    }

    // 合同
    public void heTong() {
        System.out.println("合同");
    }
}
