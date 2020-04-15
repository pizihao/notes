package com.liu.mapper;

import com.liu.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author shidacaizi
 * @date 2020/4/13 21:30
 */
public interface UsersMapper {
    List<Users> getUserList();

    Users getUserById(@Param("id") int id);

    int addUser(@Param("name") String name, @Param("pwd") String pwd);

    int addUsers2(Map<String, Object> map);

    int updateUser(@Param("id") int id, @Param("name") String name, @Param("pwd") String pwd);

    int deleteUser(@Param("id") int id);

    List<Users> getUsersLike(@Param("value") String name);

}
