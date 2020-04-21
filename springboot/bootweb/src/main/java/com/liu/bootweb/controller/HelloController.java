package com.liu.bootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * @author shidacaizi
 * @date 2020/4/17 21:58
 */
@Controller
public class HelloController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model,
            HttpSession session){
        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            session.setAttribute("loginUser", username);
            return "redirect:/welcome.html";
        }else {
            model.addAttribute("msg","账号密码错误");
            return "index";
        }
    }
}
