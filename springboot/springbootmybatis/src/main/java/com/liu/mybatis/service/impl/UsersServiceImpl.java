package com.liu.mybatis.service.impl;

import com.liu.mybatis.mapper.UsersMapper;
import com.liu.mybatis.pojo.Users;
import com.liu.mybatis.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/4/19 15:38
 */
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<Users> getList() {
        return usersMapper.getList();
    }

    @Override
    public Users getUsersById(int id) {
        return usersMapper.getUsersById(id);
    }

    @Override
    public int addUsers(Users users) {
        return usersMapper.addUsers(users);
    }

    @Override
    public int updateUsers(Users users) {
        return usersMapper.updateUsers(users);
    }

    @Override
    public int deleteUsers(int id) {
        return usersMapper.deleteUsers(id);
    }
}
