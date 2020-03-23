package com.company.java01;

/**
 * @Description TODO
 * @Author shidacaizi
 * @Date 2020/2/22 17:42
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        /*获取系统类加载器 AppClassLoader*/
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

        //获取其上层，扩展类加载器 ExtClassLoader
        ClassLoader systemClassLoaderParent = systemClassLoader.getParent();
        System.out.println(systemClassLoaderParent);

        //继续向上层获取类加载器
        ClassLoader systemClassLoaderParentParent = systemClassLoaderParent.getParent();
        System.out.println(systemClassLoaderParentParent);//null

        //对于用户自定义类的类加载器 AppClassLoader
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

        //String类使用引导类加载器进行加载 ---》java的核心类库都是使用引导类进行加载的
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);//null;
    }
}
