package helloworld.liu.mapper;

/**
 * @author shidacaizi
 * @date 2020/4/1 14:15
 */
public class UserMySqlImpl implements UserDao {
    /**
    *
    * @Param: []
    * @return: void
    * @Author: shidacaizi
    * @Date: 2020/9/8
    */
    @Override
    public void listUser() {
        System.out.println("Mysql 用户");
    }
}
