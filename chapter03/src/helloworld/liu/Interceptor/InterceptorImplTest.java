package helloworld.liu.Interceptor;

import java.lang.reflect.InvocationTargetException;

/**
 * 拦截器实现类
 *
 * @program: JVMDome
 * @author: liuwenhao
 * @create: 2021-06-05 10:56
 **/
public class InterceptorImplTest implements InterceptorTest {

    @Override
    public Object interceptor(Variable variable) throws InvocationTargetException, IllegalAccessException {
        //拦截器处理
        return variable.handle();
    }

    @Override
    public Object plugIn(Object target) {
        //在拦截器链中调用，插入插件(拦截后的其他操作)
        //生成一个代理，在调用invoke的时候调用，
        return null;
    }
}