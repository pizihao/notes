package helloworld.reflection;

import java.lang.reflect.Method;

/**
 * @author shidacaizi
 * @date 2020/4/28 13:59
 */
public class StuText01 {

    //反射调用方法获取返回值
    //第一种方法,获取对象，直接通过对象调用方法
    //第二种方法,通过方法名获取方法,执行方法
    public static void main(String[] args) throws Exception {
        Class<?> userClass = Class.forName("helloworld.reflection.Student");
        Student student = (Student) userClass.newInstance();

        //第一种方法
        System.out.println("第一次借钱：");
        int money = student.getMoney();
        System.out.println("实际拿到钱为: " + money);
        System.out.println("------------------------分割线------------------------");

        //第二种方法,（无参的示例：借钱）
        System.out.println("第二次借钱：");
        Method getMoney = userClass.getMethod("getMoney");//得到方法对象
        Object money2 = getMoney.invoke(student);//调用借钱方法，得到返回值
        System.out.println("实际拿到钱为：" + money2);
        System.out.println("------------------------分割线------------------------");

        //第二种方法,（单个参数的示例：还钱）
        System.out.println("第一次还钱：");
        Method repay1 = userClass.getMethod("repay", int.class);//得到方法对象,有参的方法需要指定参数类型
        repay1.invoke(student, 3000);//执行还钱方法，有参传参
        System.out.println("------------------------分割线------------------------");

        //第二种方法,（单个参数的示例：还钱）
        System.out.println("第二次还钱：");
        Method repay2 = userClass.getMethod("repay", String.class, int.class);//得到方法对象,有参的方法需要指定参数类型
        repay2.invoke(student, "小飞", 5000);//执行还钱方法，有参传参


    }
}
