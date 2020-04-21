package helloworld.liu.collection;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 * @author shidacaizi
 * @date 2020/3/21 11:33
 */
public class MyClassLoader extends ClassLoader {
    private String rootPath;

    public MyClassLoader() {
        super();
    }

    public MyClassLoader(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> clz = findLoadedClass(name);
        if (clz != null)
            return clz;
        byte[] classData = getData(name);
        clz = defineClass("com.lixianzhong.classLoader.Hello", classData, 0, classData.length);
        return clz;
    }

    private byte[] getData(String className) {
        String pathName = rootPath + className.replace(".", "/") + ".class";
        System.out.println(pathName);
        byte[] bytes = null;
        try (FileInputStream fis = new FileInputStream(pathName);
             ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
            byte[] flush = new byte[1024 * 1024];
            int len = -1;
            while (-1 != (len = fis.read(flush))) {
                bos.write(flush);
            }
            bytes = bos.toByteArray();
        } catch (Exception e) {
            System.out.println("异常");
        }
        return bytes;
    }
}
