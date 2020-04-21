package helloworld.liu.test;

import helloworld.liu.mapper.UsersMapper;
import helloworld.liu.pojo.Users;
import helloworld.liu.utils.MybaitsUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/4/14 13:13
 */
public class MyTest {
    @Test
    public void test(){
        SqlSession sqlSession = MybaitsUtils.getSqlSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);

        List<Users> users = mapper.getUserList();

        users.forEach(users1 -> {
            System.out.println(users1.toString());
        });

        sqlSession.commit();
        sqlSession.close();
    }
}
