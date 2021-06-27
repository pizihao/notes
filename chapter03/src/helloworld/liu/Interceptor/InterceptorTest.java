package helloworld.liu.Interceptor;

import org.omg.PortableInterceptor.Interceptor;

import java.lang.reflect.InvocationTargetException;

/**
 * 实现一个拦截器
 *
 * @program: JVMDome
 * @description:
 * @author: liuwenhao
 * @create: 2021-06-04 16:23
 **/
public interface InterceptorTest {


    /**
     * 拦截器方法
     *
     * @param variable 拦截对象的数据信息
     * @return Object
     * @author liuwenaho
     * @date 2021/6/4 17:58
     */
    Object interceptor(Variable variable) throws InvocationTargetException, IllegalAccessException;

    /**
     * 插入插件
     *
     * @param target 插入的对象
     * @return Object
     * @author liuwenaho
     * @date 2021/6/5 10:54
     */
    Object plugIn(Object target);

}