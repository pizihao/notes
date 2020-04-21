package helloworld.hao.controller;

import helloworld.hao.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shidacaizi
 * @date 2020/4/15 18:47
 */
@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;


//    @RequestMapping("/getListUsers")
//    public Result getListUsers(){
//        return Result.success(usersService.getListUsers());
//    }

}
