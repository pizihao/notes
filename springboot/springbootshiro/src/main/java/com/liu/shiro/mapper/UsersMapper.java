package com.liu.shiro.mapper;

import com.liu.shiro.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/4/19 14:40
 */
@Mapper
@Repository
public interface UsersMapper {
    /**
     * @Param: []
     * @return: java.util.List<com.liu.mybatis.pojo.Users>
     * @Author: shidacaizi
     * @Date: 2020/4/19
     */
    List<Users> getList();

    /**
     * @Param: [id]
     * @return: com.liu.mybatis.pojo.Users
     * @Author: shidacaizi
     * @Date: 2020/4/19
     */
    Users getUsersById(@Param("id") int id);

    /**
     * @Param: [name]
     * @return: com.liu.shiro.pojo.Users
     * @Author: shidacaizi
     * @Date: 2020/4/20
     */
    Users getUsersByName(@Param("name") String name);

    /**
     * @Param: [users]
     * @return: int
     * @Author: shidacaizi
     * @Date: 2020/4/19
     */
    int addUsers(Users users);

    /**
     * @Param: [users]
     * @return: int
     * @Author: shidacaizi
     * @Date: 2020/4/19
     */
    int updateUsers(Users users);

    /**
     * @Param: [id]
     * @return: int
     * @Author: shidacaizi
     * @Date: 2020/4/19
     */
    int deleteUsers(@Param("id") int id);
}
