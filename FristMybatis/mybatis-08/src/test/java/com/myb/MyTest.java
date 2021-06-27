package com.myb;

import com.myb.mapper.UsersMapper;
import com.myb.pojo.Person;
import com.myb.utils.MybaitsUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @program: JVMDome
 * @description: 测试类
 * @author: liuwenhao
 * @create: 2021-01-18 18:55
 **/
public class MyTest {
    @Test
    public void test() {
        SqlSession sqlSession = MybaitsUtils.getSqlSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
        List<Person> userList = mapper.getUserList();
        userList.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void test1() {
        SqlSession sqlSession = MybaitsUtils.getSqlSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
        Person userById = mapper.getUserById(1);
        System.out.println(userById.toString());
        sqlSession.close();
    }

    @Test
    public void test2() {
        SqlSession sqlSession = MybaitsUtils.getSqlSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String d = "0123456789";
        char[] c = s.toCharArray();
        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            String name = "";
            int age = 0;
            for (int j = 0; j < 4; j++) {
                char a = c[random.nextInt(c.length)];
                name = name + a;
            }
            for (int j = 0; j < 2; j++) {
                int a = c[random.nextInt(c.length)];
                age = age + a;
            }
//            mapper.addUser(name, age);
        }
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test3() {
        SqlSession sqlSession = MybaitsUtils.getSqlSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
        int addUser = mapper.updateUser(1, "哈哈", 12);

        System.out.println(addUser);
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test4() {
        SqlSession sqlSession = MybaitsUtils.getSqlSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
        Person ha = new Person("ha", 12);
        int addUser = mapper.addUser(ha);
        System.out.println(ha.getId());
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test5() {
        SqlSession sqlSession = MybaitsUtils.getSqlSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);

        Map<String, Object> map = new HashMap<>();
        map.put("userName", "嘿嘿");
        map.put("age", 12);
        mapper.addPerson2(map);
        sqlSession.commit();
        sqlSession.close();
    }
}