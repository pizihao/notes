package helloworld.liu.mapper;

import helloworld.liu.pojo.Users;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/4/13 21:30
 */
public interface UsersMapper {
    @Select("select * from users")
    List<Users> getUserList();

//    Users getUserById(@Param("id") int id);
//
//    int addUser(@Param("name") String name, @Param("pwd") String pwd);
//
//    int addUsers2(Map<String, Object> map);
//
//    int updateUser(@Param("id") int id, @Param("name") String name, @Param("pwd") String pwd);
//
//    int deleteUser(@Param("id") int id);
//
//    List<Users> getUsersLike(@Param("value") String name);

}
