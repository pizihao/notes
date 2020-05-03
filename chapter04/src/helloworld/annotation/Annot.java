package helloworld.annotation;

import java.lang.annotation.*;

/**
 * @author shidacaizi
 * @date 2020/4/29 21:17
 */
/*
* @Target：用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
* 注解（annotation）可被用于 packages、types（类、接口、枚举、Annotation类型）、
* 类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）。
* 在注解类型的声明中使用了target可更加明晰其修饰的目标。取值(ElementType)有：
* ElementType.ANNOTATION_TYPE 可以应用于注释类型。
* ElementType.CONSTRUCTOR 可以应用于构造函数。
* ElementType.FIELD 可以应用于字段或属性。
* ElementType.LOCAL_VARIABLE 可以应用于局部变量。
* ElementType.METHOD 可以应用于方法级注释。
* ElementType.PACKAGE 可以应用于包声明。
* ElementType.PARAMETER 可以应用于方法的参数。
* ElementType.TYPE 可以应用于类的任何元素。
*
* @Retention：@Retention定义了该注解被保留的时间长短：某些注解仅出现在源代码中，
* 而被编译器丢弃；而另一些却被编译在class文件中；编译在class文件中的注解可能会被虚拟机忽略，
* 而另一些在class被装载时将被读取（请注意并不影响class的执行，因为注解与class在使用上是被分离的）。
* 使用这个meta-Annotation可以对注解的“生命周期”限制。作用：表示需要在什么级别保存该注释信息，
* 用于描述注解的生命周期（即：被描述的注解在什么范围内有效）　　取值（RetentionPoicy）有：
*
* 1.SOURCE:在源文件中有效（即源文件保留）
* 2.CLASS:在class文件中有效（即class保留）
* 3.RUNTIME:在运行时有效（即运行时保留）
*
* PS:注解只有一个成员时，按规范写成value()，当然不这么写不会报错。
* 如果不设置默认值，那么使用注解时必须要传值。只有类可以被注解，因为接口或者抽象类并不能被注解。
*
* */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Annot {
    String str() default "刘文浩";
}
