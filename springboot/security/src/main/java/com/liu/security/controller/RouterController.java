package com.liu.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shidacaizi
 * @date 2020/4/19 16:20
 */
@Controller
public class RouterController {
    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/tologin")
    public String toLogin() {
        return "views/login";
    }

    @RequestMapping("/level1/{page}")
    public String level1(@PathVariable("page") int page) {
        return "views/level1/" + page;
    }

    @RequestMapping("/level2/{page}")
    public String level2(@PathVariable("page") int page) {
        return "views/level2/" + page;
    }

    @RequestMapping("/level3/{page}")
    public String level3(@PathVariable("page") int page) {
        return "views/level3/" + page;
    }
}
