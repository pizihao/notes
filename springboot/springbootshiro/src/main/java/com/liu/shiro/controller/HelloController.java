package com.liu.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author shidacaizi
 * @date 2020/4/20 9:41
 */
@Controller
public class HelloController {

    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        return "login";
    }

    @RequestMapping("/add")
    public String add() {
        return "add";
    }

    @RequestMapping("/update")
    public String update() {
        return "update";
    }

    @RequestMapping("/tologin")
    public String login() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model) {
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //执行登录的方法，如果没有异常就说明OK
        try {
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

    @RequestMapping("/unauth")
    @ResponseBody
    public String unauthorized() {
        return "未经授权无法访问";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg", "安全退出！");
        return "login";
    }
}
