package com.liu.bootweb.controller;

import com.liu.bootweb.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author shidacaizi
 * @date 2020/4/17 21:58
 */
@Controller
public class HelloController {

    @Autowired
    private AsyncService asyncService;

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

    @GetMapping("/async")
    public String async() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            asyncService.executeAsync();
        }
        return "index";
    }

    @GetMapping("/submitAsync")
    @ResponseBody
    public List<Integer> submitAsync() throws InterruptedException, ExecutionException {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = asyncService.submitAsync(i);
            integers.add(future.get());
        }
        return integers;
    }
}
