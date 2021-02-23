package com.liu.mybatis.controller;

import com.liu.mybatis.pojo.Users;
import com.liu.mybatis.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/4/19 15:37
 */
@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/getlist")
    public List<Users> getList() {
        return usersService.getList();
    }

    @GetMapping("/getusersbyid/{id}")
    public Users getUsersById(@PathVariable("id") int id) {
        return usersService.getUsersById(id);
    }

    @PostMapping("/addusers")
    public int addUsers(Users users) {
        return usersService.addUsers(users);
    }

    @PutMapping("/updateusers")
    public int updateUsers(Users users) {
        return usersService.updateUsers(users);
    }

    @DeleteMapping("/deleteusers/{id}")
    public int deleteUsers(@PathVariable("id") int id) {
        return usersService.deleteUsers(id);
    }
}
