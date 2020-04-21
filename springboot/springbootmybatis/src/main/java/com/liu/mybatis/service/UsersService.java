package com.liu.mybatis.service;

import com.liu.mybatis.pojo.Users;

import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/4/19 15:38
 */
public interface UsersService {
    /**
     *
     * @Param: []
     * @return: java.util.List<com.liu.mybatis.pojo.Users>
     * @Author: shidacaizi
     * @Date: 2020/4/19
     */
    List<Users> getList();

    /**
     *
     * @Param: [id]
     * @return: com.liu.mybatis.pojo.Users
     * @Author: shidacaizi
     * @Date: 2020/4/19
     */
    Users getUsersById(int id);

    /**
     *
     * @Param: [users]
     * @return: int
     * @Author: shidacaizi
     * @Date: 2020/4/19
     */
    int addUsers(Users users);

    /**
     *
     * @Param: [users]
     * @return: int
     * @Author: shidacaizi
     * @Date: 2020/4/19
     */
    int updateUsers(Users users);

    /**
     *
     * @Param: [id]
     * @return: int
     * @Author: shidacaizi
     * @Date: 2020/4/19
     */
    int deleteUsers(int id);
}
