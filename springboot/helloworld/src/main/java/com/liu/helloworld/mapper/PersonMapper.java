package com.liu.helloworld.mapper;

import com.liu.helloworld.pojo.Person;
import com.liu.helloworld.pojo.Users;

import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/4/13 21:30
 */
public interface PersonMapper {
    List<Person> getUserList();

    int addUser(Person person);

    Users getUserById(Integer id);
}
