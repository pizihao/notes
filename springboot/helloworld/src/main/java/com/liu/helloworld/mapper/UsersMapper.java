package com.liu.helloworld.mapper;


import com.liu.helloworld.pojo.Users;

import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/4/13 21:30
 */
public interface UsersMapper {
    List<Users> getUserList();

    int addUser(Users users);

    Users getUserById(Integer id);

    int deleteById(Integer id);

    int updateById(Users users);
}
