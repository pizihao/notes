package com.liu.bootweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shidacaizi
 * @date 2020/4/18 18:32
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 登录成功之后会有用户的session
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null) {
            request.setAttribute("msg", "你还没有登录");
            request.getRequestDispatcher("/index").forward(request, response);
            return false;
        }else {
            return true;
        }
    }
}
