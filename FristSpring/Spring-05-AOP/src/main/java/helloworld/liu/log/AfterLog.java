package helloworld.liu.log;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author shidacaizi
 * @date 2020/4/3 12:36
 */
public class AfterLog implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("执行了"+method.getName()+"方法，返回了"+o);
    }
}
