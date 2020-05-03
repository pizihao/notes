package helloworld.annotation;

import sun.reflect.Reflection;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @author shidacaizi
 * @date 2020/4/29 21:18
 */
@Annot
public class MyTest02 {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean hasAnnotation = MyTest02.class.isAnnotationPresent(Annot.class);
        if ( hasAnnotation ) {
            Annot annotation = MyTest02.class.getAnnotation(Annot.class);

            System.out.println(annotation.str());
        }
    }
}
