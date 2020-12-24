package helloworld.liu.jdk8;

/**
 * @program: JVMDome
 * @description: 下载方法
 * @author: liuwenhao
 * @create: 2020-11-10 19:28
 **/
public class GenerateFile {
    public static void main(String[] args) {
        /*或许可以更简化*/
        new Upload("一个模板").exportlist(templateName -> {
            System.out.println(templateName+ "下载");
        });
    }
}