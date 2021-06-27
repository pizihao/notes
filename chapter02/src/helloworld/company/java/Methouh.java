package helloworld.company.java;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @program: JVMDome
 * @description: 方法
 * @author: liuwenhao
 * @create: 2021-03-03 17:48
 **/
public class Methouh {
    public static void main(String[] args) {
        Method[] methods = SampleClass.class.getMethods();
        Class returnType = methods[0].getReturnType();
        new SampleClass();

        System.out.println(returnType.getName().equals(ArrayList.class.getName()));
        Type trueType = ((ParameterizedType) returnType.getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(methods[0].getGenericReturnType().getTypeName());

        System.out.println(String.class.getClassLoader());
        System.out.println(int.class.getClassLoader());
        System.out.println(Integer.class.getClassLoader());
        System.out.println(SampleClass.class.getClassLoader());
    }
}

class SampleClass {
    private String sampleField;

    public ArrayList<String> getSampleField() {
        return new ArrayList<>();
    }
}

