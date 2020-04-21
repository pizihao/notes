package helloworld.hao.service.impl;

import helloworld.hao.mapper.UsersMapper;
import helloworld.hao.pojo.Users;
import helloworld.hao.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/4/15 18:45
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<Users> getListUsers() {
        return usersMapper.getListUsers();
    }
}
