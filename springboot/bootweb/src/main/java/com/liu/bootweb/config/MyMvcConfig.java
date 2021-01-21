package com.liu.bootweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: JVMDome
 * @description: 跳转和拦截器
 * @author: liuwenhao
 * @create: 2021-01-10 13:27
 **/
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    //视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/welcome.html").setViewName("welcome");
    }

    //注入bean
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleReslver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index","/login","/welcome.html","/css/*","/js/*","/images/*","/fonts/*","/");
    }
}
