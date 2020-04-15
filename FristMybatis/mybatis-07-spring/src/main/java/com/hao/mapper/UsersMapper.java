package com.hao.mapper;

import com.hao.pojo.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/4/15 18:44
 */
public interface UsersMapper {
    List<Users> getListUsers();
}
