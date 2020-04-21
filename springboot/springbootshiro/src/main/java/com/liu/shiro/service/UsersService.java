package com.liu.shiro.service;

import com.liu.shiro.pojo.Users;
import org.apache.ibatis.annotations.Param;

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
     * @Param: [name]
     * @return: com.liu.shiro.pojo.Users
     * @Author: shidacaizi
     * @Date: 2020/4/20
     */
    Users getUsersByName(@Param("name") String name);

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
