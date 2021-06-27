package helloworld.liu.Interceptor;

import java.util.List;

/**
 * @program: JVMDome
 * @description: 拦截器链
 * @author: liuwenhao
 * @create: 2021-06-06 11:38
 **/
public abstract class Chain {

    private List<InterceptorTest> interceptorTests;

    public void addInterceptorTests(InterceptorTest interceptorTest) {
        interceptorTests.add(interceptorTest);
    }

    public abstract void removeInterceptorTests();

    public void invokeInterceptor(Object target) {
        //循环调用拦截器中的方法，在初始化的时候改变对象的行为，如处理器和执行器
        interceptorTests.forEach(interceptorTest -> interceptorTest.plugIn(target));
    }

}