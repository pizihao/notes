package com.liu.helloworld.service;

import com.liu.helloworld.pojo.Users;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: JVMDome
 * @description: service
 * @author: liuwenhao
 * @create: 2021-03-10 11:46
 **/
@Service
public interface UserService {
    List<Users> getUserList();

    int addUser(Users users);

    Users getUserById(Integer id);

    int deleteById(Integer id);

    int updateById(Users users);
}