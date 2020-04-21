package helloworld.liu.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author shidacaizi
 * @date 2020/4/3 14:09
 */
// 使用注解方式实现AOP
@Aspect // 标记这个类是一个切面
public class AnnotationPointCut {

    @Before("execution(* helloworld.liu.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("====方法执行前====");
    }

    @After("execution(* helloworld.liu.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("====方法执行后====");
    }

    // 在环绕增强中，我们可以给定一个参数，代表我们要获取处理切入的点
    @Around("execution(* helloworld.liu.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("====环绕前====");

        // 获得签名
        Signature signature = point.getSignature();
        System.out.println("签名："+signature);

        // 执行方法
        Object proceed = point.proceed();

        System.out.println("====环绕后====");
        System.out.println(proceed);
    }
}
