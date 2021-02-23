package com.liu.bootweb.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @program: JVMDome
 * @description: 配置国际化
 * @author: liuwenhao
 * @create: 2021-01-10 13:27
 **/
public class MyLocaleReslver implements LocaleResolver {
    //解析请求
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // 获取请求中的语言参数
        String language = request.getParameter("l");
        //如果没有就使用默认的
        Locale locale = Locale.getDefault();

        //如果请求的连接携带了国际化的参数
        if (!StringUtils.isEmpty(language)) {
            String[] s = language.split("_");
            locale = new Locale(s[0], s[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
