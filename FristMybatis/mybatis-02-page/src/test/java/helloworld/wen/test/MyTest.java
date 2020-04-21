package helloworld.wen.test;

import helloworld.wen.mapper.UsersMapper;
import helloworld.wen.pojo.Users;
import helloworld.wen.utils.MybaitsUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/4/14 12:06
 */
public class MyTest {
    @Test
    public void getUserByLimit(){
        SqlSession sqlSession = MybaitsUtils.getSqlSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("startIndex", 1);
        map.put("pageSize", 5);

        List<Users> list = mapper.getUserByLimit(map);

        list.forEach(users -> {
            System.out.println(users.toString());
        });

        sqlSession.commit();
        sqlSession.close();
    }
}
