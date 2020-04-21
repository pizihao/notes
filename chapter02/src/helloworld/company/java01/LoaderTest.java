package helloworld.company.java01;

import sun.misc.Launcher;

import java.net.URL;

/**
 * @Description TODO
 * @Author shidacaizi
 * @Date 2020/2/22 20:49
 */
public class LoaderTest {
    public static void main(String[] args) {
        System.out.println("启动类加载器");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for(URL element : urLs){
            System.out.println(element.toExternalForm());
        }


        System.out.println("扩展类加载器");
        String property = System.getProperty("java.ext.dirs");
        for (String p : property.split(";")){
            System.out.println(p);
        }
    }
}
