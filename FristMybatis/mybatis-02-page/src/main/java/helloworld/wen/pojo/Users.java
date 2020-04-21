package helloworld.wen.pojo;

/**
 * @author shidacaizi
 * @date 2020/4/13 21:41
 */
// 实体类
public class Users {
    private int id;
    private String name;
    private String pwd;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public Users() {
    }

    public Users(int id, String name, String psw) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpwd() {
        return pwd;
    }

    public void setpwd(String pwd) {
        this.pwd = pwd;
    }
}
