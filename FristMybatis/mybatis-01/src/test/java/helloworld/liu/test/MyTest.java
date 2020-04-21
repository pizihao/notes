package helloworld.liu.test;

import helloworld.liu.mapper.UsersMapper;
import helloworld.liu.pojo.Users;
import helloworld.liu.utils.MybaitsUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author shidacaizi
 * @date 2020/4/13 21:53
 */
public class MyTest {
    @Test
    public void test(){
        SqlSession sqlSession = MybaitsUtils.getSqlSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
        List<Users> userList = mapper.getUserList();

        userList.forEach(users -> {
            System.out.println(users.toString());
        });
        sqlSession.close();
    }

    @Test
    public void test1(){
        SqlSession sqlSession = MybaitsUtils.getSqlSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
        Users userById = mapper.getUserById(1);
        System.out.println(userById.toString());
        sqlSession.close();
    }

    @Test
    public void test2(){
        SqlSession sqlSession = MybaitsUtils.getSqlSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String d = "0123456789";
        char[] c = s.toCharArray();
        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            String name = "";
            String pwd = "";
            for( int j = 0; j < 4; j ++) {
                char a = c[random.nextInt(c.length)];
                name = name + a;
            }
            for( int j = 0; j < 6; j ++) {
                char a = c[random.nextInt(c.length)];
                pwd = pwd + a;
            }
            mapper.addUser(name, pwd);
        }
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test3(){
        SqlSession sqlSession = MybaitsUtils.getSqlSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
        int addUser = mapper.updateUser(1,"哈哈", "456789");
        System.out.println(addUser);
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test4(){
        SqlSession sqlSession = MybaitsUtils.getSqlSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
        int addUser = mapper.deleteUser(1);
        System.out.println(addUser);
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test5(){
        SqlSession sqlSession = MybaitsUtils.getSqlSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);

        Map<String, Object> map = new HashMap<>();
        map.put("userName", "嘿嘿");
        map.put("passWord", "789456");
        mapper.addUsers2(map);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test6(){
        SqlSession sqlSession = MybaitsUtils.getSqlSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);

        List<Users> usersLike = mapper.getUsersLike("哈");
        usersLike.forEach(users -> {
            System.out.println(users.toString());
        });

        sqlSession.commit();
        sqlSession.close();
    }
}
