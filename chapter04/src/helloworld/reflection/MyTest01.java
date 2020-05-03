package helloworld.reflection;


import com.sun.xml.internal.bind.v2.model.core.ID;
import sun.reflect.Reflection;

import java.awt.*;
import java.awt.print.Book;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author shidacaizi
 * @date 2020/4/27 22:32
 */
public class MyTest01 {
    public static  void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        // 可以通过Class中的一个方法forname获取反射后的对象
        Class<?> className = Class.forName("helloworld.reflection.User");
        User user = (User) className.newInstance();
        Method[] methods = className.getDeclaredMethods();
        for (Method value : methods) {
            String[] split = value.toString().split("\\(");
            String[] split1 = split[0].split("\\.");
            String replace = split[split.length - 1].replace(")", "");
            if ("".equals(replace)){
                Method method = className.getDeclaredMethod(split1[split1.length - 1]);
                method.setAccessible(true);
                method.invoke(user);
            }else {
                String[] split2 = replace.split(",");
//                for (int i = 0; i < split2.length; i++) {
//                    System.out.println(Class.forName(split2[i]));
//                }
                System.out.println();
            }
        }
//        Field[] fields = className.getDeclaredFields();
//        for (int i = 0; i < fields.length; i++) {
//            System.out.println(fields[i]);
//        }
//        System.out.println(Arrays.toString(className.getFields()));
//        System.out.println(Arrays.toString(className.getDeclaredMethods()));
//        System.out.println(Arrays.toString(className.getInterfaces()));
//        System.out.println(Arrays.toString(className.getAnnotations()));
    }
}
