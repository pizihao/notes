package helloworld.reflection;

/**
 * @author shidacaizi
 * @date 2020/4/27 22:40
 */
public class User {
    private String name;
    private String sex;
    private String age;
    private String adress;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getAdress() {
        return adress;
    }

    public User(String name, String sex, String age, String adress) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.adress = adress;
    }

    public User() {
    }

    private void sleep(){
        System.out.println("开始睡觉");
    }

    public void run(){
        System.out.println("开始跑步");
    }

    public void eat(String var1, int var2){
        System.out.println(var1+var2+"开始吃饭");
    }

    public void look(String var1){
        System.out.println(var1+"开始看");
    }
}
