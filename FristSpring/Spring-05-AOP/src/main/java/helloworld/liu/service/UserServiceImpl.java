package helloworld.liu.service;

/**
 * @author shidacaizi
 * @date 2020/4/3 12:27
 */
public class UserServiceImpl implements UserService {
    @Override
    public void add() {
        System.out.println("增加");
    }

    @Override
    public void delete() {
        System.out.println("删除");
    }

    @Override
    public void update() {
        System.out.println("修改");
    }

    @Override
    public void query() {
        System.out.println("查询");
    }
}
