package helloworld.wen.pojo;

/**
 * @author shidacaizi
 * @date 2020/4/1 16:48
 */
public class User {
    private String name;

    public User(String s) {
        System.out.println("调用了有参构造器");
        this.name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
        System.out.println("使用到了无参构造方法，对象被创建了");
    }

    public void show(){
        System.out.println("name:"+name);
    }
}
