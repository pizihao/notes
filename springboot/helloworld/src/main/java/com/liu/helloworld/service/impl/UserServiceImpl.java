package com.liu.helloworld.service.impl;

import com.liu.helloworld.mapper.UsersMapper;
import com.liu.helloworld.pojo.Users;
import com.liu.helloworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: JVMDome
 * @description: Impl
 * @author: liuwenhao
 * @create: 2021-03-10 11:46
 **/
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<Users> getUserList() {
        return usersMapper.getUserList();
    }

    @Override
    public int addUser(Users users) {
        return usersMapper.addUser(users);
    }

    @Override
    public Users getUserById(Integer id) {
        return usersMapper.getUserById(id);
    }

    @Override
    public int deleteById(Integer id) {
        return usersMapper.deleteById(id);
    }

    @Override
    public int updateById(Users users) {
        return usersMapper.updateById(users);
    }
}